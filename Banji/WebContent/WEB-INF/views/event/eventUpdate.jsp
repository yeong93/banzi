<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.banzi.common.Attachment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.banzi.event.model.vo.Event"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Event event = (Event)request.getAttribute("event");
	ArrayList<Attachment> fList = (ArrayList<Attachment>)request.getAttribute("fList");
	String cp = request.getParameter("cp");
	String type = request.getParameter("type");
	
	Date currentTime = new Date();
	String modify = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(currentTime);
	String start = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(event.getStartDay());
	String end = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(event.getEndDay());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event</title>

<!-- CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/event.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/mypage.css">
<style>
	.boardImg{width: 374px !important; height: 308px !important;}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<section id="content">

		<!-- -------------------------------- 메인 -------------------------------- -->
		<div id="container" class="event">
			
				<form class="eventList mx-auto"
				  action="<%=request.getContextPath()%>/event/eventUpdate.do?type=<%=type%>&cp=<%=cp%>&no=<%=event.getEventNo()%>" method="post" 
				  enctype="multipart/form-data" role="form" onsubmit="return validate();">
				  
				  <table class="mx-auto table">
				  
				  	<tr>
				  		<td><p>제목</p></td>
				  		<td><input type="text" id="title" name="title" size="70" value="<%=event.getEventTitle() %>"></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>작성자</p></td>
				  		<td><%=loginUser.getUserName()%></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>수정일</p></td>
				  		<td><%=modify%></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>이벤트 시작일</p></td>
				  		<td>변경전: <%=start%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;변경후: <input type="datetime-local" id="startDay" name="startDay"></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>이벤트 종료일</p></td>
				  		<td>변경전: <%=end%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;변경후: <input type="datetime-local" id="endDay" name="endDay"></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>썸네일 이미지</p></td>
				  		<td>
				  			<div class="boardImg" id="titleImgArea">
								<img id="titleImg" width="374" height="308">
							</div>
				  		</td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>메인 이미지</p></td>
				  		<td>
							<div class="mr-2 boardImg" id="contentImgArea1">
								<img id="contentImg1" width="374" height="308">
							</div>
				  		</td>
				  	</tr>

		  			<div id="fileArea">
						썸네일 이미지 : <input type="file" id="img1" name="img1" onchange="LoadImg(this,1)"> 
						메인 이미지 : <input type="file" id="img2" name="img2" onchange="LoadImg(this,2)"> 
					</div>
				  	
				  	<tr>
				  		<td><p>내용</p></td>
				  		<td colspan="2">
				  			<textarea class="form-control" id="content" name="content" rows="10" style="resize: none;"
				  			val="<%=event.getEventContent()%>"></textarea>
						</td>
				  	</tr>
		
				  </table>

				<hr class="mb-4">

				<div class="text-center">
					
					<button type="submit" class="btn btn-primary btn-warning" id="upBtn">글 수정</button>
					
					<a href="eventList.do?type=<%=type%>&cp=<%=cp%>" class="btn btn-primary btn-warning float-right" id="listBtn">목록으로</a>
				</div>
				
			</form>
			
		</div>
		<!-- //container -->

	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
	<script>
		function validate() {
			
			if ($("#title").val().trim().length == 0) {
				swal({
				    icon: "info", 
				    title: "제목을 입력해 주세요."
				});
				$("#title").focus();
				return false;
			}
			
			if ($("#startDay").val().trim().length == 0) {
				swal({
				    icon: "info", 
				    title: "이벤트 시작일을 입력해 주세요."
				});
				$("#startDay").focus();
				return false;
			}
			
			if ($("#endDay").val().trim().length == 0) {
				swal({
				    icon: "info", 
				    title: "이벤트 종료일을 입력해 주세요."
				});
				$("#endDay").focus();
				return false;
			}
			
			if ($("#endDay").val() <= $("#startDay").val()) {
				swal({
				    icon: "warning", 
				    title: "종료일을 다시 선택해주세요.",
				    text: "이벤트 종료일은 이벤트 시작일보다 느려야 합니다."
				});
				$("#endDay").focus();
				return false;
			}
		}
		
		$(function(){
			
			<% 
				String src = null;
				if(fList != null){
					for(Attachment at : fList){
						src = request.getContextPath()
								+"/resources/uploadImages/"
								+at.getFileChangeName();
				%>
						var imgId;
						switch (<%=at.getFileLevel()%>) {
					    case 0: imgId = "#titleImg"; break;
					    case 1: imgId = "#contentImg1"; break;
				 		}
						
						if(imgId != undefined){
							$(imgId).attr("src", "<%=src%>")
						}
			<% 	} 
				} %>
			
		});
		
		$(function () {
	       $("#fileArea").hide(); 

	      $("#titleImgArea").click(function () {
	        $("#img1").click(); 
	      });
	      $("#contentImgArea1").click(function () {
	        $("#img2").click(); 
	      });
	      
	      $(".boardImg").on("click",function(){
	    	  var index =  $(this).children("img").prop("id") + 1;
	        console.log(index);
	        $("#img" + index).click();
	      });
	      
	    });
		

		function LoadImg(value, num) {
			if (value.files && value.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					switch (num) {
					case 1:
						$("#titleImg").attr("src", e.target.result);
						break;
					case 2:
						$("#contentImg1").attr("src", e.target.result);
						break;
					}
				}
				reader.readAsDataURL(value.files[0]);
			}
		}
	</script>

</body>
</html>