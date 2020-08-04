<%@page import="com.kh.banzi.notice.model.vo.Notice"%>
<%@page import="com.kh.banzi.common.Attachment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<% 
  ArrayList<Attachment> fList = (ArrayList<Attachment>)request.getAttribute("fList");
  Notice notice= (Notice)request.getAttribute("notice");
  String cp = request.getParameter("cp");
  
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<style>
  #board-area{ margin-bottom:100px;}
  #board-content{ padding-bottom:150px;}

  .boardImgArea{
    height: 300px;
  }

  .boardImg{
    width : 100%;
    height: 100%;
    
    max-width : 300px;
    max-height: 300px;
    
    margin : auto;
  }
  
  /* 이미지 화살표 색 조정
  -> fill='%23000' 부분의 000을
     RGB 16진수 값을 작성하여 변경 가능 
   */
  .carousel-control-prev-icon {
    background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E") !important;
  }
  
  .carousel-control-next-icon {
      background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E") !important;
  }
  
  .replyWrite > table{
    width: 90%;
    align: center;
  }
  
  #replyContentArea{ width: 90%; }
  
  #replyContentArea > textarea{
      resize: none;
      width: 100%;
  }
  
  #replyBtnArea{
      width: 100px;
      text-align: center;
  }
  
  .rWriter{ margin-right: 30px;}
  .rDate{
    font-size: 0.7em;
    color : gray;
  }
  
  #replyListArea{
    list-style-type: none;
  }
  .container{
    padding-top:157px;
  }
</style>
</head>
<body>
    <%@ include file="../common/header.jsp"%>
  <div class="container">

    <div>

      <div id="board-area">

        <!-- Title -->
        <h3 class="mt-4"><%= notice.getTitle() %></h3>

        <!-- Writer -->
        <p class="lead">
          작성자 : <%= notice.getRegWriter() %>
        </p>

        <hr>

        <!-- Date -->
        <p>
          <%= notice.getRegDate() %>
          <span class="float-right">조회수 <%= notice.getViews() %></span>
        </p>

        <hr>
               <% if(fList != null){ %>
                <div class="carousel slide m-3" id="carousel-325626">
                    
                    <div class="carousel-inner boardImgArea">
                     <% 
                        String src = null;
                        boolean flag = true;
                        for(int i=0; i<4 ; i++) {
                         for(Attachment at : fList){
                          if(at.getFileLevel() == i){
                           src = request.getContextPath()+"/resources/uploadImages/"+at.getFileChangeName();
                           String imgClass = "carousel-item";
                           
                           if(flag){
                            imgClass += " active";
                            flag=false;
                           }
                     %>     
                          <div class="<%=imgClass%>">
                            <img class="d-block w-100 boardImg" src="<%= src %>"/>
                            <input type="hidden" value=<%=at.getFileNo() %>>
                           </div> 
                        
                      <%  } } } %>
                      
                    </div> 
                    <a class="carousel-control-prev" href="#carousel-325626" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span></a> <a class="carousel-control-next" href="#carousel-325626" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a>
                </div>
                <% } %>       
        

        <!-- Content -->
        <div id="board-content"><%= notice.getContent() %></div>
        

        <hr>
         
        
        <div>
          <% if(loginUser != null && (notice.getRegWriter().equals(loginUser.getUserName()))) {%>
        <button id="deleteBtn" class="btn btn-primary float-right">삭제</button>
          <!-- 삭제 버튼 클릭시 해당 게시글 상태를 'N'으로 바꾸고 목록으로 돌아가기 --> 
          <a href="updateForm.do?cp=<%=cp%>&no=<%=notice.getBoardNo()%>" class="btn btn-primary float-right ml-1 mr-1">수정</a>
          <% } %> 
          
          <a href="list.do?cp=<%=cp%>" class="btn btn-primary float-right">목록으로</a>
        </div>
      </div>


      <%@ include file="../common/footer.jsp"%>
    </div>
  </div>
  
  <script>
   $("#deleteBtn").on("click", function(){
      if(confirm("정말 삭제 하시겠습니까?")){
        location.href="delete.do?no=<%=notice.getBoardNo()%>";
      }
   }); 

  </script>
</body>
</html>
