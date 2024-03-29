<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.kh.banzi.common.Attachment"%>
<%@page import="com.kh.banzi.event.model.vo.Event"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.banzi.event.model.vo.PageInfo"%>

<%
	PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");
	List<Event> eList = (List<Event>)request.getAttribute("eList");
	List<Attachment> fList = (List<Attachment>) request.getAttribute("fList");
	String eventType = request.getParameter("type"); 

	int currentPage = pInfo.getCurrentPage();
	int listCount = pInfo.getListCount();
	int maxPage = pInfo.getMaxPage();
	int startPage = pInfo.getStartPage();
	int endPage = pInfo.getEndPage();
	int boardType = pInfo.getBoardType();

	int prev = (currentPage - 1)/5 * 5;
	int next = (currentPage + 4)/5 * 5 + 1;
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>종료된 이벤트</title>
<!-- CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/event.css">

<style>
	.btnArea{height: 45px !important; border: none !important;}
	.btnArea button {margin-left: 230px !important;}
</style>

</head>

<body>

	<%@ include file="../common/header.jsp"%>
	<section id="content">

		<!-- -------------------------------- 메인 -------------------------------- -->
		<div id="container" class="event">
			<h1 class="type1">이벤트</h1>

			<!-- -------------------------------- 메뉴 -------------------------------- -->
			<ul class="tabType1">
				<li><a href="eventList.do?type=1">진행중 이벤트</a></li>
				<li class="on"><a href="pastList.do?type=2">종료된 이벤트</a></li>
				<li><a href="winnerList.do?type=2">이벤트 당첨자</a></li>
			</ul>

			<!-- -------------------------------- 이벤트 -------------------------------- -->
			<ul class="eventList mx-auto">

					<% if(eList.isEmpty()){ %>
					
						<p class="tit">존재하는 이벤트가 없습니다.</p>
						
					<% }else{ %>
					
						<% for(Event e: eList){ %>
								<li>
									<% 
										String viewUrl =  request.getContextPath() + "/event/eventView.do?type=" + eventType
																		+ "&cp=" + currentPage + "&no=" + e.getEventNo();
									%>
									
								
									<a id="view" href="<%=viewUrl%>"> 
									
										<div class="thumb">
										<%
											String src = request.getContextPath() + "/resources/img/event/empty.png";
     									for(Attachment at : fList){
     										if(at.getParentBoardNo() == e.getEventNo()){
     											
     											src = request.getContextPath() 
     													+ "/resources/uploadImages/" 
     													+ at.getFileChangeName();
     										}
     									}
     										%>
	   											<img src="<%=src%>" width="374 !important" height="308 !important">
   											<% 
     								%>
     									<div class="end">
												<p class="text">이벤트가<br>종료되었습니다.</p>
											</div>
										</div>
										<p class="ing" id="<%=e.getEventNo()%>">종료</p>
										<p class="tit"><%=e.getEventTitle()%></p>
										<% 
											String start = new SimpleDateFormat("yyyy-MM-dd").format(e.getStartDay());
											String end = new SimpleDateFormat("yyyy-MM-dd").format(e.getEndDay());
										%>
										<p class="date"><%=start%> ~ <%=end%></p>
									</a>
								</li>
						<% } %>
						
					<% } %>

			</ul>
			
			<div class=" text-center">
				<% if(loginUser != null && loginUser.getUserId().equals("master")){ %>
						<button type="button" class="btn btn btn-primary btn-warning" id="insertBtn" onclick="location.href='insertEventForm.do?type=<%=eventType%>&cp=<%=currentPage%>';">글작성</button>
				<% } %>
			</div>

			
			<!-- -------------------------------- 페이징 바 -------------------------------- -->
			<ul class="paging">
					
					<%if(currentPage > 5) {%>
						<li class="btn"><a href="<%=request.getContextPath()%>/event/pastList.do?type=<%=eventType%>&cp=1">&lt;&lt;</a></li>
						<li class="btn"><a href="<%=request.getContextPath()%>/event/pastList.do?type=<%=eventType%>&cp=<%=prev%>">&lt;</a></li>
					<% } %>
							
					<% for(int p = startPage; p <= endPage; p++){ %>
					 <% if(p == currentPage){ %>
					 		<li><strong><%=p%></strong></li>
						<% }else{ %>
							<li><a id="now" href="<%=request.getContextPath()%>/event/pastList.do?type=<%=eventType%>&cp=<%=p%>"><%=p%></a></li>
						<% } %>
					<% } %>
		
					<%if(next < maxPage) {%>
						<li class="btn"><a href="<%=request.getContextPath()%>/event/pastList.do?type=<%=eventType%>&cp=<%=next%>">&gt;</a></li>
						<li class="btn"><a href="<%=request.getContextPath()%>/event/pastList.do?type=<%=eventType%>&cp=<%=maxPage%>">&gt;&gt;</a></li>
					<% } %>
			
			</ul>
			

		</div>
		<!-- //container -->

	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>


	<script>
	$("#view").on("mouseenter",function(){
		
		$(this).parent().css("cursor", "pointer");
	});
	

	</script>
</body>

</html>