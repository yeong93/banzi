<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.kh.banzi.notice.model.vo.Notice"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.banzi.common.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");
  List<Notice> nList = (List<Notice>)request.getAttribute("nList");

  
 int currentPage = pInfo.getCurrentPage();
 int listCount = pInfo.getListCount();
 int maxPage = pInfo.getMaxPage();
 int startPage = pInfo.getStartPage();
 int endPage = pInfo.getEndPage();
 int boardType = pInfo.getBoardType();
 
 int prev = (currentPage-1)/10*10;   // < 버튼 
 
 int next = (currentPage+9)/10*10+1; // > 버튼 
 String pattern = "yy-MM-dd HH:mm";
 String pattern2 = "HH:mm";
 Calendar today = Calendar.getInstance();
 String year = today.get(Calendar.YEAR)+"";
 String month = String.format("%2s", "0"+(today.get(Calendar.MONDAY)+1)+"");
 String day = String.format("%2s", "0"+today.get(Calendar.DATE)+"");
 SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
 SimpleDateFormat sdf2 = new SimpleDateFormat(pattern2);
%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>공지사항</title>
   <style>
       h1{
        font-family: 'Roboto', sans-serif;
        text-align:center;
        padding-bottom:25px;
       }
       *{
         font-family: "InfinitySans-RegularA1";
       }
      .pagination {
            justify-content: center;
        }
        #searchForm{
            position: relative;
        }

        #searchForm>*{
            top : 0;
        }
        .boardTitle > img{
          width: 50px;
          height: 50px;
          
        }
        table *{
          text-align : center;
        }
        .container{
          padding-top:100px;
        } 
        table th{
          background-color:#FFBA00;
          font-weight:bold;
          font-size:1.15em;
          
        }
        table td{
          font-weight:400;
        }
        th:first-of-type{
        width:15%;
        }
        th:nth-of-type(2){
        width:40%;
        }
        th:nth-of-type(4){
        width:15%;
        }
        .reply{
          width:100px;
          color:white;
          border-radius: 5px;
          color:white;
          float:right;
          height:40px;
          line-height:40px;
        }
        .no{
         background-color: #848484;
        }
        .ok{
         background-color: #FFBA00;
         color:black;
        }
        .nick_box{
         font-weight:bold;
         margin-bottom:10px;
        }
        .reply_content{
         font-weight:200;
         font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,"Noto Sans",sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji";
        }
        .p1{
         font-size:1.3em;
        }
        #content img{
        width:100%;
        heught:auto;
        }
        
        <!-- btn -->
        .replyWrite>table {
  width: 90%;
  margin-top : 100px;
}

#replyContentArea {
  width: 90%;
}

#replyContentArea>textarea {
  resize: none;
  width: 100%;
  text-align:left;
}

#replyBtnArea {
  width: 100px;
  text-align: center;
}

.rWriter {
  display : inline-block;
  margin-right: 30px;
  vertical-align: top;
}

.rDate {
  display : inline-block;
  font-size: 0.5em;
  color: gray;
}

#replyListArea {
  list-style-type: none;
}

.rContent, .btnArea{
  display: inline-block;
  box-sizing: border-box;
}

.rContent {
  height: 100%;
  width : 84.5%;
}

.btnArea {
  height: 100%;
  width : 15%;
  text-align: right;
}
.replyWrite{
padding-top:40px;
}
#info{
height:50px;
padding:15px;
border-bottom : 2px solid #a6a6a9;
}
.date{
float:right;
}
  </style>
  
