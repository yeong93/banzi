package com.kh.banzi.index;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.banzi.common.Attachment;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.index.model.service.indexService;
import com.kh.banzi.information.model.vo.Information;
import com.kh.banzi.qna.model.vo.Qna;
import com.kh.banzi.review.model.service.ReviewService;
import com.kh.banzi.review.model.vo.Review;


@WebServlet("/main/*")
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/main").length());
		
		String path = null;
		RequestDispatcher view = null;
		
		String status = null;
		String msg = null;
		String text = null;
		String errorMsg = null;
		
		try {
			indexService indexService = new indexService();
			
			// 정보게시판
			if(command.equals("/selectInfo.do")) {
				// 제목,내용 얻어옴
				List<Information> iList = indexService.selectInfoList();
				
				// info 이미지 얻어오기 
				List<Attachment> fList = indexService.selectInfoFileList();
				
				Map<String, Object> map = new HashMap<>();
				map.put("iList", iList);
				map.put("fList", fList);
				
				Gson gson = new Gson();
				gson.toJson(map, response.getWriter());
			}
			
			else if(command.equals("/selectInfo2.do")) {
				// 제목,내용 얻어옴
				List<Information> iList = indexService.selectInfoListTwo();
				
				// info 이미지 얻어오기 
				List<Attachment> fList = indexService.selectInfoFileListTwo();
				
				Map<String, Object> map = new HashMap<>();
				map.put("iList", iList);
				map.put("fList", fList);
				
				Gson gson = new Gson();
				gson.toJson(map, response.getWriter());
				
			}
			
			else if(command.equals("/selectInfo3.do")) {
				// 제목,내용 얻어옴
				List<Information> iList = indexService.selectInfoListThree();
				
				// info 이미지 얻어오기 
				List<Attachment> fList = indexService.selectInfoFileListThree();
				
				Map<String, Object> map = new HashMap<>();
				map.put("iList", iList);
				map.put("fList", fList);
				
				Gson gson = new Gson();
				gson.toJson(map, response.getWriter());
				
			}
			
			else if(command.equals("/selectInfo4.do")) {
				// 제목,내용 얻어옴
				List<Information> iList = indexService.selectInfoListFour();
				
				// info 이미지 얻어오기 
				List<Attachment> fList = indexService.selectInfoFileListFour();
				
				Map<String, Object> map = new HashMap<>();
				map.put("iList", iList);
				map.put("fList", fList);
				
				Gson gson = new Gson();
				gson.toJson(map, response.getWriter());
				
			}
			
			
			
		
			
		}catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/index.jsp";
			request.setAttribute("errorMsg", errorMsg + " 과정에서 오류가 발생했습니다.");
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
