package com.kh.banzi.community.controller;

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

import com.kh.banzi.common.Attachment;
import com.kh.banzi.common.MyFileRenamePolicy;
import com.kh.banzi.common.PageInfo;
import com.kh.banzi.community.model.service.CommunityService;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.community.model.vo.Reply;
import com.kh.banzi.user.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

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

                List<Attachment> fList = service.selectFileList(pInfo);


                path = "/WEB-INF/views/community/communityList.jsp";
                request.setAttribute("pInfo", pInfo);
                request.setAttribute("cList", cList);
                request.setAttribute("fList", fList);
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
                    List<Attachment> fList = service.selectFiles(boardNo, community.getBoardType());

                    if(!fList.isEmpty()) 
                        request.setAttribute("fList", fList);

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
            } else if (command.equals("/insertForm.do")) {
                path = "/WEB-INF/views/community/communityInsert.jsp";
                view = request.getRequestDispatcher(path);
                view.forward(request, response);

            } else if (command.equals("/insert.do")) {
                int maxSize = 1024 * 1024 * 10; // 10MB   

                String root = request.getSession().getServletContext().getRealPath("/");

                String filePath = root + "resources\\uploadImages";


                MultipartRequest mRequest 
                = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());


                String boardTitle = mRequest.getParameter("title");
                String boardContent = mRequest.getParameter("content");
                String categoryName = mRequest.getParameter("category");

                String userId = ((User)request.getSession().getAttribute("loginUser")).getUserId();
                int boardType = Integer.parseInt(request.getParameter("type"));

                Community community = new Community(userId, boardTitle, boardContent, boardType);


                List<Attachment> fList = new ArrayList<Attachment>();

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

                int result = service.insertBoard(community,fList);

                if(result > 0) {
                    status = "success";
                    msg = "게시글 등록 성공";
                    path = "view.do?type="+boardType+"&cp=1&no="+result;
                }else {
                    status = "error";
                    msg = "게시글 등록 실패";
                    path = request.getHeader("referer");

                }
                request.getSession().setAttribute("status", status);
                request.getSession().setAttribute("msg", msg);
                response.sendRedirect(path);

            } else if (command.equals("/delete.do")) {
                int boardNo = Integer.parseInt(request.getParameter("no"));
                int result = service.deleteCommunity(boardNo);

                if (result > 0) {
                    status = "success";
                    msg = "게시글이 삭제되었습니다.";
                    path = "list.do?";
                }else {
                    status = "error";
                    msg = "게시글 삭제 실패";
                    path = request.getHeader("referer");
                }
                request.getSession().setAttribute("status", status);
                request.getSession().setAttribute("msg", msg);
                response.sendRedirect(path);
            } else if (command.equals("/updateForm.do")) {
                int boardNo = Integer.parseInt(request.getParameter("no"));

                Community community = service.updateView(boardNo);
                community.setBoardNo(boardNo);

                if(community != null) {
                    List<Attachment> fList = service.selectFiles(boardNo, 3);

                    if(!fList.isEmpty()) {
                        request.setAttribute("fList", fList);
                    }
                    path =  "/WEB-INF/views/community/communityUpdateForm.jsp";
                    request.setAttribute("community", community);
                    view = request.getRequestDispatcher(path);
                    view.forward(request, response);
                }
            } else if (command.contentEquals("/update.do")) {
                int maxSize = 1024 * 1024 *10; //10Mb
                // 2) 파일 저장 경로
                String root = request.getSession().getServletContext().getRealPath("/");
                
                String filePath = root + "resources\\uploadImages";
                
                MultipartRequest mRequest =
                      new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
             
                int boardNo = Integer.parseInt(mRequest.getParameter("no"));
                String title = mRequest.getParameter("title");
                String content = mRequest.getParameter("content");
                
                Community community = new Community(boardNo, title, content, 3);
                
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
                
                int result = service.updateCommunity(community, fList);
                
                if(result>0) {
                   status = "success";
                   msg = "게시글 수정 성공";
                   path = "view.do?cp=" + currentPage +"&no=" + boardNo;
                }else {
                   status = "error";
                   msg = "게시글 수정 실패";
                   path = request.getHeader("referer");
                }
                
                request.getSession().setAttribute("status", status);
                request.getSession().setAttribute("msg", msg);
                response.sendRedirect(path);
            } else if (command.equals("/insertReply.do")) {
                int boardNo = Integer.parseInt(request.getParameter("boardNo"));
                String regName = request.getParameter("regName");
                String content = request.getParameter("content");
                
                Reply reply = new Reply(regName, content, boardNo);
                
                int result = service.insertReply(reply);
                
                if(result > 0)
                    response.getWriter().print("댓글 삽입 성공");
                else
                    response.getWriter().print("댓글 삽입 실패");
                
            } else if (command.equals("/deleteReply.do")) {
                int replyNo = Integer.parseInt(request.getParameter("replyNo"));
                
                int result = service.deleteReply(replyNo);
                
                if(result > 0)
                    response.getWriter().print("댓글 삭제 성공");
                else
                    response.getWriter().print("댓글 삭제 실패");
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
