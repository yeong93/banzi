<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>

<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous" />
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<style>

#mypage *{
	font-family: "InfinitySans-RegularA1";
}

input[type="number"]::-webkit-outer-spin-button, input[type="number"]::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}

#title {
	text-align: center;
}

#myPage-btn {
	font-family: "yg-jalnan" !important;
	border: 10px solid #FFCE54 !important;
	background-color: #FFCE54;
	border-radius: 0;
	border: none;
}

#myPage-btn:focus {
	box-shadow: none;
}

#myPage-btn:hover {
	border: 10px solid #FFCE54;
	background-color: #ffffff;
	color: #FFCE54;
}

.readonly {
	background-color: #ffffff !important;
}

#mypage input, #mypage select {
	border: none;
	border-radius: 0;
	border-bottom: 1px solid #ced4da;
}

#mypage input:focus, #mypage select:focus {
	border-color: #FFCE54;
	box-shadow: none;
}

.readonly:focus {
	border-color: #ced4da !important;
}

#mypage fieldset {
	border: 1px solid #ced4da;
}

#mypage legend {
	font-size: 1.1em;
	color: #ced4da;
}

/* *************** aside ******************* */
#myPage-aside *{
	text-decoration: none;
	color: black;
	border-radius: 0;
	text-align: center;
}

#myPage-aside li:hover{
	background-color: #ced4da;
}


</style>
</head>

<body id="mypage">

	<%@ include file="../common/header.jsp"%>

	<section id="content">
	
		<div class="container-fluid mt-5">
			<div class="row">

				<div class="col-md-3">
						<h3 id="title">회원정보 수정</h3>
						
						<div class="col-sm-10 mt-5 mx-auto" id="myPage-aside">
							<ul class="list-group">
								<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/myPage/changeUserForm.do">회원정보 수정</a></li>
								<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/myPage/changePwdForm.do">비밀번호 수정</a></li>
								<li class="list-group-item list-group-item-action"><a href="<%=request.getContextPath()%>/myPage/secessionForm.do">회원 탈퇴</a></li>
							</ul>
						</div>
				</div>
				
				<div class="col-md-9">
					<div class="col-sm-8">
					
						<div class="bg-white shadow-sm container p-5">
							<form method="POST" action="changeUser.do" onsubmit="return validate();" class="form-horizontal"
								role="form">

								<!-- 아이디 -->
								<div class="row mb-5 ml-3 mr-3">
									<div class="col-md-12">
										<input type="text" class="form-control readonly" id="id"
											name="id" value="<%=loginUser.getUserId()%>" readonly>
									</div>
								</div>

								<!-- 이름 -->
								<div class="row mb-5 ml-3 mr-3">
									<div class="col-md-12">
										<input type="text" class="form-control readonly" id="name"
											name="name" value="<%=loginUser.getUserName()%>" readonly>
									</div>
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
											<input type="text" class="form-control input" id="answer" name="answer" value="<%=loginUser.getUserAnswer()%>">
										</div>
									</fieldset>
								</div>
								
								<script>
									$.each($("#question > option"), function(index, item){
										if($(item).val() == "<%=loginUser.getUserQuestion()%>"){
											$(item).prop("selected",true);
										}
									});
								</script>

								<!-- 이메일 -->
								<div class="row mb-5 ml-3 mr-3">
									<div class="col-md-12">
										<input type="email" class="form-control input" id="email" name="email" value="<%=loginUser.getUserEmail()%>">
									</div>
								</div>


								<!-- 등급 -->
								<div class="row mb-5 ml-4 mr-4">
									<fieldset class="col-md-12">
										<legend class="col-md-3 mb-4">등급</legend>

										&nbsp;<input type="radio" id="user" name="grade" value="user" >
										<label for="user">사용자</label>&nbsp;&nbsp;&nbsp; 
										
										&nbsp;<input type="radio" id="veterinarian" name="grade" value="veterinarian"> 
										<label for="veterinarian">수의사</label>&nbsp;&nbsp;&nbsp;
										
										&nbsp;<input type="radio" id="trainer" name="grade"value="trainer"> 
										<label for="trainer">훈련사</label> 
										
										&nbsp;<input type="radio" id="editor" name="grade" value="editor">
										<label for="editor">에디터</label>&nbsp;&nbsp;&nbsp; 
										
										<br><br>
									</fieldset>
								</div>
								<script>
								
									$(function(){
										$("input:radio[value='<%=loginUser.getUserGrade()%>']").prop("checked", true);
									});
								</script>
								
								<hr>
								<br>
								<div class="row mb-5">
									<div class="col-md-12">
										<button class="btn btn-primary btn-lg btn-block" type="submit" id="myPage-btn">수정</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script>
		function validate() {
			
			if($("#answer").val().trim() == ""){
				alert("보안 질문에 답변해주세요.");
				$("#answer").focus();
				return false; 
			}
			
			if($("#email").val().trim() == ""){
				alert("이메일을 입력해주세요.");
				$("#email").focus();
				return false; 
			}
			
			return true;	
		}
	</script>
</body>

</html>