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
            QnaService service = new QnaService();

            String currentPage = request.getParameter("cp");

            if (command.equals("/list.do")) {
                PageInfo pInfo = service.getPageInfo(currentPage);

                List<Qna> qList = service.selectList(pInfo);

                path = "/WEB-INF/views/qna/qnaList.jsp";
                request.setAttribute("pInfo", pInfo);
                request.setAttribute("qList", qList);
                request.getRequestDispatcher(path).forward(request, response);
            } else if (command.equals("/view.do")) {
                int boardNo = Integer.parseInt(request.getParameter("no"));

                Qna qna = service.selectQna(boardNo);

                List<Reply> rList = service.selectReply(boardNo);
                List<Attachment> fList = service.selectFiles(boardNo);
                String userGrade = "";
                if(request.getSession().getAttribute("loginUser") != null) {
                    userGrade = ((User)request.getSession().getAttribute("loginUser")).getUserGrade();
                }
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();

                Map<String, Object> map = new HashMap<>();
                map.put("qna", qna);
                map.put("rList", rList);
                map.put("fList", fList);
                map.put("userGrade", userGrade);

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

                String userId = ((User)request.getSession().getAttribute("loginUser")).getUserId();
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

                int result = service.insertQna(qna,fList);

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
            } else if (command.equals("/insertReply.do")) {

                int boardNo = Integer.parseInt(request.getParameter("boardNo"));
                String regWriter = request.getParameter("userId");
                String content = request.getParameter("replyContent");

                Reply reply = new Reply(regWriter, content, boardNo);
                int result = service.inserReply(reply);

                if (result > 0)
                    response.getWriter().print("댓글 삽입 성공");
                else
                    response.getWriter().print("댓글 삽입 실패");
            } else if (command.equals("/updateForm.do")) {
                int boardNo = Integer.parseInt(request.getParameter("no"));

                Qna qna = service.updateView(boardNo);

                if(qna != null) {
                    List<Attachment> fList = service.selectFiles(boardNo);

                    if(!fList.isEmpty()) {
                        request.setAttribute("fList", fList);
                    }
                    path =  "/WEB-INF/views/qna/qnaUpdateForm.jsp";
                    request.setAttribute("qna", qna);
                    view = request.getRequestDispatcher(path);
                    view.forward(request, response);
                }
            } else if (command.equals("/update.do")) {
                int maxSize = 1024 * 1024 *10; //10Mb

                // 2) 파일 저장 경로
                String root = request.getSession().getServletContext().getRealPath("/");

                String filePath = root + "resources\\uploadImages";

                // MultipartRequest 객체 생성
                MultipartRequest mRequest =
                        new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());

                // 파일을 제외한 글번호, 제목, 내용, 카테고리 얻어오기
                int boardNo = Integer.parseInt(mRequest.getParameter("no"));
                String title = mRequest.getParameter("title");
                String content = mRequest.getParameter("content");
                int boardType = 5;
                Qna qna = new Qna(boardNo, title, content, boardType);

                // 전달받은 파일 정보를 저장할 리스트 생성
                List<Attachment> fList = new ArrayList<Attachment>();

                Enumeration<String> files = mRequest.getFileNames();
                // 폼에서 전송된 파일들의 name 속성값을 반복접근하는 객체

                Attachment temp = null;
                while(files.hasMoreElements()) {
                    String name = files.nextElement();
                    // 현재 접근한 요소의 값(name 속성값) 저장

                    // getFilesystemName 조회하면 변경된이름이 조회됨.
                    if(mRequest.getFilesystemName(name) != null) {
                        temp= new Attachment();

                        temp.setFileOriginName(mRequest.getOriginalFileName(name));
                        temp.setFileChangeName(mRequest.getFilesystemName(name));

                        // name 속성에 따라 fileLevel을 부여
                        // img1 - 0(썸네일) / img2 - 1(1번칸) / img3 - 2(2번칸) / img4 - 3(3번칸)
                        int fileLevel = 0;
                        switch(name) {
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

                int result = service.updateBoard(qna, fList);

                if(result>0) {
                    status = "success";
                    msg = "게시글 수정 성공";
                    path = request.getContextPath()+"/qna/list.do";
                }else {
                    status = "error";
                    msg = "게시글 수정 실패";
                    path = request.getHeader("referer");
                }

                request.getSession().setAttribute("status", status);
                request.getSession().setAttribute("msg", msg);
                response.sendRedirect(path);
            } else if (command.equals("/deleteReply.do")) {
                int replyNo = Integer.parseInt(request.getParameter("replyNo"));
                int boardNo = Integer.parseInt(request.getParameter("boardNo"));

                int result = service.deleteReply(replyNo, boardNo);

                if (result > 0)
                    response.getWriter().print("댓글 삭제 성공");
                else
                    response.getWriter().print("댓글 삭제 실패");
            } else if (command.equals("/selectReply.do")) {
                int boardNo = Integer.parseInt(request.getParameter("boardNo"));
                
                List<Reply> rList = new QnaService().selectReply(boardNo);
                
                Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm:ss").create();
                
                gson.toJson(rList, response.getWriter());
            } else if (command.equals("/delete.do")) {
                int boardNo = Integer.parseInt(request.getParameter("no"));
                System.out.println(boardNo);

                
                int result = service.deleteQna(boardNo);
                
                if(result > 0 ) {
                    status = "success";
                    msg = "게시글이 삭제되었습니다.";
                    path = "list.do";
                    
                 }else {
                    status = "error";
                    msg = "게시글 삭제 실패";
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
