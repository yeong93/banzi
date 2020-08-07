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
 
 int prev = 5 * ( (currentPage - 1) / 5 );   // < 버튼 
 
 int next = 1 + ( (currentPage + 4) / 5 * 5); // > 버튼 
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
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>게시판</title>
   <style>
   @font-face {
  font-family: "yg-jalnan";
  src:
    url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
   
       h1{
        /* font-family: 'Roboto', sans-serif; */
        font-family: "yg-jalnan";
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
         font-size:0.82em;
        }
        #date{
        margin-bottom:20px;
        }
        .reply_content{
         font-weight:550;
         font-family: 'Nanum Gothic', sans-serif;
         font-size:0.95em;
         margin:13px 0px;
        }
        .p1{
         font-size:1.3em;
        }
        #content img{
        max-width:650px;
        margin:25px 0px;
        display:block;
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
.changeColor {
background-color: #81F7D8;
}
  </style>
  
</head>
<body>
    <%@ include file="../common/header.jsp"%>
  <div class="container">

    <div class="container">
      <h1>Q N A</h1>
          <div class="my">
              <table class="table" id="list-table">
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
                      <td id="<%=q.getRegId()%>"><%=q.getRegWriter() %></td>
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
          <% if(loginUser != null ) {%>
          <button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = 'insertForm.do?type=<%=boardType%>';">글쓰기</button>
          <% } %> 
          
          <!-- 페이징바 -->
            <div style="clear:both">
              <ul class ="pagination">
               <% if(currentPage > 5) { %>
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
                    
                    
                    <% if((next <= maxPage)) {%>
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
			        <button type="button" class="btn btn-success" data-dismiss="modal" onclick="ok();">확인</button>
			      </div>
			    </div>
			  </div>
			</div>
	    <%@ include file="../common/footer.jsp"%>
	  </div>
    
  <script>
<%
    User tempUser = (User)session.getAttribute("loginUser");
%>
var loginMemberId;
<% if(tempUser != null){%>
userId = "<%=tempUser.getUserId()%>";
userNick = "<%=tempUser.getUserName()%>";
userGrade = "<%=tempUser.getUserGrade()%>".trim();
<%} else {%>
userNick = "";
userId = "";
userGrade ="";
<%}%>
    
    
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
    
    $("#list-table td").on("click",function(e){
        e.preventDefault();
        $('#exampleModal').modal("show");
    });
    $("#list-table td").click(function(){
      var boardNo = $(this).parent().attr("id");
      reload(boardNo);
    });
    
    function reload(boardNo){
      $.ajax({
        url : "<%=request.getContextPath()%>/qna/view.do?cp=<%=currentPage%>&no="+boardNo,
        type : "GET",
        dataType : "JSON",
        success : function(map){
          $("#reply").text("");
          $("#exampleModalLabel").text(map.qna.title);
          $("#info").attr("class",map.qna.regId).text("작성자 : " + map.qna.regWriter);
          $("#content").html(map.qna.content);
          $("[type='hidden']").attr('class', boardNo);
          if(map.qna.regId == userId){
              $(".bt").remove();
              $(".modal-footer").prepend("<div class ='btn btn-primary bt' data-dismiss='modal' onclick='deleteQna();'>삭제");
              $(".modal-footer").prepend("<div class ='btn btn-primary bt' data-dismiss='modal' onclick='updateQna();'>수정");
            }
          if(map.fList.length != 0){
            var src;
            var flag = true;
            for(var i = 0; i < map.fList.length; i++){
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
             $div = $("<div>").addClass("nick_box").text("작성자 : " + map.rList[i].regWriter);
             $div2 = $("<div>").addClass("nick_box").attr("id","date").text("작성일 : " + map.rList[i].regDate);
             $p2 = $("<p>").addClass("reply_content").html(map.rList[i].content);
             console.log("접속" + userId);
             console.log("접속" + map.rList[i].regId);
             
             if(userId == map.rList[i].regId){
                 var $deleteReply = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("삭제").attr("onclick","showDeleteReply("+map.rList[i].replyNo+");");
                 $("#content").append($div,$div2, $p2, $deleteReply);
              }else{
            	   $("#content").append($div,$div2, $p2);
              }
            }
          }
          if(userGrade == "veterinarian" || userGrade == "animaltrainer" ){
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
   		  location.href="<%=request.getContextPath()%>/qna/updateForm.do?no="+boardNo;

    };
    function deleteQna(){
    	if(confirm("해당 글을 삭제 하시겠습니까?")){
    	 var boardNo = $("[type='hidden']").attr("class");
    	 location.href="<%=request.getContextPath()%>/qna/delete.do?no="+boardNo;
    	}
    }; 
    function ok(){
    	location.reload();
    }
    
    // 댓글 삭제
    function showDeleteReply(replyNo){
   	 if(confirm("해당 댓글을 삭제하시겠습니까?")){
      var boardNo = $("[type='hidden']").attr("class");
    	$.ajax({
    	 url : "<%=request.getContextPath()%>/qna/deleteReply.do",
    	 data : {"replyNo" : replyNo, "boardNo" : boardNo},
    	 success : function(result){
    		 alert(result);
    		 reload(boardNo);
    	 }, error :  function(){
    		 console.log("ajax 통신 실패");
    	 }
    		
    	});
   	 }    	
    };
    
    $(document).keydown(function(event) {
        if ( event.keyCode == 27 || event.which == 27 ) {
        	location.reload();
        }
    });
    
    $(document).ready(function (){
    	changeColor();
    })
    
		function changeColor(){
    	  $('#list-table tr').mouseover(function(){
    		  $(this).addClass('changeColor');
		}).mouseout(function() {
			   $(this).removeClass('changeColor');
		});
		}

  </script>
  
  
  
</body>
</html>