</head>
<body>
    <%@ include file="../common/header.jsp"%>
  <div class="container">

    <div class="container">
      <h1>N O T I C E</h1>
          <div class="my">
              <table class="table table-hover table-striped" id="list-table">
                   <thead>
                      <tr>
                          <th>작성자</th>
                          <th>제목</th>
                          <th>작성일</th>
                          <th>조회수</th>
                      </tr>
                  </thead> 
                  <tbody>
                  <%for(Notice n : nList){ %>
                    <tr id="<%=n.getBoardNo() %>">
                      <td><%=n.getRegWriter() %></td>
                      <td class="boardTitle">
                      <%=n.getTitle() %>
                      </td>
                      <%String[] str = (n.getRegDate()+"").substring(0, 10).split("-");%>
                      <%if(str[0].equals(year)&&str[1].equals(month)&&str[2].equals(day)){ %>
                        <td><%=sdf2.format(n.getRegDate())%></td> 
                        <%} else{ %>
                        <td><%=sdf1.format(n.getRegDate())%></td>
                        <%} %>
                        <td><%= n.getViews()%></td>
                        </tr>
                  <%} %>
                  </tbody>
              </table>
          </div>
  
          <hr>
          
          <hr>
          
          <% if(loginUser != null && (loginUser.getUserGrade().trim().equals("master") || loginUser.getUserGrade().trim().equals("editor"))) {%>
          <button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = 'insertForm.do?type=<%=boardType%>';">글쓰기</button>
          <% } %> 
          
          <!-- 페이징바 -->
            <div style="clear:both">
              <ul class ="pagination">
               <% if(currentPage > 10) { %>
                    <!--  맨 처음 페이지로 이동[<<] -->
                    <li>
                       <a class="page-link" href="<%=request.getContextPath()%>/qna/list.do?&cp=1">&lt;&lt;</a>
                    </li>
                 
                    <!--  이전 순번의 페이징 바로 이동[<] -->
                    <li>
                       <a class = "page-link"
                       href = "<%=request.getContextPath()%>/qna/list.do?cp=<%=prev%>">&lt;</a>
                    </li>
                    <%}%>
                    <!--  10개의 페이지 목록 -->
                    <% for(int p = startPage; p<=endPage; p++) {%>
                       
                       <%if (p== currentPage) {%>
                       
                    <li><a class="page-link"><%=p%></a></li>
                    
                    <%} else{%>
                    
                    <li>
                       <a class="page-link" href="<%=request.getContextPath()%>/qna/list.do?cp=<%=p%>"><%=p %></a>
                    
                    <%} %>
                    
                    <%} %>
                    
                    
                    <% if((next < maxPage)) {%>
                       <!-- 다음 페이지[>] -->
                    
                    <li>
                       <a class="page-link" href="<%=request.getContextPath()%>/qna/list.do?cp=<%=next%>">&gt;</a>
                    </li>
                    
                    <!--  마지막 페이지로 이동[>>] -->
                    
                    <li>
                       <a class="page-link" href="<%=request.getContextPath()%>/qna/list.do?cp=<%=maxPage%>">&gt;&gt;</a>
                    </li>
                    
                    <%} %>
                    
              </ul>
           </div>
          
          <!-- 페이징바 -->
          
          
          
          <!-- 검색 -->
          <div>
              <form action="search" method="GET" class="text-center" id="searchForm">
                  <select name="searchKey" class="form-control" style="width:100px; display: inline-block;">
                      <!-- <option value="title" selected>글제목</option> -->
                      <option value="title">글제목</option>
                      <option value="content">내용</option>
                      <option value="titcont">제목+내용</option>
                  </select>
                  <input type="text" name="searchValue" class="form-control" style="width:25%; display: inline-block;">
                  <button class="form-control btn btn-primary" style="width:100px; display: inline-block;">검색</button>
              </form>
              
          </div>
      </div>
      <!-- Modal -->
      <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-scrollable">
          <div class="modal-content">
            <div class="modal-header" style="border-bottom : 2px solid #a6a6a9;">
              <h5 class="modal-title" id="exampleModalLabel"></h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div id="info">
            </div>
            <div id="content" class="modal-body" style="word-break:break-all;">
              ...
            </div>
            <input type="hidden">
            <div id="reply" class="modal-body">
             <hr>
             댓글
            </div>
            <div class="modal-footer">
              <button type="button" id="close" class="btn btn-secondary" data-dismiss="modal" onclick="ok();">Close</button>
              <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="ok();">확인</button>
           </div>
          </div>
        </div>
      </div>
      <div>
      </div>
      <%@ include file="../common/footer.jsp"%>
    </div>
	
	<script>
    //------------------------------------------------------------------------------------------------------------
    // 게시글 상세보기 기능 (jquery를 통해 작업)
     $("#list-table td").on("click", function(){
         var boardNo = $(this).parent().attr("id");
         location.href = "<%=request.getContextPath()%>/notice/view.do?cp=<%=currentPage%>&no="+boardNo;
      }).on("mouseenter",function(){
         $(this).parent().css("cursor","pointer")
      })
      ; 
    
    //------------------------------------------------------------------------------------------------------------
    // 검색
	</script>
	
	
	
</body>
</html>
