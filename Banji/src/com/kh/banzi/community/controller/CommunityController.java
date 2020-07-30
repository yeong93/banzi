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
import com.kh.banzi.community.model.service.CommunityService;
import com.kh.banzi.community.model.vo.Community;
import com.kh.banzi.community.model.vo.PageInfo;
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
            
            CommunityService cService = new CommunityService();
            
            String currentPage = request.getParameter("cp");


            if(command.equals("/list.do")) {
                PageInfo pInfo = cService.getPageInfo(currentPage);

                List<Community> cList = cService.selectList(pInfo);
                
                List<Attachment> fList = cService.selectFileList(pInfo);


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
                Community community = cService.selectCommunity(boardNo);

                // 2. 게시글 조회 성공 시 이미지 조회
                if(community != null) {
                    System.out.println("게시글 regName" + community.getRegName());
                    List<Attachment> fList = cService.selectFiles(boardNo);
                    
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
            }else if(command.equals("/insertForm.do")) {
                path = "/WEB-INF/views/community/communityInsert.jsp";
                view = request.getRequestDispatcher(path);
                view.forward(request, response);
                
            }else if(command.equals("/insert.do")) {
                int maxSize = 1024 * 1024 * 10; // 10MB   
                
                // 1_2. 전송된 파일을 저장할 위치 경로를 지정
                // Servlet Container 경로 추출 -> WebContent 폴더 위치
                String root = request.getSession().getServletContext().getRealPath("/");
                // C:\workspace\5_WebServer\wsp\WebContent
                
                // uploadImages 폴더 위치 추출
                String filePath = root + "resources\\uploadImages";
                
                
                // 2_1. MultipartRequest 객체 생성 
                // -> MultipartRequest 객체가 생성되는 시점에 
                //      전송받은 파일들의 이름이 변경되며, 지정된 경로에 바로 저장이 됨.
                MultipartRequest mRequest 
                   = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
                                     // 기존 요청, 파일저장경로 , 용량제한, 일반텍스트 인코딩, 파일명 변경 개겣
                
                
                // 2_2. 파일 외에 게시판 제목, 내용 , 카테고리, 작성자 아이디 얻어오기
                // enctype이 Multipart인 경우 request.getParameter()를 이용하여 
                // 파라미터를 얻어올 수 없음. -> MultipartRequest.getParameter()를 사용 
                String boardTitle = mRequest.getParameter("title");
                String boardContent = mRequest.getParameter("content");
                String categoryName = mRequest.getParameter("category");
                
                String userId = ((User)request.getSession().getAttribute("loginUser")).getUserName();
                int boardType = Integer.parseInt(request.getParameter("type"));
                
                Community community = new Community(userId, boardTitle, boardContent, boardType);
                      
                
                // 2_3. 파일 정보를 저장할 List 생성 
                List<Attachment> fList = new ArrayList<Attachment>();
                
                // MultipartRequest 에 담겨있는 파일 정보를 하나씩 꺼내옴
                // Enumeration : Iterator 과거 버전 
                   // Iterator : 컬렉션 객체에 저장된 요소 반복 접근자 
                
                
                Enumeration<String> files = mRequest.getFileNames(); 
                      // 전송된 파일의 name 속성값을 모두 반환 - > 파일이 역순으로 반환됨.
                
                Attachment temp = null; // 임시 참조 변수 
                while(files.hasMoreElements()) {
                   //files.hasMereElements() : 다음 반복 접근할 요소가 있으면 true
                   
                   String name = files.nextElement();
                   // files.nextElement() : 다음 요소를 얻어옴.
                   
                   // 요청 객체 중 전달받은 name 속성을 가진 요소 있을 경우 
                   // (파일이 지정된 name 속성을 가진 input태그에 잘 올려져 있는가?)
                   if(mRequest.getFilesystemName(name) != null) {
                      // mRequest.getFilesystemName(name)
                      // -> name 속성값과 일치하는 input 태그 요소의 
                      //    작성된 파일명을 변환한 (rename)한 값을 반환
                      
                      temp = new Attachment();
                      
                      temp.setFileOriginName(mRequest.getOriginalFileName(name));
                      temp.setFileChangeName(mRequest.getFilesystemName(name));
                      
                      // name 속성에 따라 파일 레벨 지정
                      // img1 -> 0 (썸네일), img2 -> 1, img3 -> 2, img4 -> 3
                      int fileLevel = 0;
                      switch (name) {
                      case "img1" : fileLevel = 0; break;
                      case "img2" : fileLevel = 1; break;
                      case "img3" : fileLevel = 2; break;
                      case "img4" : fileLevel = 3; break;
                      }
                   temp.setFileLevel(fileLevel);
                   
                   // 파일이 저장되어 있는 경로를 추가 
                   temp.setFilePath(filePath);
                   
                   fList.add(temp);
                   
                   }
                }
                
                int result = cService.insertBoard(community,fList);
                
                if(result > 0) {
                   status = "success";
                   msg = "게시글 등록 성공";
                   //path = "list.do?type="  + boardType;
                   path = "view.do?type="+boardType+"&cp=1&no="+result;
                }else {
                   status = "error";
                   msg = "게시글 등록 실패";
                   path = request.getHeader("referer");
                   
                }
                request.getSession().setAttribute("status", status);
                request.getSession().setAttribute("msg", msg);
                response.sendRedirect(path);
                
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
