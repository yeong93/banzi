<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
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
      <h1>Q N A</h1>
          <div class="my">
              <table class="table table-hover table-striped" id="list-table">
                   <thead>
                      <tr>
                          <th>작성자</th>
                          <th>제목</th>
                          <th>작성일</th>
                          <th style="padding-right:0px">답변</th>
                      </tr>
                  </thead> 
                  <tbody>
                  <%for(Qna q : qList){ %>
                    <tr id="<%=q.getBoardNo() %>" class="mouse">
                      <td><%=q.getRegWriter() %></td>
                      <td class="boardTitle">
                      <%=q.getTitle() %>
                      </td>
                      <%String[] str = (q.getRegDate()+"").substring(0, 10).split("-");%>
                      <%if(str[0].equals(year)&&str[1].equals(month)&&str[2].equals(day)){ %>
                        <td><%=sdf2.format(q.getRegDate())%></td> 
                        <%} else{ %>
                        <td><%=sdf1.format(q.getRegDate())%></td>
                        <%} %>
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
          <% if(loginUser != null && loginUser.getUserGrade().equals("master") && loginUser.getUserGrade().equals("editor")) {%>
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
<!--               <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="updateQna();">수정</button>
 -->			      </div>
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
<%
    User tempUser = (User)session.getAttribute("loginUser");
%>
var loginMemberId;
<% if(tempUser != null){%>
userId = "<%=tempUser.getUserId()%>";
userNick = "<%=tempUser.getUserName()%>";
<%} else {%>
userNick = "";
<%}%>
    // -- QNA글 보기
    $(document).on("click","#list-table td",function(e){
        var boardNo = $(this).parent().attr("id");
    	$.ajax({
    		url : "<%=request.getContextPath()%>/qna/view.do?cp=<%=currentPage%>&no="+boardNo,
    	  type : "GET",
    	  dataType : "JSON",
    	  success : function(map){
    		  $("#reply").text("");
    		  $("#exampleModalLabel").text(map.qna.title);
    		  $("#info").attr("class",map.qna.regWriter).text("작성자 : " + map.qna.regWriter);
    		  $("#info").append("<span class='date'> 작성일 :" + map.qna.regDate);
    		  $("#content").html(map.qna.content);
    		  $("[type='hidden']").attr('class', boardNo);
    		  if(map.qna.regWriter == userNick){
    			  $("#close").after("<div class ='btn btn-primary' data-dismiss='modal' onclick='updateQna();'>수정");
    		  }
    		  if(map.fList.length != 0){
    			  var src;
    			  var flag = true;
    			  for(var i = 0; i < map.fList.length; i++){
 						  src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[i].fileChangeName;
 						  $("#content").append("<img src="+src+">");
 						  console.log(src);
 						  src = "";
    			  }
    			  
    		  }
     		  if(map.rList.length != 0){
 	           $hr = $("<hr>");
             $p1 = $("<p>").addClass("p1").text("댓글");
             $("#content").append($hr,$p1);
     			  for(var i = 0; i < map.rList.length; i++){
     				  if(i > 0){
     					  $hr2 = $("<hr>");     					  
  		          $("#content").append($hr2);
     				  }
    			   $p1 = $("<p>").text("댓글");
    			   $div = $("<div>").addClass("nick_box").text(map.rList[i].regWriter);
    			   $p2 = $("<p>").addClass("reply_content").text(map.rList[i].content);
             $("#content").append($div, $p2);
    			   if(userNick != "" && userNick == map.rList[i].regWriter){
 			         var $showUpdate = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("수정").attr("onclick","showUpdateReply(this, "+map.rList[i].replyNo+")");
 			         var $deleteReply = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("삭제").attr("onclick","showDeleteReply("+map.rList[i].replyNo+")");
 			         $("#content").append($showUpdate, $deleteReply);
    			   }
     			  }
    		  }
     		  if(map.userGrade != "user" && map.userGrade != ""){
     			  $div = $("<div>").addClass("replyWrite");
     			  $table = $("<table align='center'>");
     			  $tr =$("<tr>");
     			  $td = $("<td id=replyContentArea>")
     			  $textArea = $("<textArea rows='4' id='replyContent'>");
     			  $td2 = $("<td id='replyBtnArea'>");
     			  $btn = $("<button class='btn btn-primary' id='addReply'>").attr("onclick", "insert();").html("답변<br>등록");
     			  $div.append($table.append($tr.append($td.append($textArea), $td2.append($btn))));
     			  $("#content").append($div);
     		  }
    	  }, error : function(){
    		  console.log("ajax 통신 실패");
    	  }
    		
    	});
    });
    
    $("#list-table td").on("click",function(e){
        e.preventDefault();
        $('#exampleModal').modal("show");
    });
    
    function insert(){
    	var replyContent = $("#replyContent").val();
    	if(replyContent.trim().length == 0){
    		alert("답변 작성 후 클릭해 주세요");
    	  $("#replyContent").focus();
    	}else{
    		url = "<%=request.getContextPath()%>/qna/insertReply.do";
    		var boardNo = $("[type='hidden']").attr("class");
    		
    		$.ajax({
    			url : url,
    			type : "POST",
    			data : {"userId" : userId, "boardNo" : boardNo , "replyContent" : replyContent},
    			
    			success : function(result){
    				alert(result);
		        $("#replyContent").val("");
		        reload(boardNo);
    			}, error : function(){
    				console.log("ajax 통신 실패");
    			}
    		}); 		
    	}
    };
    
    function reload(boardNo){
      $.ajax({
        url : "<%=request.getContextPath()%>/qna/view.do?cp=<%=currentPage%>&no="+boardNo,
        type : "GET",
        dataType : "JSON",
        success : function(map){
          $("#reply").text("");
          $("#exampleModalLabel").text(map.qna.title);
          $("#content").html(map.qna.content);
          $("[type='hidden']").attr('class', boardNo);
          if(map.fList.length != 0){
            var src;
            var flag = true;
            for(var i = 0; i < map.fList.length; i++){
              console.log(i);
              console.log(map.fList.fileLevel);
              console.log(map.fList[i].fileChangeName);
              src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[i].fileChangeName;
              $("#content").append("<img src="+src+">");
            }
            
          }
          if(map.rList.length != 0){
             $hr = $("<hr>");
             $p1 = $("<p>").addClass("p1").text("댓글");
             $("#content").append($hr,$p1);
            for(var i = 0; i < map.rList.length; i++){
              if(i > 0){
                $hr2 = $("<hr>");                 
                $("#content").append($hr2);
              }
             $p1 = $("<p>").text("댓글");
             $div = $("<div>").addClass("nick_box").text(map.rList[i].regWriter);
             $p2 = $("<p>").addClass("reply_content").text(map.rList[i].content);
             if(userNick == map.rList[i].regWriter){
                 var $showUpdate = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("수정").attr("onclick","showUpdateReply(this, "+map.rList[i].replyNo+")");
                 var $deleteReply = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("삭제").attr("onclick","showDeleteReply("+map.rList[i].replyNo+")");
                 $("#content").append($div, $p2, $showUpdate, $deleteReply);
              }else{
            	   $("#content").append($div, $p2);
              }
            }
          }
          if(map.userGrade != "user" && map.userGrade != ""){
            $div = $("<div>").addClass("replyWrite");
            $table = $("<table align='center'>");
            $tr =$("<tr>");
            $td = $("<td id=replyContentArea>")
            $textArea = $("<textArea rows='4' id='replyContent'>");
            $td2 = $("<td id='replyBtnArea'>");
            $btn = $("<button class='btn btn-primary' id='addReply'>").attr("onclick", "insert();").html("답변<br>등록");
            $div.append($table.append($tr.append($td.append($textArea), $td2.append($btn))));
            $("#content").append($div);
          }
        }, error : function(){
          console.log("ajax 통신 실패");
        }
        
      });
    };
    function updateQna(){
    	var boardNo = $("[type='hidden']").attr("class");
    	console.log($("#info").attr("class"));
     	if(userNick == $("#info").attr("class")){
     		  location.href="<%=request.getContextPath()%>/qna/updateForm.do?no="+boardNo;
     	}else{
     		alert("작성자만 수정 가능합니다.");
     		return;
     	}
    };
    function ok(){
    	location.reload();
    }
    
    // 댓글 수정
    function showDeleteReply(replyNo){
   	 if(confirm("해당 댓글을 삭제하시겠습니까?")){
      var boardNo = $("[type='hidden']").attr("class");
      console.log("댓"+replyNo);
    	$.ajax({
    	 url : "<%=request.getContextPath()%>/qna/deleteReply.do",
    	 data : {"replyNo" : replyNo},
    	 success : function(result){
    		 alert(result);
    		 reload(boardNo);
    	 }, error :  function(){
    		 console.log("ajax 통신 실패");
    	 }
    		
    	});
   	 }    	
    };
  </script>
  
  
  
</body>
</html>