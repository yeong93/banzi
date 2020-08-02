<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.kh.banzi.qna.model.vo.Qna"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.banzi.common.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");
  List<Qna> qList = (List<Qna>)request.getAttribute("qList");

  
 int currentPage = pInfo.getCurrentPage();
 int listCount = pInfo.getListCount();
 int maxPage = pInfo.getMaxPage();
 int startPage = pInfo.getStartPage();
 int endPage = pInfo.getEndPage();
 int boardType = pInfo.getBoardType();
 
 int prev = (currentPage-1)/10*10;   // < 버튼 
 
 int next = (currentPage+9)/10*10+1; // > 버튼 
%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>게시판</title>
   <style>
       h1{
        font-family: 'Roboto', sans-serif;
        text-align:center;
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
        }
        table td{
          font-weight:400;
        }
        th:first-of-type{
        width:30%;
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
  </style>
  
</head>
<body>
    <%@ include file="../common/header.jsp"%>
  <div class="container">

    <div class="container">
      <h1>Q N A</h1>
          <div class="my">
              <table class="table table-hover table-striped" id="list-table">
                   <thead>
                      <tr>
                          <th>제목</th>
                          <th>작성자</th>
                          <th>작성일</th>
                          <th style="padding-right:0px">답변</th>
                      </tr>
                  </thead> 
                  <tbody>
                  <%for(Qna q : qList){ %>
                    <tr id="<%=q.getBoardNo() %>">
                      <td class="boardTitle">
                      <%=q.getTitle() %>
                      </td>
                      <td><%=q.getRegWriter() %></td>
                      <td><%=q.getRegDate() %>
                      <!-- <button type="botton" class="btn btn-secondary float-right">미답변</button> -->
                      <%if (q.getReplyCount() > 0) {%>
                      <td><div class="reply ok">답변완료</div>
                      <%} else{ %>
                      <td><div class="reply no">미답변</div></td>
                      <%} %>
                  <%} %>
                  </tbody>
              </table>
          </div>
  
          <hr>
          
          <hr>
          
          <%-- 로그인이 되어있는 경우 --%>
          <% if(loginUser != null) {%>
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
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel"></h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div id="content" class="modal-body" style="word-break:break-all;">
			        ...
			      </div>
			      <div id="reply" class="modal-body">
			       <hr>
			       댓글
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        <button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
			      </div>
			    </div>
			  </div>
			</div>
	    <%@ include file="../common/footer.jsp"%>
	  </div>
  
  <script>
    //------------------------------------------------------------------------------------------------------------
    // 게시글 상세보기 기능 (jquery를 통해 작업)
        // 게시글 상세보기 기능 (jquery를 통해 작업)
    
    //------------------------------------------------------------------------------------------------------------
    // 검색
    
    
    // -- QNA글 보기
    $(document).on("click","#list-table td",function(e){
        var boardNo = $(this).parent().attr("id");
    	$.ajax({
    		url : "<%=request.getContextPath()%>/qna/view.do?cp=<%=currentPage%>&no="+boardNo,
    	  type : "GET",
    	  dataType : "JSON",
    	  success : function(map){
    		  console.log(map);
    		  console.log(map.rList.length);
    		  console.log(map.fList.length);
    		  $("#reply").text("");
    		  $("#exampleModalLabel").text(map.qna.title);
    		  $("#content").text(map.qna.content);
    		  if(map.fList.length != 0){
    			  $pic = $("")
    		  }
     		  if(map.rList.length != 0){
 	           $hr = $("<hr>");
             $p1 = $("<p>").addClass("p1").text("댓글");
             $("#reply").append($hr,$p1);
     			  for(var i = 0; i < map.rList.length; i++){
     				  if(i > 0){
     					  $hr2 = $("<hr>");     					  
  		          $("#reply").append($hr2);
     				  }
    			   $p1 = $("<p>").text("댓글");
    			   $div = $("<div>").addClass("nick_box").text(map.rList[i].regWriter);
    			   $p2 = $("<p>").addClass("reply_content").text(map.rList[i].content);
    			   $("#reply").append($div, $p2);
     			  }
    		  }
    	  }, error : function(){
    		  console.log("ajax 통신 실패");
    	  }
    		
    	});
    });
    
    $("#list-table td").on("click",function(e){
        e.preventDefault();
        $('#exampleModal').modal("show");
    })
    
  </script>
  
  
  
</body>
</html>