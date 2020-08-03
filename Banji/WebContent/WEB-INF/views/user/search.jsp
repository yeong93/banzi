<%@page import="com.kh.banzi.user.model.vo.User"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>비밀번호 변경</title>
<meta charset="UTF-8">

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

<!-- CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login.css">

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<style>
#search-id {
	line-height: 2.3em !important;
}

#search-area a:hover {
	text-decoration: none;
	color: #ffce54;
}

#loginBtn {
	font-family: "yg-jalnan" !important;
}
</style>

</head>

<body id="login">
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100 p-t-50 p-b-90">
                <form class="form-signin" method="POST" action="<%=request.getContextPath()%>/userLogin/search.do" onsubmit="return validate();">


                    <span class="login100-form-title p-b-51">비밀번호 변경</span>


                    <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="text" name="userId" placeholder="아이디" id="userId">
                        <span class="focus-input100"></span>
                    </div>
                    
                    <div class="wrap-input100 validate-input m-b-16">
                        <select class="input100" id="userQuestion" name="userQuestion">
                        <option value="none1">보안질문</option>
                        <option value="none2">-------------------------------------------------------</option>
												<option value="first">내 학창시절 별명은?</option>
												<option value="second">가장 좋아했던 동화책의 제목은?</option>
												<option value="third">첫 애완동물의 이름은?</option>
											</select>
                    </div>
                    
                     <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="text" name="userAnswer" placeholder="보안 답변" id="userAnswer">
                        <span class="focus-input100"></span>
                    </div>
                    
                    <hr>
                    
                     <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="password" name="userPwd" placeholder="새 비밀번호" id="userPwd">
                    </div>
                        <span id="check-area1"></span><br><br>
                    
                     <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="password" name="userPwdCheck" placeholder="새 비밀번호 확인" id="userPwdCheck">
                    </div>
                      	<span id="check-area2"></span><br><br>

                    <div class="container-login100-form-btn m-t-17">
                        <button type="submit" class="login100-form-btn" id="loginBtn">search</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    

	<script>
	
		var pwdCheck = { 
				"userPwd":false,
				"userPwdCheck":false,
			};
		
		var $changePwd = $("#userPwd, #userPwdCheck");
		$changePwd.on("input", function(){
		
		var regExp = /^[A-Za-z0-9]{6,12}$/;
		
		if(!regExp.test($("#userPwd").val())){ 
				$("#check-area1").text("비밀번호 형식이 유효하지 않습니다.").css("color","red");
				pwdCheck.userPwd = false;
			}else{
				$("#check-area1").text("유효한 비밀번호 형식입니다.").css("color","green");
				pwdCheck.userPwd = true;
		}
		
		if(!pwdCheck.userPwd && $("#userPwdCheck").val().length > 0){
			swal("유효한 비밀번호를 작성해 주세요.");
			$("#userPwdCheck").val("");
			$("#userPwd").focus();
			
		}else if(pwdCheck.userPwd && $("#userPwdCheck").val().length > 0){
			
			if($("#userPwd").val().trim() != $("#userPwdCheck").val().trim()){
				$("#check-area2").text("비밀번호 불일치").css("color","red");
				pwdCheck.checkPwd = false;
			}else{
				$("#check-area2").text("비밀번호 일치").css("color","green");
				pwdCheck.checkPwd = true;
			}
		}
		
	});
	
		function validate() {

			if ($("#userId").val().trim() == "") {
				swal({
				    icon: "info", 
				    title: "아이디를 입력해 주세요."
				});
				$("#userId").focus();
				return false;
			}
			if (($("#userQuestion").val() == "none1") || ($("#userQuestion").val() == "none2")) {
				swal({
				    icon: "info", 
				    title: "보안 질문을 선택해 주세요."
				});
				$("#userQuestion").focus();
				return false;
			}
			if ($("#userAnswer").val().trim() == "") {
				swal({
				    icon: "info", 
				    title: "보안 답변을 입력해 주세요."
				});
				$("#userAnswer").focus();
				return false;
			}
			if ($("#userPwd").val().trim() == "") {
				swal({
				    icon: "info", 
				    title: "새 비밀번호를 입력해 주세요."
				});
				$("#userPwd").focus();
				return false;
			}
			if ($("#userPwdCheck").val().trim() == "") {
				swal({
				    icon: "info", 
				    title: "새 비밀번호 확인를 입력해 주세요."
				});
				$("#userPwdCheck").focus();
				return false;
			}
			
			for(var key in pwdCheck){
				if(!signUpCheck[key]){
					
					var msg;
					switch(key){
					case "userPwd" : msg="새 비밀번호가";  break;
					case "userPwdCheck" : msg="새 비밀번호 확인이";  break;
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



	<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>
</body>

</html>