<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<!-- mypage CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/mypage.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/event.css">

</head>

<body id="mypage">

	<%@ include file="../common/header.jsp"%>

	<section id="content">
	
		<!-- -------------------------------- 메인 -------------------------------- -->
		<div class="container-fluid mt-5 " class="event">
		
			<h1 class="type1">마이 페이지</h1>

			<!-- -------------------------------- 메뉴 -------------------------------- -->
			<ul class="tabType1">
				<li class="on"><a href="<%=request.getContextPath()%>/myPage/changeUserForm.do">회원정보 수정</a></li>
				<li><a href="<%=request.getContextPath()%>/myPage/changePwdForm.do">비밀번호 수정</a></li>
				<li><a href="<%=request.getContextPath()%>/myPage/secessionForm.do">회원 탈퇴</a></li>
			</ul>
			
			<%
				String answer = loginUser.getUserAnswer(); 
				String email = loginUser.getUserEmail();
				
				if(answer == null){
					answer = "";
				}
				if(email == null){
					email = "";
				}
			%>
			
				<div class="col-md-9 mx-auto">
					<div class="col-sm-8 mx-auto">
					
						<div class="bg-white shadow-sm container p-5">
							<form method="POST" action="changeUser.do" onsubmit="return validate();" class="form-horizontal"
								role="form">

								<!-- 아이디 -->
									<div class="row mb-5 ml-4 mr-4">
									<fieldset class="col-md-12">
										<legend class="col-md-3 mb-4">아이디</legend>
										<div class="col-md-12 mb-5">
											 <input type="text" class="form-control readonly" id="id" name="id" value="<%=loginUser.getUserId()%>" readonly>
										</div>
									</fieldset>
								</div>

								<!-- 이름 -->
									<div class="row mb-5 ml-4 mr-4">
									<fieldset class="col-md-12">
										<legend class="col-md-3 mb-4">이름</legend>
										<div class="col-md-12 mb-5">
											<input type="text" class="form-control readonly" id="name" name="name" value="<%=loginUser.getUserName()%>" readonly>
										</div>
									</fieldset>
								</div>

								<!-- 보안질문 -->
								<div class="row mb-5 ml-4 mr-4">
									<fieldset class="col-md-12">
										<legend class="col-md-3 mb-4">보안질문</legend>
										<div class="col-md-12 mb-3">
											<select class="custom-select input" id="question" name="question">
												<option value="first">내 학창시절 별명은?</option>
												<option value="second">가장 좋아했던 동화책의 제목은?</option>
												<option value="third">첫 애완동물의 이름은?</option>
											</select>
										</div>
										<div class="col-md-12 mb-5">
											<input type="text" class="form-control input" id="answer" name="answer" value="<%=answer%>">
										</div>
									</fieldset>
								</div>
								
								<script>
									$.each($("#question > option"), function(index, item){
										if($(item).val() == "<%=loginUser.getUserQuestion()%>"){
											$(item).prop("selected", true);
										}
									});
								</script>

								<!-- 이메일 -->
								<div class="row mb-5 ml-4 mr-4">
									<fieldset class="col-md-12">
										<legend class="col-md-3 mb-4">이메일</legend>
										<div class="col-md-12 mb-5">
											 <input type="email" class="form-control input" id="email" name="email" value="<%=email%>">
										</div>
									</fieldset>
								</div>
								
								<hr>
								<br>
								<div class="row mb-5">
									<div class="col-md-12">
										<button class="btn btn-primary btn-lg btn-block" type="submit" id="mypage-btn">수정</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
		function validate() {
			
			if($("#answer").val().trim() == ""){
				swal({
				    icon: "info", 
				    title: "보안질문에 답변해주세요."
				});
				$("#answer").focus();
				return false; 
			}
			
			if($("#email").val().trim() == ""){
				swal({
				    icon: "info", 
				    title: "이메일을 입력해주세요."
				});
				$("#email").focus();
				return false; 
			}
			
			return true;	
		}
	</script>
</body>

</html>