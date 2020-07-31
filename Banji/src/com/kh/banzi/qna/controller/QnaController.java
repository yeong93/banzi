package com.kh.banzi.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.community.model.vo.Reply;
import com.kh.banzi.qna.model.service.QnaService;
import com.kh.banzi.qna.model.vo.Qna;

@WebServlet("/qna/*")
public class QnaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QnaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        String contextPath = request.getContextPath();

        String command = uri.substring((contextPath + "/qna").length());

        // forword용 변수 
        String path = null;
        RequestDispatcher view = null;

        String status = null; 
        String msg = null;
        String text = null;
        String errorMsg = null;
        try {
            QnaService qService = new QnaService();
            
            String currentPage = request.getParameter("cp");
            
            if (command.equals("/list.do")) {
                PageInfo pInfo = qService.getPageInfo(currentPage);
                
                List<Qna> qList = qService.selectList(pInfo);
                
                path = "/WEB-INF/views/qna/qnaList.jsp";
                request.setAttribute("pInfo", pInfo);
                request.setAttribute("qList", qList);
                request.getRequestDispatcher(path).forward(request, response);
            } else if (command.equals("/view.do")) {
                int boardNo = Integer.parseInt(request.getParameter("no"));
                
                Qna qna = qService.selectQna(boardNo);
                List<Reply> rList = qService.selectReply(boardNo);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
                gson.toJson(qna, response.getWriter());
            } else if (command.equals("/insertForm.do")) {
                path = "/WEB-INF/views/qna/qnaInsert.jsp";
                view = request.getRequestDispatcher(path);
                view.forward(request, response);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
