<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>

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
						<h3 id="title">회원 탈퇴</h3>
						
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
							<form method="POST" action="changePwd.do" onsubmit="return validate();" class="form-horizontal"
								role="form">

								<!-- 현재 비밀번호 -->
								<div class="row mb-5 ml-3 mr-3">
									<div class="col-md-12">
										<input type="password" class="form-control" id="nowPwd"
											name="nowPwd" placeholder="현재 비밀번호">
									</div>
								</div>
								
								<br><br><hr><br><br>
								
								<!-- 새 비밀번호 -->
								<div class="row mb-5 ml-3 mr-3">
									<div class="col-md-12">
										<input type="password" class="form-control" id="newPwd"
											name="newPwd" placeholder="새 비밀번호"><br>
										<span id="check-area1"></span><br>
									</div>
								</div>
								
								<!-- 새 비밀번호 확인 -->
								<div class="row mb-5 ml-3 mr-3">
									<div class="col-md-12">
										<input type="password" class="form-control" id="checkPwd"
											name="checkPwd" placeholder="새 비밀번호 확인"><br>
										<span id="check-area2"></span><br>
									</div>
								</div>
								
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

   var pwdCheck = { 
			"nowPwd":false,
			"newPwd":false,
			"checkPwd":false
		};
   
   var $pwd = $("#newPwd, #checkPwd");
	 var $newPwd = $("newPwd");
	 var $checkPwd = $("checkPwd");
	
	$pwd.on("input", function(){
		
		var regExp = /^[A-Za-z0-9]{6,12}$/;
		
		if(!regExp.test($("#newPwd").val())){ 
				$("#check-area1").text("비밀번호 형식이 유효하지 않습니다.").css("color","red");
				pwdCheck.newPwd = false;
			}else{
				$("#check-area1").text("유효한 비밀번호 형식입니다.").css("color","green");
				pwdCheck.newPwd = true;
		}
		
		if(!pwdCheck.newPwd && $("#checkPwd").val().length > 0){
			swal("유효한 비밀번호를 작성해 주세요.");
			$("#checkPwd").val("");
			$("#newPwd").focus();
			
		}else if(pwdCheck.newPwd && $("#checkPwd").val().length > 0){
			
			if($("#newPwd").val().trim() != $("#checkPwd").val().trim()){
				$("#check-area2").text("비밀번호 불일치").css("color","red");
				pwdCheck.checkPwd = false;
			}else{
				$("#check-area2").text("비밀번호 일치").css("color","green");
				pwdCheck.checkPwd = true;
			}
		}
		
	});
   
   function validate() {
		
		if($("#nowPwd").val().trim() == ""){
			swal("현재 비밀번호를 입력해주세요.");
			$("#nowPwd").focus();
			return false; 
		}
		
		if($("#newPwd").val().trim() == ""){
			swal("새 비밀번호를 입력해주세요.");
			$("#newPwd").focus();
			return false; 
		}
		
		if($("#checkPwd").val().trim() == ""){
			swal("새 비밀번호 확인을 입력해주세요.");
			$("#checkPwd").focus();
			return false; 
		}
		
		for(var key in pwdCheck){
			if(!signUpCheck[key]){
				
				var msg;
				switch(key){
				case "newPwd" : msg="새 비밀번호가";  break;
				case "checkPwd" : msg="새 비밀번호가";  break;
				}
				
				swal(msg + "유효하지 않습니다.");
				var el = "#"+key;
				$(el).focus();
				return false;
			}
		}

		return true;	
	};
		
		
		
		
	</script>
</body>

</html>
