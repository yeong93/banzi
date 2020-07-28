package com.kh.banzi.myPage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.banzi.myPage.model.service.MyPageService;
import com.kh.banzi.user.model.service.UserSerivce;
import com.kh.banzi.user.model.vo.User;

@WebServlet("/myPage/*")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPageController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/myPage").length());
		
		String path = null;
		RequestDispatcher view = null;
		
		String status = null;
		String msg = null;
		String text = null;
		String errorMsg = null;
		
		try {
			MyPageService mService = new MyPageService();
			
			if(command.equals("/changeUserForm.do")) {
				path = "/WEB-INF/views/myPage/changeUser.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}else if(command.equals("/changeUser.do")){
				errorMsg = "회원정보 수정";
				
				HttpSession session = request.getSession();
				User loginUser = (User)session.getAttribute("loginUser");
				
				String userEmail = request.getParameter("email");
				String userGrade = request.getParameter("grade");
				String userQuestion = request.getParameter("question");
				String userAnswer = request.getParameter("answer");
				
				User user = new User(loginUser.getUserId(), userEmail, userGrade, userQuestion, userAnswer);
				
				int result = new MyPageService().changeUser(user);
				
				if(result > 0) {
					status = "success";
					msg = "회원정보 수정 성공";
					text = "회원정보가 수정되었습니다";
					
					user.setUserName(loginUser.getUserName());
					user.setUserGrade(loginUser.getUserGrade());
					session.setAttribute("loginUser", user);
				}else {
					status = "failure";
					msg="회원정보 수정 실패";
					text="회원정보 수정 중 문제가 발생되었습니다.\n"
							+ "문제가 지속된 경우 관리자에게 문의해주세요.";
				}
				request.getSession().setAttribute("status", status);
				request.getSession().setAttribute("msg", msg);
				request.getSession().setAttribute("text", text);
				response.sendRedirect("changeUserForm.do");
				
			}else if(command.equals("/changePwdForm.do")){
				path = "/WEB-INF/views/myPage/changePwd.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}else if(command.equals("/changePwd.do")){
				errorMsg = "비밀번호 수정";
				
				String nowPwd = request.getParameter("nowPwd");
				String newPwd = request.getParameter("newPwd");
				
				HttpSession session = request.getSession();
				String userId = ((User)session.getAttribute("loginUser")).getUserId();
				User user = new User(userId, nowPwd);
				
				int result = new MyPageService().changePwd(user, newPwd);
				
				if(result > 0) {
					status = "success";
					msg = "비밀번호가 변경되었습니다.";
					response.sendRedirect(request.getContextPath());
				}else if(result < 0) {
					status = "error";
					msg = "현재 비밀번호가 일치하지 않습니다.";
				}else {
					status = "error";
					msg = "비밀번호 변경에 실패하였습니다.";
				}
				session.setAttribute("status", status);
				session.setAttribute("msg", msg);
				response.sendRedirect("changePwdForm.do");
				
			}else if(command.equals("/secessionForm.do")){
				path = "/WEB-INF/views/myPage/secession.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}else if(command.equals("/secession.do")){
				errorMsg = "회원 탈퇴";
				
				String nowPwd = request.getParameter("pwd");
				
				HttpSession session = request.getSession();
				String userId = ((User)session.getAttribute("loginUser")).getUserId();
				User user = new User(userId, nowPwd);
				
				int result = new MyPageService().secession(user);
				
				String url = request.getHeader("referer");
				
				if(result > 0) {
					session.removeAttribute("loginUser");
					status = "success";
					msg = "회원 탈퇴에 성공하였습니다.";
					url = request.getContextPath();
				}else if (result == 0) {
					status = "error";
					msg = "회원 탈퇴에 실패하였습니다.";
				}else {
					status = "error";
					msg = "현재 비밀번호가 일치하지 않습니다.";
				}
				session.setAttribute("status", status);
				session.setAttribute("msg", msg);
				response.sendRedirect(url);
				
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
