<%@page import="com.kh.banzi.event.model.vo.PageInfo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kh.banzi.event.model.vo.Event"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	List<Event> sList = (List<Event>)request.getAttribute("sList");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>

<!-- mypage CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/mypage.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/event.css">

</head>

<body id="mypage">

	<%@ include file="../common/header.jsp"%>

	<section id="content">
	
		<!-- -------------------------------- 메인 -------------------------------- -->
		<div id="container" class="event">
			<h1 class="type1">관리자 페이지</h1>

			<!-- -------------------------------- 메뉴 -------------------------------- -->
			<ul class="tabType1">
				<li><a href="userAuthList.do">회원 등급 부여</a></li>
				<li class="on"><a href="stillList.do">당첨자 미발표 이벤트</a></li>
			</ul>

			<div class="eventList mx-auto">
				<table class="table mx-auto text-center" id="list-table">
					<thead class="thead-light">
						<tr>
							<th>글 번호</th>
							<th>해당 이벤트 번호</th>
							<th>해당 이벤트 제목</th>
							<th>해당 이벤트 기간</th>
							<th>글 작성</th>
					
						</tr>
					</thead>
					
					<tbody>
						<% if(sList.isEmpty()){ %>
						
							<tr>
								<th colspan="2">존재하는 이벤트 당첨자 발표 글이 없습니다.</th>
							</tr>
							
						<% }else { %>
						
							<% for(Event e: sList){ %>
								<tr>
									<td id="td1"><strong><%=e.getEventNo()%></strong></td>
									<td id="td2"><%=e.getEventWinNo()%></td>
									<td id="td3"><%=e.getEventTitle()%></td>
										<% 
											String start = new SimpleDateFormat("yyyy-MM-dd").format(e.getStartDay());
											String end = new SimpleDateFormat("yyyy-MM-dd").format(e.getEndDay());
										%>
									<td id="td4"><%=start%> ~ <%=end%></td>
									<td><button type="button" class="btn btn-outline-secondary" onclick="<%=request.getContextPath()%>/masterPage/insertWinner.do?no=<%=e.getEventNo()%>';">수정</button></td> 
								</tr>
							<% } %>
							
						<% } %>
					</tbody>
				</table>
			</div>
			
			<!-- -------------------------------- 페이징 바 -------------------------------- -->
		</div>
		<!-- //container -->

	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script>
	
	</script>
</body>

</html>