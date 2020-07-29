package com.kh.banzi.information.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.banzi.common.PageInfo;
import com.kh.banzi.information.model.service.InformationService;
import com.kh.banzi.information.model.vo.Information;

@WebServlet("/information/*")
public class InformationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Front Controller 패턴
		// - 클라이언트의 요청을 한 곳으로 집중시켜 개발하여 코드 길이 감소와 유지보수성을 증가시킨 패턴
		// - 요청에 따른 Servlet을 각각 생성하지 않고 하나의 Servlet에 여러 요청을 처리함.
		
		String uri = request.getRequestURI();
		// http://localhost:8080/banzi/information/list.do;
		
		String contextPath = request.getContextPath();
		// http://localhost:8080/banzi
		
		// substring : 문자열을 자르는 메소드
		String command = uri.substring((contextPath + "/information").length());
		
		// forward용 변수
		String path = null;
		RequestDispatcher view = null;
		// RequestDispatcher : 클래스, 현재 request에 담긴 정보를 저장하고 있다가
		// 그 다음 페이지, 그 다음 페이지에도 해당 정보를 볼 수 있게 계속 저장하는 기능
		
		// 메세지 전달용 변수
		String status = null;
		String msg = null;
		String text = null;
		String ErrorMsg = null;
		
		try {
			InformationService service = new InformationService();
			
			// 게시판은 기본적으로 게시판 타입(종류), 현재 페이지 정보를 유지
			// 게시판 타입을 쿼리스트링에서 얻어오기
			System.out.println(request.getParameter("type"));
			
			int boardType = Integer.parseInt(request.getParameter("type"));
			
			// 게시판 목록 현재 페이지를 쿼리스트링에서 얻어오기
			String currentPage = request.getParameter("cp");
			
			if(command.equals("/list.do")) {
				ErrorMsg = "정보 게시판 목록 조회";
				
				PageInfo pInfo = service.getPageInfo(currentPage, boardType);
				
				List<Information> bList = service.selectList(pInfo);
				
				path = "/WEB-INF/views/information/informationList.jsp";
				request.setAttribute("pInfo", pInfo);
				request.setAttribute("bList", bList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			}else if(command.equals("")){
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("ErrorMsg", ErrorMsg + "과정에서 오류가 발생했습니다.");
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
