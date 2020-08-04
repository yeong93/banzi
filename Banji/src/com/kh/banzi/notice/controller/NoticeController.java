package com.kh.banzi.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.community.model.service.CommunityService;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.notice.model.service.NoticeService;
import com.kh.banzi.notice.model.vo.Notice;
import com.kh.banzi.qna.model.service.QnaService;

@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NoticeController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        String contextPath = request.getContextPath();

        String command = uri.substring((contextPath + "/notice").length());

        // forword용 변수 
        String path = null;
        RequestDispatcher view = null;

        String status = null; 
        String msg = null;
        String text = null;
        String errorMsg = null;
        
        try {
            NoticeService service = new NoticeService();
            String currentPage = request.getParameter("cp");
            if (command.equals("/list.do")) {
                PageInfo pInfo = new QnaService().getPageInfo(currentPage);
                
                List<Notice> nList = service.selectList(pInfo);
                path = "/WEB-INF/views/notice/noticeList.jsp";
                request.setAttribute("pInfo", pInfo);
                request.setAttribute("nList", nList);
                request.getRequestDispatcher(path).forward(request, response);
            } else if (command.equals("/view.do")) {
                int boardNo = Integer.parseInt(request.getParameter("no"));

                // 1. 게시글 조회
                Notice notice = service.selectNotice(boardNo);

                // 2. 게시글 조회 성공 시 이미지 조회
                if(notice != null) {
                    List<Attachment> fList = new CommunityService().selectFiles(boardNo, 5);

                    if(!fList.isEmpty()) 
                        request.setAttribute("fList", fList);

                    path = "/WEB-INF/views/notice/noticeView.jsp";

                    request.setAttribute("notice", notice);
                    view = request.getRequestDispatcher(path);
                    view.forward(request, response);
                }else {
                    status = "error";
                    msg = "게시글 조회 실패";
                    request.getSession().setAttribute("status", status);
                    request.getSession().setAttribute("msg", msg);
                    response.sendRedirect(request.getHeader("referer"));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
