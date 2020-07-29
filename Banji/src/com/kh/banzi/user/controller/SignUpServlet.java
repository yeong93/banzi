package com.kh.banzi.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		// 메세지 전달용 변수
		String status = null;
		String msg = null;
		String text = null;
		String ErrorMsg = "과정에서 오류가 발생하였습니다.";
		
		try {
			// 회원가입 첫 페이지 (1) 약관 동의
			if(command.equals("/signUpAssign1.do")) {

				path = "/WEB-INF/views/user/signUpAssign_1.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}
			// 회원가입 두번째 페이지(2-1) 회원 가입
			else if(command.equals("/signUpAssign2.do")) {
				path = "/WEB-INF/views/user/signUpAssign_2.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			// 회원가입 두번째 페이지 (2-2) 회원가입 정보 전달
			else if(command.equals("/signUp.do")) {
				
				ErrorMsg = "회원 가입" + ErrorMsg;
				// 1. POST 방식으로 전달받은 데이터 문자 인코딩 변경
				request.setCharacterEncoding("UTF-8");
				// 세션 생성
				HttpSession session = request.getSession();
				
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
				
					int result = new UserSerivce().signUp(user);
					
					if(result > 0) {
						// 회원가입 성공
						session.setAttribute("signUpUser", user);
						status = "success";
						if(user.getUserGrade().equals("user")) {
							msg = "회원가입에  성공하였습니다. 가입을 축하합니다.";
							path = "signUpSuccessUser.do";
						}else {
							msg = "회원 가입 대기중입니다. 관리자의 승인이 있을 때까지 기다려주세요.";
							path = "signUpSuccessExpert.do";
						}
						
						session.setAttribute("status", status);
						session.setAttribute("msg", msg);
						response.sendRedirect(path);
						
					}
					/*else {
						// 회원가입 실패
						status = "error";
						msg = "회원가입에 실패했습니다.";
						path = "/WEB-INF/views/common/errorPage.jsp";
					}
					*/
			// 회원가입 성공 페이지 (유저)로 이동
			}else if(command.equals("/signUpSuccessUser.do")) {
				
				path = "/WEB-INF/views/user/signUpAssign_3_1.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			// 회원가입 성공 페이지 (전문가)로 이동
			else if(command.equals("/signUpSuccessExpert.do")) {
		
				path = "/WEB-INF/views/user/signUpAssign_3_2.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			// 아이디 유효성 검사
			else if(command.equals("/idDupCheck.do")) {
				String id = request.getParameter("id");
				try {
					int result = new UserSerivce().idDupCheck(id);
					response.getWriter().print(result);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", ErrorMsg);
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
