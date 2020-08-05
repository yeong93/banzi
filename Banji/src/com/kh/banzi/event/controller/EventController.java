package com.kh.banzi.event.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.java.javaURLContextFactory;

import com.google.gson.Gson;
import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.MyFileRenamePolicy;
import com.kh.banzi.event.model.service.EventService;
import com.kh.banzi.event.model.vo.Event;
import com.kh.banzi.event.model.vo.PageInfo;
import com.kh.banzi.user.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/event/*")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EventController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/event").length());

		String path = null;
		RequestDispatcher view = null;

		String status = null;
		String msg = null;
		String text = null;
		String errorMsg = null;

		try {
			EventService eService = new EventService();
			int eventType = Integer.parseInt(request.getParameter("type"));
			String currentPage = request.getParameter("cp");

//--------------------------------- 진행중인 이벤트 ------------------------------------
			if(command.equals("/eventList.do")) {
				errorMsg = "진행중인 이벤트 목록 조회";
		
				PageInfo pInfo = EventService.getPageInfo(currentPage, eventType);
				List<Event> eList = eService.eventList(pInfo, eventType); 
				List<Attachment> fList = eService.fileList(pInfo, eventType);
				
				
				path = "/WEB-INF/views/event/eventList.jsp";
			
				request.setAttribute("pInfo", pInfo); 
				request.setAttribute("eList", eList);
				request.setAttribute("fList", fList);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
//--------------------------------- 종료된 이벤트 ------------------------------------
			}else if(command.equals("/pastList.do")) {
				errorMsg = "종료된 이벤트 목록 조회";
		
				PageInfo pInfo = EventService.getPageInfo(currentPage, eventType);
				List<Event> eList = eService.eventList(pInfo, eventType); 
				List<Attachment> fList = eService.fileList(pInfo, eventType);
				
				
				path = "/WEB-INF/views/event/pastList.jsp";
			
				request.setAttribute("pInfo", pInfo); 
				request.setAttribute("eList", eList);
				request.setAttribute("fList", fList);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
//--------------------------------- 이벤트 게시글 삽입 ------------------------------------				
			}else if(command.equals("/insertEventForm.do")) {
				
				path = "/WEB-INF/views/event/insertEvent.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}else if(command.equals("/insertEvent.do")) {
				errorMsg = "이벤트 게시글 삽입";
								
				int maxSize = 1024 * 1024 * 10;
				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources\\uploadImages\\";
				MultipartRequest mRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
				String userId = ((User)request.getSession().getAttribute("loginUser")).getUserId();
				String eventTitle = mRequest.getParameter("title");
				String eventContent = mRequest.getParameter("content");
				String getStartDay = mRequest.getParameter("startDay");
				String getEndDay = mRequest.getParameter("endDay");
				
				int index = getStartDay.indexOf("T");
				
				String startDate = getStartDay.substring(0, index);
				String startTime = getStartDay.substring(index+1);
				String start = startDate + " " + startTime + ":00.0";
				java.sql.Timestamp startDay = java.sql.Timestamp.valueOf(start);	
				
				String endDate = getEndDay.substring(0, index);
				String endTime = getEndDay.substring(index+1);
				String end = endDate + " " + endTime + ":00.0";
				java.sql.Timestamp endDay = java.sql.Timestamp.valueOf(end);
				
				Event event = new Event(userId, eventTitle, eventContent, startDay, endDay);
				
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
						}
						temp.setFileLevel(fileLevel);
						temp.setFilePath(filePath);
						
						fList.add(temp);
					}
				}
				int result = eService.insertEvent(event, fList);
				
				if(result >0) {
					status = "success";
					msg = "게시글이 등록되었습니다.";
					path = "eventView.do?type="+eventType+"&cp=1&no="+result;
					
				}else {
					status = "error";
					msg = "게시글 등록 실패";
					path = request.getHeader("referer");
				}
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
//--------------------------------- 이벤트 게시글 상세 조회 ------------------------------------				
			}else if(command.equals("/eventView.do")) {	
				errorMsg = "이벤트 게시글 상세 조회";
				
				int eventNo = Integer.parseInt(request.getParameter("no"));
				
				Event event = eService.selectEvent(eventNo);
				System.err.println(event);
				if(event != null) {
					List<Attachment> fList = eService.selectFiles(eventNo);
					
					if(!fList.isEmpty()) {
						request.setAttribute("fList", fList);
					}
					path = "/WEB-INF/views/event/eventView.jsp";
					request.setAttribute("event", event);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				}else {
					status = "error";
					msg = "게시글 조회 실패";
					request.getSession().setAttribute("status", status);
					request.getSession().setAttribute("msg", msg);
					response.sendRedirect(request.getHeader("referer"));
				}
				
//--------------------------------- 이벤트 게시글 수정 ------------------------------------				
			}else if(command.equals("/eventUpdateForm.do")) {				
				int eventNo = Integer.parseInt(request.getParameter("no"));
	
				Event event = eService.eventUpdateView(eventNo);
				
				if(event != null) {
					List<Attachment> fList = eService.selectFiles(eventNo);
					
					if(!fList.isEmpty()) {
						request.setAttribute("fList", fList);
					}
					path = "/WEB-INF/views/event/eventUpdate.jsp";
					request.setAttribute("event", event);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}
				
			}else if(command.equals("/eventUpdate.do")) {
				errorMsg = "이벤트 게시글 수정";
				
				int maxSize = 1024 * 1024 * 10;
				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources\\uploadImages\\";
				MultipartRequest mRequest  
				= new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());

				int eventNo = Integer.parseInt(mRequest.getParameter("no"));
				String eventTitle = mRequest.getParameter("title");
				String eventContent = mRequest.getParameter("content");
				String getStartDay = mRequest.getParameter("startDay");
				String getEndDay = mRequest.getParameter("endDay");
				
				int index = getStartDay.indexOf("T");
				
				String startDate = getStartDay.substring(0, index);
				String startTime = getStartDay.substring(index+1);
				String start = startDate + " " + startTime + ":00.0";
				java.sql.Timestamp startDay = java.sql.Timestamp.valueOf(start);	
				
				String endDate = getEndDay.substring(0, index);
				String endTime = getEndDay.substring(index+1);
				String end = endDate + " " + endTime + ":00.0";
				java.sql.Timestamp endDay = java.sql.Timestamp.valueOf(end);
				
				Event event = new Event(eventNo, eventTitle, eventContent, startDay, endDay);
				
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

		                 }
		                 
		                 temp.setFileLevel(fileLevel);
		                 temp.setFilePath(filePath);
		                 
		                 fList.add(temp);
					}
				}
				int result = eService.eventUpdate(event, fList);
				
				if(result > 0) {
					status = "success";
					msg = "게시글 수정 성공";
					path = "eventView.do?type="+eventType+"&cp="+currentPage+"&no="+result;
				}else {
					status = "error";
					msg = "게시글 수정 실패";
					path = request.getHeader("referer");
				}
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
			
//--------------------------------- 이벤트 게시글 삭제 ------------------------------------				
			}else if(command.equals("/eventDelete.do")) {
				errorMsg = "이벤트 게시글 삭제";
				
				int eventNo = Integer.parseInt(request.getParameter("no"));
				
				int result = eService.eventDelete(eventNo);
				
				if(result > 0) {
					status = "success";
					msg = "게시글 삭제 성공";
					if(eventType == 1) {
						path = "eventList.do?type=" + eventType;
					}else {
						path = "pastList.do?type=" + eventType;
					}
				}else {
					status = "error";
					msg = "게시글 삭제 실패";
					path = request.getHeader("referer");
				}
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
//--------------------------------- 이벤트 당첨자 ------------------------------------
			}else if(command.equals("/winnerList.do")) {
				errorMsg = "이벤트 당첨자 목록 조회";
		
				PageInfo pInfo = eService.winnerPageInfo(currentPage);
				List<Event> wList = eService.winnerList(pInfo); 		
				
				path = "/WEB-INF/views/event/winnerList.jsp";
			
				request.setAttribute("pInfo", pInfo); 
				request.setAttribute("wList", wList);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			
			}else if(command.equals("/winnerView.do")) {
				errorMsg = "이벤트 당첨자 세부 조회";
				
				int no = Integer.parseInt(request.getParameter("no"));
				Event event = eService.winnerView(no);
				
				Gson gson = new Gson();
				gson.toJson(event, response.getWriter());
				
			}else if(command.equals("/changeWinnerForm.do")) {
				int no = Integer.parseInt(request.getParameter("no"));
				
				Event event = eService.winnerView(no);
				
				path = "/WEB-INF/views/event/changeWinner.jsp";
				request.setAttribute("event", event);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}else if(command.equals("/changeWinner.do")) {
				errorMsg = "이벤트 당첨자 수정";
				
				int no = Integer.parseInt(request.getParameter("no"));
				String content = request.getParameter("content");
				System.out.println(content);
						
				int result = eService.changeWinner(no, content);
				
				if(result > 0) {
					status = "success";
					msg = "당첨자 발표 수정 성공";
				}else {
					status = "error";
					msg = "당첨자 발표 수정 실패";
				}
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				path = "winnerList.do?type="+eventType+"&cp="+currentPage+"&no="+no;
				response.sendRedirect(path);
				
			}

		} catch (Exception e) {
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
