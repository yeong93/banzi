package com.kh.banzi.information.controller;

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

import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.MyFileRenamePolicy;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.information.model.service.InformationService;
import com.kh.banzi.information.model.vo.Information;
import com.kh.banzi.user.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/information/*")
public class InformationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Front Controller 패턴
		// - 클라이언트의 요청을 한 곳으로 집중시켜 개발하여 코드 길이 감소와 유지보수성을 증가시킨 패턴
		// - 요청에 따른 Servlet을 각각 생성하지 않고 하나의 Servlet에 여러 요청을 처리함.
		
		String uri = request.getRequestURI();
		// http://localhost:8080/banzi/information/list.do;
		
		String contextPath = request.getContextPath();
		// http://localhost:8080/banzi
		
		// substring : 문자열을 자르는 메소드
		String command = uri.substring((contextPath + "/information").length());
		
		// forward용 변수
		String path = null;
		RequestDispatcher view = null;
		// RequestDispatcher : 클래스, 현재 request에 담긴 정보를 저장하고 있다가
		// 그 다음 페이지, 그 다음 페이지에도 해당 정보를 볼 수 있게 계속 저장하는 기능
		
		// 메세지 전달용 변수
		String status = null;
		String msg = null;
		String text = null;
		String ErrorMsg = null;
		
		try {
			InformationService service = new InformationService();
			
			// 게시판은 기본적으로 게시판 타입(종류), 현재 페이지 정보를 유지
			// 게시판 타입을 쿼리스트링에서 얻어오기
			
			int boardType = Integer.parseInt(request.getParameter("type"));
			
			// 게시판 목록 현재 페이지를 쿼리스트링에서 얻어오기
			String currentPage = request.getParameter("cp");
			
			if(command.equals("/list.do")) {
				ErrorMsg = "정보 게시판 목록 조회";
				
				PageInfo pInfo = service.getPageInfo(currentPage, boardType);
				
				List<Information> bList = service.selectList(pInfo);
				
				path = "/WEB-INF/views/information/informationList.jsp";
				request.setAttribute("pInfo", pInfo);
				request.setAttribute("bList", bList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			//  ================= 게시글 작성 화면 이동 Controller =========================
			}else if(command.equals("/insertForm.do")) {
				path = "/WEB-INF/views/information/informationInsert.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			//  ================= 게시글 작성 Controller =========================
			}else if(command.equals("/insert.do")){

				int maxSize = 1024 * 1024 * 10; // 10MB
				
				String root = request.getSession().getServletContext().getRealPath("/");

				String filePath = root + "resources\\uploadImages";

				MultipartRequest mRequest = 
				new MultipartRequest(request, filePath, maxSize, "UTF-8",
						new MyFileRenamePolicy());
				String infoBoardTitle = mRequest.getParameter("title");
				String infoBoardContent = mRequest.getParameter("content");
				String categoryName = mRequest.getParameter("category");

				String userId = ((User)request.getSession().getAttribute("loginUser")).getUserId();
				Information information = new Information(infoBoardTitle, infoBoardContent, userId, categoryName, boardType);
				
				List<Attachment> fList = new ArrayList<Attachment>();
				
				Enumeration<String> files = mRequest.getFileNames();
				
				Attachment temp = null;
				while(files.hasMoreElements()) {
					String name = files.nextElement();
					
					if(mRequest.getFilesystemName(name) != null) {
						temp = new Attachment();
						temp.setFileOriginName(mRequest.getOriginalFileName(name));
						temp.setFileChangeName(mRequest.getFilesystemName(name));
						
						int fileLevel = 0;
						switch(name) {
						case "img1" : fileLevel = 0; break;
						case "img2" : fileLevel = 1; break;
						case "img3" : fileLevel = 2; break;
						case "img4" : fileLevel = 3; break;
						}
						temp.setFileLevel(fileLevel);
						// 파일 저장 경로 추가
						temp.setFilePath(filePath);
						
						fList.add(temp);
					}
				}
				int result = service.insertInformation(information,fList);
				
				if(result >0) {
					status = "success";
					msg = "게시글이 등록되었습니다.";
					path = "view.do?type=" + boardType+"&cp=1&no="+result;
					
				}else {
					status = "error";
					msg = "게시글 등록 실패";
					path = request.getHeader("referer");
				}
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
			// ============= 게시글 상세 조회 Controller =============================
			}else if(command.equals("/view.do")) {
				int infoBoardNo = Integer.parseInt(request.getParameter("no"));
				// 1. 게시글 조회
				Information information = service.selectInformation(infoBoardNo);
				if(information != null) {
					List<Attachment> fList = service.selectFiles(infoBoardNo);
					
					if(!fList.isEmpty()) {
						request.setAttribute("fList", fList);
					}
					path = "/WEB-INF/views/information/informationView.jsp";
					request.setAttribute("information", information);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}else {
					status = "error";
					msg = "게시글 조회 실패";
					request.getSession().setAttribute("status", status);
					request.getSession().setAttribute("msg", msg);
					// 이전 페이지로 이동함
					response.sendRedirect(request.getHeader("referer"));
				}
				
			//  ================= 게시글 삭제 Controller =========================
			}else if(command.equals("/delete.do")) {
				int infoBoardNo = Integer.parseInt(request.getParameter("no"));
				int result = service.deleteInformation(infoBoardNo);
				
				if(result >0) {
					status = "success";
					msg = "게시글이 삭제되었습니다.";
					path = "list.do?type=" + boardType;
				}else {
					status = "error";
					msg = "게시글 삭제에 실패하셨습니다." ;
					path = request.getHeader("referer");
				}
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
			// ================= 게시글 수정 화면 이동  Controller =========================
			}else if(command.equals("/updateForm.do")) {
				int infoBoardNo = Integer.parseInt(request.getParameter("no"));
				// 업데이트를 위한 게시글 정보 조회 서비스 호출
				Information information = service.updateView(infoBoardNo);
				System.out.println(information);
				if(information != null) {
					List<Attachment> fList = service.selectFiles(infoBoardNo);
					if(!fList.isEmpty()) {
						request.setAttribute("fList", fList);
					}
				path = "/WEB-INF/views/information/informationUpdateForm.jsp";
				request.setAttribute("information", information);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				}
				
			// ================= 게시글 수정 Controller =========================
			}else if(command.equals("/update.do")) {
				int maxSize = 1024 * 1024 * 10;
				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources\\uploadImages\\";
				
				MultipartRequest mRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
				int infoBoardNo = Integer.parseInt(mRequest.getParameter("no"));
				String infoBoardTitle = mRequest.getParameter("title");
				String infoBoardContent = mRequest.getParameter("content");
				String categoryName = mRequest.getParameter("category");
				
				Information information = new Information(infoBoardNo, infoBoardTitle, infoBoardContent, categoryName);
				
				List<Attachment> fList = new ArrayList<Attachment>();
				Enumeration<String> files = mRequest.getFileNames();
				
				Attachment temp = null;
				while(files.hasMoreElements()) {
					String name = files.nextElement();
					
					if(mRequest.getFilesystemName(name) != null) {
						temp = new Attachment();
						
						temp.setFileOriginName(mRequest.getOriginalFileName(name));
						temp.setFileChangeName(mRequest.getFilesystemName(name));
						
						int fileLevel = 0;
						switch(name) {
						case "img1" : fileLevel = 0; break;
						case "img2" : fileLevel = 1; break;
						case "img3" : fileLevel = 2; break;
						case "img4" : fileLevel = 3; break;
						}
						temp.setFileLevel(fileLevel);
						temp.setFilePath(filePath);
						fList.add(temp);
					}
				}
				int result = service.updateInformation(information, fList);
				
				if(result >0) {
					status = "success";
					msg = "게시글 수정에 성공했습니다.";
					path = "view.do?type="+boardType+"&cp="+currentPage+"&no="+result;
				}else {
					status = "error";
					msg = "게시글 수정에 실패했습니다.";
					path = request.getHeader("referer"); // 이전 페이지로 이동
				}
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("ErrorMsg", ErrorMsg + "과정에서 오류가 발생했습니다.");
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
