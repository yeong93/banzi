package com.kh.banzi.notice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
import com.kh.banzi.common.MyFileRenamePolicy;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.community.model.service.CommunityService;
import com.kh.banzi.community.model.vo.Reply;
import com.kh.banzi.notice.model.service.NoticeService;
import com.kh.banzi.notice.model.vo.Notice;
import com.kh.banzi.qna.model.service.QnaService;
import com.kh.banzi.user.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

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
            } else if (command.equals("/insertForm.do")) {
                path = "/WEB-INF/views/notice/noticeInsert.jsp";
                view = request.getRequestDispatcher(path);
                view.forward(request, response);
            } else if (command.equals("/insert.do")) {
                System.out.println("hello");
                int maxSize = 1024 * 1024 * 10; // 10MB   

                String root = request.getSession().getServletContext().getRealPath("/");

                String filePath = root + "resources\\uploadImages";


                MultipartRequest mRequest 
                = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());


                String title = mRequest.getParameter("title");
                String content = mRequest.getParameter("content");
                
                String userId = ((User)request.getSession().getAttribute("loginUser")).getUserName();
                int boardType = Integer.parseInt(request.getParameter("type"));
                Notice notice = new Notice(userId, title, content, boardType);
                
                List<Attachment> fList = new ArrayList<>();
                Enumeration<String> files = mRequest.getFileNames(); 
                Attachment temp = null; // 임시 참조 변수; 
                while(files.hasMoreElements()) {

                    String name = files.nextElement();

                    if(mRequest.getFilesystemName(name) != null) {
                        temp = new Attachment();

                        temp.setFileOriginName(mRequest.getOriginalFileName(name));
                        temp.setFileChangeName(mRequest.getFilesystemName(name));

                        int fileLevel = 0;
                        switch (name) {
                        case "img1" : fileLevel = 0; break;
                        case "img2" : fileLevel = 1; break;
                        case "img3" : fileLevel = 2; break;
                        case "img4" : fileLevel = 3; break;
                        }
                        temp.setFileLevel(fileLevel);

                        temp.setFilePath(filePath);

                        fList.add(temp);

                    }
                }

                int result = service.insertNotice(notice,fList);

                if(result > 0) {
                    status = "success";
                    msg = "게시글 등록 성공";
                    path = request.getContextPath() + "/notice/list.do";
                }else {
                    status = "error";
                    msg = "게시글 등록 실패";
                    path = request.getHeader("referer");
                }
                request.getSession().setAttribute("status", status);
                request.getSession().setAttribute("msg", msg);
                response.sendRedirect(path);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
