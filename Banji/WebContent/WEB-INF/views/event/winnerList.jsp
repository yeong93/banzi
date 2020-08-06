<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.kh.banzi.common.Attachment"%>
<%@page import="com.kh.banzi.event.model.vo.Event"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.banzi.event.model.vo.PageInfo"%>

<%
	PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");
	List<Event> wList = (List<Event>)request.getAttribute("wList");
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
				<li><a href="pastList.do?type=2">종료된 이벤트</a></li>
				<li class="on"><a href="winnerList.do?type=2">이벤트 당첨자</a></li>
			</ul>

			<!-- -------------------------------- 이벤트 -------------------------------- -->
			
			<div class="eventList mx-auto">
				<table class="table mx-auto text-center" id="list-table">
					<thead class="thead-light">
						<tr>
							<th>글 번호</th>
							<th>해당 이벤트 번호</th>
							<th>해당 이벤트 제목</th>
							<th>해당 이벤트 기간</th>
							<% if(loginUser != null && loginUser.getUserId().equals("master")){ %>
								<th>글 수정</th>
							<% }%>
						</tr>
					</thead>
					
					<tbody>
						<% if(wList.isEmpty()){ %>
						
							<tr>
								<th colspan="2">존재하는 이벤트 당첨자 발표 글이 없습니다.</th>
							</tr>
							
						<% }else { %>
						
							<% for(Event e: wList){ %>
								<tr>
									<td id="td1"><%=e.getEventWinNo()%></td>
									<td id="td2"><strong><%=e.getEventNo()%></strong></td>
									<td id="td3"><%=e.getEventTitle()%></td>
										<% 
											String start = new SimpleDateFormat("yyyy-MM-dd").format(e.getStartDay());
											String end = new SimpleDateFormat("yyyy-MM-dd").format(e.getEndDay());
										%>
									<td id="td4"><%=start%> ~ <%=end%></td>
									
									<% if(loginUser != null && loginUser.getUserId().equals("master")){ %>
									
									<td><button type="button" class="btn btn-outline-secondary" onclick="location.href='changeWinnerForm.do?type=<%=eventType%>&cp=<%=currentPage%>&no=<%=e.getEventNo()%>';">수정</button></td> 
							  	<% }%>
							  	
								</tr>
							<% } %>
							
						<% } %>
					</tbody>
				</table>
			</div>
			
			<!-- -------------------------------- 페이징 바 -------------------------------- -->
			<ul class="paging">
					
					<%if(currentPage > 5) {%>
						<li class="btn"><a href="<%=request.getContextPath()%>/event/winnerList.do?type=<%=eventType%>&cp=1">&lt;&lt;</a></li>
						<li class="btn"><a href="<%=request.getContextPath()%>/event/winnerList.do?type=<%=eventType%>&cp=<%=prev%>">&lt;</a></li>
					<% } %>
							
					<% for(int p = startPage; p <= endPage; p++){ %>
					 <% if(p == currentPage){ %>
					 		<li><strong><%=p%></strong></li>
						<% }else{ %>
							<li><a id="now" href="<%=request.getContextPath()%>/event/winnerList.do?type=<%=eventType%>&cp=<%=p%>"><%=p%></a></li>
						<% } %>
					<% } %>
		
					<%if(next < maxPage) {%>
						<li class="btn"><a href="<%=request.getContextPath()%>/event/winnerList.do?type=<%=eventType%>&cp=<%=next%>">&gt;</a></li>
						<li class="btn"><a href="<%=request.getContextPath()%>/event/winnerList.do?type=<%=eventType%>&cp=<%=maxPage%>">&gt;&gt;</a></li>
					<% } %>
			
			</ul>
			

		</div>
		<!-- //container -->

	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script>
	var $td = $("#td1, #td2, #td3, #td4")
	$td.on("click", function(){
		var boardNo = $(this).parent().children().eq(1).text();
		
			$.ajax({
			url : "winnerView.do",
			data : {"no" : boardNo, "type" : <%=eventType%>, "cp" : <%=currentPage%>},
			type : "POST",
			dataType : "JSON",
			success : function(event){
			
				if(event != null){
					Swal.fire({
						  title: '<strong>'+event.eventTitle+'</strong>',
						  html:
							  '<h4>당첨자 발표</h4><hr>'+
							  '<br><h5>' + event.eventWin + '</h5><br>' +
							  '<h5> 자세한 사항은 이메일로 발송드렸습니다.</h5><br>',
						  showCloseButton: true
						})
				}else{
					Swal.fire("조회된 결과가 없습니다.");
				}
				
			}, error : function(){
				console.log("ajax 통신 실패");
			}
			
		});

	}).on("mouseenter",function(){
		$(this).parent().css("cursor", "pointer");
	});
	
	

	</script>
</body>

</html>