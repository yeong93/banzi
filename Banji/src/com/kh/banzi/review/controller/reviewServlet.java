package com.kh.banzi.review.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.banzi.common.MyFileRenamePolicy;
import com.kh.banzi.review.model.service.ReviewService;
import com.kh.banzi.review.model.vo.Attachment;
import com.kh.banzi.review.model.vo.PageInfo;
import com.kh.banzi.review.model.vo.Review;
import com.kh.banzi.user.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/review/*")
public class reviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath +"/review").length());
		
		String path = null;
		RequestDispatcher view = null;
		
		String status = null;
		String msg = null;
		String text = null;
		String errorMsg = null;
		
		
		try {
			ReviewService reviewService = new ReviewService();
			
			int boardType = Integer.parseInt(request.getParameter("type"));
			String currentPage = request.getParameter("cp");
			
			// 게시글 목록 조회
			if(command.equals("/review.do")) {
				errorMsg = "리뷰 목록 조회";
				
				PageInfo pInfo = reviewService.getPageInfo(currentPage,boardType);
				List<Review> rList = reviewService.selectList(pInfo);
				
				
				List<Attachment> fList = reviewService.selectFileList(pInfo);
				
				path = "/WEB-INF/views/review/userReview.jsp";
				request.setAttribute("pInfo", pInfo);
				request.setAttribute("rList", rList);
				request.setAttribute("fList", fList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			// 리뷰 작성 화면 이동
			}else if(command.equals("/writeReviewForm.do")) {
				path = "/WEB-INF/views/review/userReviewForm.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			// 리뷰 등록 (글 + 파일)
			}else if(command.equals("/insertReview.do")) {
				int maxSize = 1024 *1024 *10;
				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources\\uploadImages";
				
				MultipartRequest mRequest
				= new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
				String reviewTitle = mRequest.getParameter("title");
				int reviewCategory = Integer.parseInt(mRequest.getParameter("category"));
				String reviewContent = mRequest.getParameter("content");
				int reviewRating =  Integer.parseInt(mRequest.getParameter("rating"));
				String userId = ((User)request.getSession().getAttribute("loginUser")).getUserId();
				
				Review review = new Review(userId, reviewTitle, reviewContent, reviewRating, reviewCategory, boardType);
				
				List<Attachment> fList = new ArrayList<Attachment>();
				Enumeration<String> files = mRequest.getFileNames();
				
				Attachment temp = null; // 임시 참조 변수
				while(files.hasMoreElements()) {
					String name = files.nextElement();
			
					if(mRequest.getFilesystemName(name) != null) {
						temp = new Attachment();
						temp.setFileOriginName(mRequest.getOriginalFileName(name));
						temp.setFileChangeName(mRequest.getFilesystemName(name));
						
						// name 속성에 따라 파일 레벨 지정
						// img1 -> 0(썸네일), img2->1, img3->2, img4->3
						int fileLevel = 0;
						switch(name) {
						case "img1" : fileLevel = 0; break;
						case "img2" : fileLevel = 1; break;
						case "img3" : fileLevel = 2; break;
						}
						
						temp.setFileLevel(fileLevel);
						temp.setFilePath(filePath);
						fList.add(temp);
						// 파일 얻어오기 끝
					}
				}
				//
				int result = reviewService.insertReview(review, fList, boardType);
				
				if(result>0) {
					status= "success";
					msg = "게시글 등록 성공";
					path = "review.do?type="+boardType + "&cp=1&no="+result;
					
				}else {
					status= "error";
					msg = "게시글 등록 실패";
					path = request.getHeader("referer");
				}
				
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
			}
			
			
			// 게시글 상세조회 Controller
			else if(command.equals("/detailReview.do")) {
				int boardNo = Integer.parseInt(request.getParameter("no"));
				// 1. 게시글 조회
				Review review = reviewService.detailReview(boardNo);
				
				if(review!= null) {
					List<Attachment> fList = reviewService.selectFiles(boardNo);
	  
					if(!fList.isEmpty()) request.setAttribute("fList", fList);
					
					path = "/WEB-INF/views/review/userReviewDetail.jsp";
					request.setAttribute("review", review);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}else {
					status = "error";
	                msg = "게시글 조회 실패";
	                request.getSession().setAttribute("status", status);
	                request.getSession().setAttribute("msg", msg);
	                response.sendRedirect(request.getHeader("referer"));
				}
			
				// 리뷰 삭제
				}else if(command.equals("/delete.do")) {
					errorMsg = "리뷰 삭제";
					int reviewNo = Integer.parseInt(request.getParameter("no"));
					
					int result = reviewService.deleteReview(reviewNo);
					
					 if(result>0) {
						 status = "success";
						 msg = "리뷰 삭제 성공";
						 path = "review.do?type=" + boardType;
					 }else {
						 status = "error";
						 msg="리뷰 삭제 실패";
						 path = request.getHeader("referer");
					 }
					
					 request.getSession().setAttribute("status", status);
					 request.getSession().setAttribute("msg", msg);
					 response.sendRedirect(path);
					 
				// 리뷰 수정 화면 위임
				}else if(command.equals("/updateReview.do")) {
					int reviewNo = Integer.parseInt(request.getParameter("no"));
					Review review = reviewService.updateReview(reviewNo);
					
					if(review != null) {
						List<Attachment> fList = reviewService.selectFiles(reviewNo);
						
						if(!fList.isEmpty()) {
							request.setAttribute("fList", fList);
						}
						
						path="/WEB-INF/views/review/reviewUpdateForm.jsp";
						request.setAttribute("review", review);
						view = request.getRequestDispatcher(path);
						view.forward(request, response);
					}
					
				// --- 여기까지 완성
				// 리뷰 수정 ing -> 평가 가져온게 잘못된듯.
				}else if(command.equals("/updateReviewForm.do")) {
					 System.out.println("OK");
					
					 int maxSize = 1024 * 1024 *10; 
					String root = request.getSession().getServletContext().getRealPath("/");
					
					String filePath = root + "resources\\uploadImages\\";
					MultipartRequest mRequest =
							new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
					
					// 수정
					int reviewNo =  Integer.parseInt(mRequest.getParameter("no"));
					String reviewTitle = mRequest.getParameter("title");
					String reviewContent = mRequest.getParameter("content");
					int reviewCategory = Integer.parseInt(mRequest.getParameter("category"));
					int rating = Integer.parseInt(mRequest.getParameter("forwardRating"));
					
					
					Review review = new Review(reviewNo, reviewTitle, reviewContent, rating, reviewCategory);
					
					System.out.println("review 수정!!" + review);
					List<Attachment> fList = new ArrayList<Attachment>();
					
					Enumeration<String> files = mRequest.getFileNames();
					Attachment temp = null;
					while(files.hasMoreElements()) {
						String name = files.nextElement();
						
						if(mRequest.getFilesystemName(name) != null) {
							temp= new Attachment();
							
							temp.setFileOriginName(mRequest.getOriginalFileName(name));
							temp.setFileChangeName(mRequest.getFilesystemName(name));
							
			                 int fileLevel = 0;
			                 switch(name) {
			                 case "img1" : fileLevel = 1; break;
			                 case "img2" : fileLevel = 2; break;
			                 case "img3" : fileLevel = 3; break;
			                 }
			                 
			                 temp.setFileLevel(fileLevel);
			                 temp.setFilePath(filePath);
			                 fList.add(temp);
						}
					}
					// 여기까지
					//System.out.println(review);
					int result = reviewService.updateReview(review, fList);
					System.out.println("몇번인데"+result);
					
					if(result>0) {
						status = "success";
						msg = "게시글 수정 성공";
						path = "detailReview.do?type="+boardType + "&cp=" + currentPage +"&no=" + result;
					}else {
						status = "error";
						msg = "게시글 수정 실패";
						path = request.getHeader("referer");
					}
					
					request.getSession().setAttribute("status", status);
					request.getSession().setAttribute("msg", msg);
					response.sendRedirect(path);
					
				
				// 카테고리 분류 화면 이동	
				}else if(command.equals("/reviewCategory.do")) {
					errorMsg = "리뷰 목록 조회";
					
					PageInfo pInfo = reviewService.getPageInfo(currentPage,boardType);
					
					int category = Integer.parseInt(request.getParameter("category"));
					List<Review> rList = reviewService.selectCategotyList(pInfo,category);
					
					List<Attachment> fList = reviewService.selectFileList(pInfo);
					path = "/WEB-INF/views/review/userCategoryReview.jsp";
					
					request.setAttribute("pInfo", pInfo);
					request.setAttribute("rList", rList);
					request.setAttribute("fList", fList);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}
	
			
		}catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", errorMsg + " 과정에서 오류가 발생했습니다.");
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
