<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	List<User> aList = (List<User>)request.getAttribute("aList");
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
				<li class="on"><a href="userAuthList.do">회원 등급 부여</a></li>
				<li><a href="stillList.do">당첨자 미발표 이벤트</a></li>
			</ul>

			<!-- -------------------------------- 이벤트 -------------------------------- -->
			<div class="eventList mx-auto">
				<table class="table mx-auto text-center">
					<thead class="thead-light">
						<tr>
							<th>회원 번호</th>
							<th>회원 아이디</th>
							<th>회원 등급</th>
							<th>회원 등급 부여</th>
						</tr>
					</thead>
					
					<tbody>
						<% if(aList.isEmpty()){ %>
						
							<tr>
								<th colspan="2">존재하는 등급 부여 목록이 없습니다.</th>
							</tr>
							
						<% }else { %>
						
							<% for(User u : aList){ %>
								<tr>
									<td><%=u.getUserNo()%></td>
									<td><%=u.getUserId()%></td>
									<td><%=u.getUserGrade()%></td>
									<td>
										<button type="button" class="btn btn-outline-secondary" id="insertBtn" onclick="location.href='changeAuth.do?&no=<%=u.getUserNo()%>';"><%=u.getUserId()%> 회원 등급 부여</button>
									</td>
								</tr>
							<% } %>
							
						<% } %>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
	</script>
</body>

</html>
