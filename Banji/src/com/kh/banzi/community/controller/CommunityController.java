package com.kh.banzi.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.banzi.community.model.service.CommunityService;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.community.model.vo.PageInfo;

@WebServlet("/community/*")
public class CommunityController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CommunityController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        String contextPath = request.getContextPath();

        String command = uri.substring((contextPath + "/community").length());

        // forword용 변수 
        String path = null;
        RequestDispatcher view = null;

        String status = null; 
        String msg = null;
        String text = null;
        String errorMsg = null;


        try {
            CommunityService service = new CommunityService();

            String currentPage = request.getParameter("cp");


            if(command.equals("/list.do")) {
                PageInfo pInfo = service.getPageInfo(currentPage);

                List<Community> cList = service.selectList(pInfo);



                path = "/WEB-INF/views/community/communityList.jsp";
                request.setAttribute("pInfo", pInfo);
                request.setAttribute("cList", cList);
                view = request.getRequestDispatcher(path);
                view.forward(request, response);

            }

            // 게시글 상세 조회 Controller 
            else if(command.equals("/view.do")) {
                int boardNo = Integer.parseInt(request.getParameter("no"));

                // 1. 게시글 조회
                Community community = service.selectCommunity(boardNo);

                // 2. 게시글 조회 성공 시 이미지 조회
                if(community != null) {
                    path = "/WEB-INF/views/community/communityView.jsp";

                    request.setAttribute("community", community);
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

        }catch(Exception e) {
            e.printStackTrace();
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
