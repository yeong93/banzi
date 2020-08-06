<%@page import="com.kh.banzi.event.model.vo.Event"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String type = request.getParameter("type"); 
	String cp = request.getParameter("cp");
	int no = Integer.parseInt(request.getParameter("no"));
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
				<form class="eventList mx-auto" method="POST" action="<%=request.getContextPath()%>/masterPage/insertWinner.do?type=<%=type%>&cp=<%=cp%>&no=<%=no%>" onsubmit="return validate();">

				  <table class="mx-auto table">
				  	<tr><td colspan="3">이벤트 번호 : <%=no%></td></tr>
				  	<tr>
				  		<td><p>내용</p></td>
				  		<td colspan="2">
				  			<textarea class="form-control" id="content" name="content" rows="10" style="resize: none;" 
				  			></textarea>
						</td>
				  	</tr>
		
				  </table>

				<hr class="mb-4">

				<div class="text-center">
					<button type="submit" class="btn btn-primary btn-warning" id="insertBtn">작성</button>
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