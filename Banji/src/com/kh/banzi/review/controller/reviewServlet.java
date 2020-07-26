package com.kh.banzi.review.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/review/*")
public class reviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath +"/review").length());
		
		try {
			if(command.equals("/review.do")) {
				String path = "/WEB-INF/views/review/userReview.jsp";
				RequestDispatcher view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				// 리뷰 작성 폼 이동
			}else if(command.equals("/writeReviewForm.do")) {
				
				String path = "/WEB-INF/views/review/userReviewForm.jsp";
				
//				request.setAttribute("writer", writer);
//				request.setAttribute("reviewCreateDate", reviewCreateDate);
				RequestDispatcher view = request.getRequestDispatcher(path);
				
				view.forward(request, response);
				
			}else if(command.equals("/writeReviewForm.do")) {
				
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
