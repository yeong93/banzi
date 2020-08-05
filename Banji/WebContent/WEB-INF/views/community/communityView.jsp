<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.banzi.common.Attachment"%>
<%@page import="com.kh.banzi.community.model.vo.Community"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<% 
  ArrayList<Attachment> fList = (ArrayList<Attachment>)request.getAttribute("fList");
  Community community = (Community)request.getAttribute("community");
	String cp = request.getParameter("cp");
	String pattern = "yy-MM-dd HH:mm";
	SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>게시글</title>
<style>
	#board-area{ margin-bottom:100px;}
	#board-content{ padding-bottom:150px;}

	.boardImgArea{
		height: 300px;
	}
	*{
	font-family: 'Nanum Gothic', sans-serif;
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
		width: 100%;
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
	.reply_content{
	 width:80%;
	}
	.p1{
	 font-size:1.2em;
	 font-weight:bold;
	}
	.hr{
    border-top:2px solid #868484;
	}
	.rWriter{
	 padding-bottom: 15px;
   font-weight: 600;
   font-size:0.9em;
	}
	.reply_content{
	 margin:20px 0px 25px;
	}
</style>
</head>
<body>
		<%@ include file="../common/header.jsp"%>
	<div class="container">

		<div>

			<div id="board-area">

				<!-- Title -->
				<h3 class="mt-4"><%= community.getTitle() %></h3>

				<!-- Writer -->
				<p class="lead">
					작성자 : <%= community.getRegName() %>
				</p>

				<hr>

				<!-- Date -->
				<p>
					<%= sdf1.format(community.getRegDate()) %>
			 		<span class="float-right">조회수 <%= community.getViews() %></span>
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
				<div id="board-content"><%= community.getContent() %></div>
				<div id="reply-area ">
				  <%if(loginUser != null) {%>
				  <!-- 댓글 작성 부분 -->
				  <div class="replyWrite">
				    <table align="center">
				      <tr>
				        <td id="replyContentArea"><textArea rows="3" id="replyContent"></textArea>
				        </td>
				        <td id="replyBtnArea">
				          <button class="btn btn-primary" id="addReply">댓글<br>등록</button>
				        </td>
				      </tr>
				    </table>
				  </div>
				<%} %>
				  <!-- 댓글 출력 부분 -->
				  <div class ="relply-list">
				  </div>
				</div>


				<hr>
				 
				
				<div>
 					<% if(loginUser != null && (community.getRegName().equals(loginUser.getUserName()))) {%>
			  <button id="deleteBtn" class="btn btn-primary float-right">삭제</button>
					<!-- 삭제 버튼 클릭시 해당 게시글 상태를 'N'으로 바꾸고 목록으로 돌아가기 --> 
					<a href="updateForm.do?cp=<%=cp%>&no=<%=community.getBoardNo()%>" class="btn btn-primary float-right ml-1 mr-1">수정</a>
					<% } %> 
					
					<a href="list.do?cp=<%=cp%>" class="btn btn-primary float-right">목록으로</a>
				</div>
			</div>


			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
	
	<script>
 <%-- 	 $("#deleteBtn").on("click", function(){
		  if(confirm("정말 삭제 하시겠습니까?")){
			  location.href="delete.do?no=<%=community.getBoardNo()%>";
		  }
	 });  --%>
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
 	    $(function(){
 		     selectReplyList(<%=community.getBoardNo()%>);
 		   });
 		   
 	    // 댓글 목록 조회
	   function selectReplyList(boardNo){
	     var url = "<%= request.getContextPath() %>/qna/selectReply.do";
	     $.ajax({
	       url : url,
	       data : {"boardNo" : boardNo},
	       dataType : "JSON",
	       success : function(rList){
	         if(rList.length != 0){
	        	   $(".relply-list").html("");
	             $hr = $("<hr>").addClass("hr");
	             $p1 = $("<p>").addClass("p1").text("댓글");
	             $(".relply-list").append($hr,$p1);
	             for(var i = 0; i < rList.length; i++){
	               if(i > 0){
	            	   $hr2 = $("<hr>");
	                 $(".relply-list").append($hr2); 
	               }
	               $div = $("<div>").addClass("rWriter").html("작성자 : " + rList[i].regWriter +"<br>"+"작성일 : " + rList[i].regDate);
	               $p2 = $("<p>").addClass("reply_content").html(rList[i].content);
	               $(".relply-list").append($div, $p2);
	               if(userNick == rList[i].regWriter){
	            	   var $deleteReply = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("삭제").attr("onclick","showDeleteReply("+rList[i].replyNo+")");
	            	   $(".relply-list").append( $deleteReply);  
	               }
	             }
	           
	         }
	       }, error : function() {
	         console.log("ajax 통신 실패");
	       }
	     });
	   }
 		   
 		   
 		   $("#addReply").on("click",function(){
 			   var content = $("#replyContent").val();
 			   
 			   if(content.trim().length==0){
 				   alert("댓글 작성 후 클릭해 주세요");
			     $("#replyContent").focus();
 			   }else{
 				   var url = "<%=request.getContextPath()%>/community/insertReply.do";
 				   var boardNo = "<%=community.getBoardNo()%>";
 				   var regName = "<%=community.getRegName()%>";
			     $.ajax({
			        url : url,
			        type : "POST",
			        data : {"regName" : regName, "boardNo" : boardNo, "content" : content},
			        
			        success : function(result){
			          alert(result);
			          $("#replyContent").val("");
			          // 삽입된 내용을 화면에서 지움
			          
			          // 갱신된 DB 내용을 다시 조회하여 화면 댓글 목록을 갱신함.
			          selectReplyList(boardNo);
			        }, error : function(){
			          console.log("ajax 통신 실패");
			        }
			      });
 			   }
 		   });
 		   
 		    function showDeleteReply(replyNo){
 		      if(confirm("해당 댓글을 삭제하시겠습니까?")){
 		      /*  var boardNo = $("[type='hidden']").attr("class") */;
 		      var boardNo = "<%=community.getBoardNo()%>";
 		       $.ajax({
 		    	  url : "<%=request.getContextPath()%>/community/deleteReply.do",
 		        data : {"replyNo" : replyNo},
 		        success : function(result){
 		          alert(result);
              selectReplyList(boardNo);
 		        }, error :  function(){
 		          console.log("ajax 통신 실패");
 		        }
 		         
 		       });
 		      }else{
 		    	  return;
 		      }      
 		     };
 		     
 		     // 글삭제
 		    $("#deleteBtn").on("click",function(){
          if(confirm("해당 글을삭제하시겠습니까?")){
        	  var boardNo = "<%=community.getBoardNo()%>";
        	  location.href ="<%=request.getContextPath()%>/community/delete.do?no="+boardNo;
          }else{
        	  return;
          }
 		    });
	</script>
</body>
</html>
