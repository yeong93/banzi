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
				errorMsg = "로그아웃";
				
				request.getSession().invalidate();
				response.sendRedirect(request.getContextPath());
				
				
			// ------------------------ 비밀번호 찾기 ------------------------
			}else if(command.equals("/searchForm.do")) {
				
				path = "/WEB-INF/views/user/search.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}else if(command.equals("/search.do")) {
				errorMsg = "비밀번호 변경";
				
				String userId = request.getParameter("userId");
				String userQuestion = request.getParameter("userQuestion");
				String userAnswer = request.getParameter("userAnswer");
				String userPwd = request.getParameter("userPwd");
				
				User user = new User(userId, userQuestion, userAnswer);
				user.setUserPwd(userPwd);
				
				int result = uService.search(user);
				if(result > 0) {
					status = "success";
					msg = "비밀번호 변경 성공";
					text = "비밀번호가 변경 되었습니다";
				}else {
					status = "error";
					msg="비밀번호 변경 실패";
					text="아이디, 보안질문, 보안답변을 확인해주세요.";

				}
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				request.getSession().setAttribute("text", text);
				response.sendRedirect(request.getContextPath());
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
