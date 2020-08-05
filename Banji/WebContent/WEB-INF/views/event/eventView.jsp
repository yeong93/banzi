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
	
	String create = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(event.getCreateDay());
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

</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<section id="content">

		<!-- -------------------------------- 메인 -------------------------------- -->
		<div id="container" class="event eventList mx-auto">
			<h1 class="type1">이벤트</h1>
				  
				  <table class="mx-auto table text-center">
				  
				  	<tr>
				  		<td><p>제목</p></td>
				  		<td><%=event.getEventTitle() %></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>작성일</p></td>
				  		<td><%=create %></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>이벤트 시작일</p></td>
				  		<td><%=start %></td>
				  	</tr>
				  	
				  	<tr>
				  		<td><p>이벤트 종료일</p></td>
				  		<td><%=end %></td>
				  	</tr>
				  	
				  	<% if(fList != null){ 
				  		String src = null;

               			for(int i=0; i<2 ; i++) {
               			 for(Attachment at : fList){
               				if(at.getFileLevel() == i){
               					src = request.getContextPath()+"/resources/uploadImages/"+at.getFileChangeName();
               			}}}
				  	%>
					  	<tr>
					  		<td colspan="2">
								<div class="mr-2 boardImg" id="contentImgArea1">
									<img id="contentImg1" src="<%= src%>">
								</div>
					  		</td>
					  	</tr>
				  	
				  	<% } %>
				  	
				  	<%
				  		String content = event.getEventContent();
				  		if(content == null){
				  			content = "";
				  		}
				  	
				  	%>
				  	
				  	<tr>
				  		<td colspan="2">
				  			<div id="event-content"><%=content%></div>
						</td>
				  	</tr>
		
				  </table>

				<hr class="mb-4">

				<div class="text-center">
					<% if(loginUser != null && loginUser.getUserId().equals("master")){ %>
						<button type="submit" class="btn btn-primary btn-warning" id="delBtn"
						onclick="location.href='eventDelete.do?no=<%=event.getEventNo()%>';">글 삭제</button>
		
						<button type="button" class="btn btn-primary btn-warning" id="upBtn"
						onclick="location.href='eventUpdateForm.do?type=<%=type%>&cp=<%=cp%>&no=<%=event.getEventNo()%>';">글 수정</button>
		
					<% } %>
					
					<a href="eventList.do?&type=<%=type%>&cp=<%=cp%>" class="btn btn-primary btn-warning float-right" id="listBtn">목록으로</a>
				</div>
				
				
		</div>
		<!-- //container -->

	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
	<script>
	
		$("#delBtn").on("click",function() {
			if(confirm("정말 삭제하시겠습니까?")){
				location.href="eventDelete.do?type=<%=type%>&no=<%=event.getEventNo()%>";
			}
			
		});
	
	</script>

</body>
</html>