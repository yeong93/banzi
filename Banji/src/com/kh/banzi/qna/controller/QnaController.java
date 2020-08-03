package com.kh.banzi.qna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
import com.kh.banzi.common.MyFileRenamePolicy;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.community.model.vo.Reply;
import com.kh.banzi.qna.model.service.QnaService;
import com.kh.banzi.qna.model.vo.Qna;
import com.kh.banzi.user.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

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
                List<Attachment> fList = qService.selectFiles(boardNo);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
                
                Map<String, Object> map = new HashMap<>();
                map.put("qna", qna);
                map.put("rList", rList);
                map.put("fList", fList);
                gson.toJson(map, response.getWriter());
            } else if (command.equals("/insertForm.do")) {
                path = "/WEB-INF/views/qna/qnaInsert.jsp";
                view = request.getRequestDispatcher(path);
                view.forward(request, response);
            } else if (command.equals("/insert.do")) {
                int maxSize = 1024 * 1024 * 10; // 10MB   

                String root = request.getSession().getServletContext().getRealPath("/");

                String filePath = root + "resources\\uploadImages";


                MultipartRequest mRequest 
                = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());


                String title = mRequest.getParameter("title");
                String content = mRequest.getParameter("content");
                
                String userId = ((User)request.getSession().getAttribute("loginUser")).getUserName();
                int boardType = Integer.parseInt(request.getParameter("type"));
                Qna qna = new Qna(userId, title, content, boardType);
                
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

                int result = qService.insertQna(qna,fList);

                if(result > 0) {
                    status = "success";
                    msg = "게시글 등록 성공";
                    path = request.getContextPath() + "/qna/list.do";
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
