<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>

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
				<li><a href="<%=request.getContextPath()%>/myPage/changeUserForm.do">회원정보 수정</a></li>
				<li class="on"><a href="<%=request.getContextPath()%>/myPage/changePwdForm.do">비밀번호 수정</a></li>
				<li><a href="<%=request.getContextPath()%>/myPage/secessionForm.do">회원 탈퇴</a></li>
			</ul>
			
				<div class="col-md-9 mx-auto">
					<div class="col-sm-8 mx-auto">
					
						<div class="bg-white shadow-sm container p-5">
							<form method="POST" action="changePwd.do" onsubmit="return validate();" class="form-horizontal"
								role="form">

								<!-- 현재 비밀번호 -->
								<div class="row mb-5 ml-3 mr-3">
									<div class="col-md-12">
										<input type="password" class="form-control" id="nowPwd" name="nowPwd" placeholder="현재 비밀번호">
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

   var pwdCheck = { 
			"nowPwd":false,
			"newPwd":false,
			"checkPwd":false
		};
   
   var $changePwd = $("#newPwd, #checkPwd");
	 var $newPwd = $("newPwd");
	 var $checkPwd = $("checkPwd");
	
	 $changePwd.on("input", function(){
		
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
	   
	   if($("#nowPwd").val().trim() == $("#newPwd").val().trim()){
			  swal({
				    icon: "warning", 
				    title: "새 비밀번호 재입력",
				    text : "현재 비밀번호와 다른 비밀번호를 입력해주세요."
				});
			  $("#newPwd").val("");
			  $("#checkPwd").val("");
			  $("#newPwd").focus();
			  return false; 
		  }

	 
		
		if($("#nowPwd").val().trim() == ""){
			swal({
			    icon: "info", 
			    title: "현재 비밀번호를 입력해주세요."
			});
			$("#nowPwd").focus();
			return false; 
		}
		
		if($("#newPwd").val().trim() == ""){
			swal({
			    icon: "info", 
			    title: "새 비밀번호를 입력해주세요."
			});
			$("#newPwd").focus();
			return false; 
		}
		
		if($("#checkPwd").val().trim() == ""){
			swal({
			    icon: "info", 
			    title: "새 비밀번호 확인을 입력해주세요."
			});
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
				
				swal({
				    icon: "info", 
				    title: msg + "유효하지 않습니다."
				});
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
