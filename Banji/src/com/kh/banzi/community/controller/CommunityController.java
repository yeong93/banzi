package com.kh.banzi.community.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/community/*")
public class CommunityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CommunityController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String uri = request.getRequestURI();
	      
	      String contextPath = request.getContextPath();
	      
	      String command = uri.substring((contextPath + "/community").length());
	      
	      // forword용 변수 
	      String path = null;
	      RequestDispatcher view = null;
	      
	      String status = null; 
	      String msg = null;
	      String text = null;
	      String errorMsg = null;
	      try {
	          
	      }finally {
	          
	      }
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}