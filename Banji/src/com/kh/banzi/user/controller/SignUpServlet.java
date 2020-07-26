package com.kh.banzi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.banzi.user.model.service.UserSerivce;
import com.kh.banzi.user.model.vo.User;


@WebServlet("/user/*")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Front Controller 패턴
		// - 클라이언트의 요청을 한 곳으로 집중시켜 개발하여 코드 길이 감소와 유지보수성을 증가시킨 패턴
		// - 요청에 따른 Servlet을 각각 생성하지 않고 하나의 Servlet에 여러 요청을 처리함.
		
		String uri = request.getRequestURI();
		// http://localhost:8080/wsp/notice/list.do;
		
		String contextPath = request.getContextPath();
		// http://localhost:8080/wsp
		
		// substring : 문자열을 자르는 메소드
		String command = uri.substring((contextPath + "/user").length());
		
		// forward용 변수
		String path = null;
		RequestDispatcher view = null;
		// RequestDispatcher : 클래스, 현재 request에 담긴 정보를 저장하고 있다가
		// 그 다음 페이지, 그 다음 페이지에도 해당 정보를 볼 수 있게 계속 저장하는 기능
		
		try {
			if(command.equals("/signUpAssign1.do")) {
				path = "/WEB-INF/views/user/signUpAssign_1.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			}
			else if(command.equals("/signUpAssign2.do")) {
				path = "/WEB-INF/views/user/signUpAssign_2.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			else if(command.equals("/signUp.do")) {
				// 1. POST 방식으로 전달받은 데이터 문자 인코딩 변경
				request.setCharacterEncoding("UTF-8");
				
				// 전송값(파라미터)를 모두 변수에 저장
				String userId = request.getParameter("id");
				String userPwd = request.getParameter("pwd");
				String userName = request.getParameter("name");
				String userEmail = request.getParameter("email");
				String userGrade = request.getParameter("grade");
				String userQuestion = request.getParameter("question");
				String userAnswer = request.getParameter("answer");
				
				// 생성자를 이용해 값을 세팅
				User user = new User(userId, userPwd, userName, userEmail, userGrade, userQuestion, userAnswer);
				user.setUserPwd(userPwd);
				
				try {
					int result = new UserSerivce().signUp(user);
			
					if(result > 0) {
						// 회원가입 성공
						response.sendRedirect(request.getContextPath());
					}else {
						// 회원가입 실패
					}
				}catch(Exception e) {
					e.printStackTrace();
				}

			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
