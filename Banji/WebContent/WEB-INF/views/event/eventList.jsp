<%@page import="com.kh.banzi.common.Attachment"%>
<%@page import="com.kh.banzi.event.model.vo.Event"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.banzi.event.model.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");
	List<Event> eList = (List<Event>)request.getAttribute("eList");
	List<Attachment> fList = (List<Attachment>) request.getAttribute("fList");

	int currentPage = pInfo.getCurrentPage();
	int listCount = pInfo.getListCount();
	int maxPage = pInfo.getMaxPage();
	int startPage = pInfo.getStartPage();
	int endPage = pInfo.getEndPage();
	int boardType = pInfo.getBoardType();

	int prev = (currentPage - 1)/10 * 10;
	int next = (currentPage + 9)/10 * 10 + 1;
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>진행중인 이벤트</title>
<!-- mypage CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/event.css">

</head>

<body>

	<%@ include file="../common/header.jsp"%>
	<section id="content">

		<!-- -------------------------------- 메인 -------------------------------- -->
		<div id="container" class="event">
			<h1 class="type1">이벤트</h1>

			<!-- -------------------------------- 메뉴 -------------------------------- -->
			<ul class="tabType1">
				<li class="on"><a href="/event/eventList.do?type=1">진행중 이벤트</a></li>
				<li><a href="/event/pastList.do?type=2">종료된 이벤트</a></li>
				<li><a href="/event/winnerList.do">당첨자 발표</a></li>
			</ul>

			<!-- -------------------------------- 이벤트 -------------------------------- -->
			<ul class="eventList mx-auto">

					<% if(eList.isEmpty()){ %>
					
						<p class="tit">존재하는 이벤트가 없습니다.</p>
						
					<% }else{ %>
					
						<% for(Event e: eList){ %>
						
								<% 
									String src = null;
									for(Attachment at : fList){
										if(at.getParentBoardNo() == e.getEventNo()){
											src = request.getContextPath()
													+ "/resources/img/"
													+ at.getFileChangeName();
										}
									}
								%>
								<li id="detail">
									<a src="<%=src%>">
										<p class="thumb"><img src="#" alt="<%=e.getEventNo()%>"></p>
										<p class="ing">진행중</p>
										<p class="tit"><%=e.getEventContent()%></p>
										<p class="date"><%=e.getStartDay()%> ~ <%=e.getEndDay()%></p>
									</a>
								</li>
						<% } %>
						
					<% } %>
						
			</ul>
			
			<% if(loginUser != null && loginUser.getUserId().equals("master")){ %>
					<button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href='insertEvent.do';">글쓰기</button>
			<% } %>

			<!-- -------------------------------- 페이징 바 -------------------------------- -->
			<ul class="paging">
			
			<%if(currentPage > 10) {%>
				<li class="btn" href="<%=request.getContextPath()%>/event/eventList.do?type=1&cp=1">&lt;&lt;</li>
				<li class="btn" href="<%=request.getContextPath()%>/event/eventList.do?type=1&cp=<%=prev%>">&lt;</li>
			<% } %>
					
			<% for(int p = startPage; p <= endPage; p++){ %>
			 <% if(p == currentPage){ %>
			 		<li><strong><%=p%></strong></li>
				<% }else{ %>
					<li><a href="<%=request.getContextPath()%>/event/eventList.do?type=1&cp=<%=p%>"><%=p%></a></li>
				<% } %>
			<% } %>

			<%if(next < maxPage) {%>
				<li class="btn"><a href="<%=request.getContextPath()%>/event/eventList.do?type=1&cp=<%=next%>">&gt;</a></li>
				<li class="btn"><a href="<%=request.getContextPath()%>/event/eventList.do?type=1&cp=<%=maxPage%>">&gt;&gt;</a></li>
			<% } %>
			
			</ul>
		
		</div>
		<!-- //container -->

	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>


	<script>
	$("#detail").on("click", function(){
		
		var eventNo = $(this).children("img").attr("alt");
		location.href = "<%=request.getContextPath()%>/event/eventList.do?cp=<%=currentPage%>&no" + eventNo;
	
	}).on("mouseenter",function(){
		
		$(this).parent().css("cursor", "pointer");
		
	});
	

	</script>
</body>

</html>