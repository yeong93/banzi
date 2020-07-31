package com.kh.banzi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.kh.banzi.user.model.service.UserSerivce;
import com.kh.banzi.user.model.vo.User;


@WebServlet("/userLogin/*")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/userLogin").length());
		
		String path = null;
		RequestDispatcher view = null;
		
		String status = null;
		String msg = null;
		String text = null;
		String errorMsg = null;

		try {
			UserSerivce uService = new UserSerivce();
			
			// ------------------------ 로그인, 아이디 기억하기 ------------------------
			if(command.equals("/loginForm.do")) {
				path = "/WEB-INF/views/user/login.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}else if(command.equals("/login.do")) {
				errorMsg = "로그인";
				
				String userId = request.getParameter("userId");
				String userPwd = request.getParameter("userPwd");
				User user = new User(userId, userPwd);
				
				User loginUser = uService.login(user);
				
				HttpSession session = request.getSession();
				
				if(loginUser != null) {
					session.setAttribute("loginUser", loginUser);
					session.setMaxInactiveInterval(1800);

					String saveId = request.getParameter("saveId");
					Cookie cookie = new Cookie("saveId", userId);
					System.out.println("saveId : " + saveId);
					if(saveId != null) { 
						cookie.setMaxAge(60*60*24*7);
					}else {
						cookie.setMaxAge(0);
					}
					
					cookie.setPath("/"); 
					response.addCookie(cookie);
					
					path = request.getHeader("referer");
					
					
					response.getWriter().print(0);
				}else {
					response.getWriter().print(1);
				}
				
				
			// ------------------------ 로그아웃 ------------------------
			}else if(command.equals("/logout.do")) {
				request.getSession().invalidate();
				response.sendRedirect(request.getContextPath());
				
				
			// ------------------------ 아이디 찾기, 비밀번호 찾기 ------------------------
			}else if(command.equals("/searchIdForm.do")) {
				path = "/WEB-INF/views/user/searchId.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}else if(command.equals("/searchPwdForm.do")) {
				path = "/WEB-INF/views/user/searchPwd.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
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
