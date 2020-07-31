<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String type = request.getParameter("type"); 
	String cp = request.getParameter("cp");
	
	SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy.MM.dd", Locale.KOREA );
	Date currentTime = new Date ();
	String today = mSimpleDateFormat.format(currentTime);
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 글작성</title>
<!-- CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/event.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/mypage.css">

</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<section id="content">

		<!-- -------------------------------- 메인 -------------------------------- -->
		<div id="container" class="event">
			<h1 class="type1">이벤트 게시글 작성</h1>
			
			<ul class="eventList mx-auto">
				
				<form action="<%=request.getContextPath()%>/event/insert.do?type=1&cp=<%=cp%>" method="post" 
				  enctype="multipart/form-data" role="form" onsubmit="return validate();">
				  
				  <table class="mx-auto table">
				  
				  	<tr>
				  		<td><p>제목</p></td>
				  		<td><input type="text" id="title" name="title" size="70"></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>작성자</p></td>
				  		<td><p id="writer"><%=loginUser.getUserName()%></p></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>작성일</p></td>
				  		<td><p class="my-0" id="createDay" name="createDay"><%=today%></p></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>이벤트 시작일</p></td>
				  		<td><input type="datetime-local" id="startDay" name="startDay"></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>이벤트 종료일</p></td>
				  		<td><input type="datetime-local" id="endDay" name="endDay"></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>미리보기</p></td>
				  		<td>
				  			<div class="boardImg" id="imgArea">
									<img id="img" width="250" height="206">
								</div>
				  		</td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>이미지 파일</p></td>
				  		<td>
				  			<div class="form-group" id="fileArea">
							    <input type="file" style="height: 30px" class="form-control-file" id="file" name="name">
							</div>
				  		</td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>내용</p></td>
				  		<td></td>
				  	</tr>
				  	
				  	<tr>

				  		<td colspan="2">
				  			<textarea class="form-control" id="content" name="content"
									rows="10" style="resize: none;"></textarea>
							</td>
				  	</tr>
				  	
				  </table>

				<hr class="mb-4">

				<div class="text-center">
					<button type="submit" class="btn btn-primary btn-warning">글 등록</button>
					<button type="button" class="btn btn-primary btn-warning">목록으로</button>
				</div>

			</form>
				
			</ul>
			
			
			
			
			
		</div>
		<!-- //container -->

	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

</body>
</html>