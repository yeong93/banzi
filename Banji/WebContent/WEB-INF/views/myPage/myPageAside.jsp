<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body id="sidebar">
<div class="col-sm-4 mt-5">
	<ul class="list-group">
		<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/myPage/changeUserForm.do">회원정보 수정</a></li>
		<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/myPage/changePwdForm.do">비밀번호 수정</a></li>
		<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/myPage/secessionForm.do">회원 탈퇴</a></li>
	</ul>
</div>
</body>
</html>