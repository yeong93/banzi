package com.kh.banzi.masterPage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.banzi.masterPage.model.service.MasterPageService;
import com.kh.banzi.user.model.vo.User;

/**
 * Servlet implementation class MasterPageServlet
 */
@WebServlet("/masterPage/*")
public class MasterPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MasterPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/masterPage").length());
		String path = null;
		RequestDispatcher view = null;
		String status = null;
		String msg = null;
		String text = null;
		String errorMsg = null;
		
		try {
			MasterPageService service = new MasterPageService();
			
			if(command.equals("/userAuthList.do")) {
				errorMsg = "회원 등급이  부여되지 않은 회원 목록 조회";
				
				List<User> aList = service.authList();
				
				path = "/WEB-INF/views/masterPage/userAuthList.jsp";
				request.setAttribute("aList", aList);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}else if(command.equals("/changeAuth.do")) {
				errorMsg = "회원 등급 부여";
				
				int no = Integer.parseInt(request.getParameter("no"));
				int result = service.changeAuth(no);
				
				if(result > 0) {
					status = "success";
					msg = "회원 등급 부여 성공";
				}else {
					status = "error";
					msg = "회원 등급 부여 실패";
				}
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(request.getHeader("referer"));
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
