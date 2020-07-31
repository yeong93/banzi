<%@page import="com.kh.banzi.common.Attachment"%>
<%@page import="com.kh.banzi.event.model.vo.Event"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.banzi.event.model.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");
	List<Event> eList = (List<Event>) request.getAttribute("eList");
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
				<li><a href="/event/winnerList.do?type=3">당첨자 발표</a></li>
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
								<li><a src="<%=src%>" target="_blank" title="새창열림">
									<p class="thumb"><img src="#" alt=""></p>
									<p class="ing">진행중</p>
									<p class="tit"><%=e.getEventContent()%></p>
									<p class="date"><%=e.getStartDay()%> ~ <%=e.getEndDay()%></p>
								</a></li>
						<% } %>
						
					<% } %>
						
			</ul>

			<!-- -------------------------------- 페이징 바 -------------------------------- -->
			<ul class="paging">
				<li class="btn2">&lt;&lt;</li>
				<li class="btn">&lt;</li>
				<li><strong>1</strong></li>
				<li><a href="list.do?page=2">2</a></li>
				<li><a href="list.do?page=3">3</a></li>
				<li><a href="list.do?page=4">4</a></li>
				<li><a href="list.do?page=5">5</a></li>
				<li><a href="list.do?page=6">6</a></li>
				<li><a href="list.do?page=7">7</a></li>
				<li><a href="list.do?page=8">8</a></li>
				<li class="btn"><a href="list.do?page=2">&gt;</a></li>
				<li class="btn2">&gt;&gt;</li>
			</ul>


		</div>
		<!-- //container -->


	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>


	<script>

	</script>
</body>

</html>