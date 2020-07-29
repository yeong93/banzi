package com.kh.banzi.event.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.banzi.community.model.vo.PageInfo;
import com.kh.banzi.event.model.service.EventService;

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
			
			EventService sService = new EventService();
			
			int boardType = 4;
			String currentPage = request.getParameter("cp");
			
			if(command.equals("/event.do")) {
				errorMsg = "진행중인 이벤트 목록 조회";
				
			   PageInfo pInfo = EventService.getPageInfo(boardType, currentPage);
			}
			

		} catch (Exception e) {
			e.printStackTrace();

			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsf", errorMsg + " 과정에서 오류가 발생했습니다.");
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
