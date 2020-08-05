<%@page import="com.kh.banzi.event.model.vo.Event"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String type = request.getParameter("type"); 
	String cp = request.getParameter("cp");
	Event event = (Event)request.getAttribute("event");
	
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
			<h1 class="type1">이벤트 당첨자 발표</h1>
			
				<form class="eventList mx-auto" action="<%=request.getContextPath()%>/event/changeWinner.do?type=<%=type%>&cp=<%=cp%>&no=<%=event.getEventNo() %>" onsubmit="return validate();">
				  
				  <table class="mx-auto table">
					
				  	<tr>
				  		<td><p>내용</p></td>
				  		<td colspan="2">
				  			<textarea class="form-control" id="content" name="content" rows="10" style="resize: none;" 
				  			><%=event.getEventWin()%></textarea>
						</td>
				  	</tr>
		
				  </table>

				<hr class="mb-4">

				<div class="text-center">
					<button type="submit" class="btn btn-primary btn-warning" id="insertBtn">글 수정</button>
					<button type="button" class="btn btn-primary btn-warning" id="listBtn">목록으로</button>
				</div>

			</form>
				
	
			
		</div>
		<!-- //container -->

	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
	<script>
		function validate() {
			if ($("#content").text().trim().length == 0) {
				swal({
				    icon: "info", 
				    title: "내용을 입력해 주세요."
				});
				$("#content").focus();
				return false;
			}
			
		}
		
		
	</script>

</body>
</html>