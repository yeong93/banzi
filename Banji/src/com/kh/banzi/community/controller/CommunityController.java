package com.kh.banzi.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.banzi.community.model.service.CommunityService;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.community.model.vo.PageInfo;

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
	          CommunityService service = new CommunityService();
	          
	          String currentPage = request.getParameter("cp");

	          
	          if(command.equals("/list.do")) {
	              PageInfo pInfo = service.getPageInfo(currentPage);
	              
	              List<Community> cList = service.selectList(pInfo);
	              
	              
	              path = "/WEB-INF/views/community/communityList.jsp";
	              view = request.getRequestDispatcher(path);
	              view.forward(request, response);
	              
	          }
	          
	      }catch(Exception e) {
	          e.printStackTrace();
	      }
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
