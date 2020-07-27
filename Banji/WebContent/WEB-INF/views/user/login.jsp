<%@page import="com.kh.banzi.user.model.vo.User"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="en">

<head>
<title>로그인</title>
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/SHstyle.css">

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

<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100 p-t-50 p-b-90">
                <form class="form-signin" method="POST" action="<%=request.getContextPath()%>/login/login.do" onsubmit="return fnLogin();">


                    <span class="login100-form-title p-b-51">로그인</span>


                    <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="text" name="userId" placeholder="아이디" id="userId" value="<%=rememberId%>">
                        <span class="focus-input100"></span>
                    </div>


                    <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="password" name="userPwd" placeholder="비밀번호" id="userPwd">
                        <span class="focus-input100"></span>
                    </div>

                    <div class="flex-sb-m w-full p-t-3 p-b-24">
                        <div class="contact100-form-checkbox">
                            <div id="remember-area">
                                <input class="input-checkbox100" type="checkbox" id="rememberId"  name="rememberId" <%= isRemember ? "checked" : "" %>>
                                <label class="label-checkbox100" for="rememberId">아이디 기억하기</label>
                            </div>
                            <div id="search-area">
                                <a href="<%=request.getContextPath()%>/userLogin/searchIdForm.do" class="search" name="search-id">아이디 찾기&nbsp;&nbsp;</a>
                                <a href="<%=request.getContextPath()%>/userLogin/searchPwdForm.do" class="search" name="seearch-Pwd">비밀번호 찾기</a>
                            </div>

                        </div>

                    </div>

                    <div class="container-login100-form-btn m-t-17">
                        <button type="submit" class="login100-form-btn" id="loginBtn">Login</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>


    <script>
	
    	function fnLogin(){
    		if(loginValidate()){
    			$.ajax({
    				url: "<%=request.getContextPath()%>/userLogin/login.do",
    				data: {"userId":$("#userId").val(),
    					   "userPwd":$("#userPwd").val()},
    				type: "POST",
    				dataType: "JSON",
    				success: function(fail){
    					if(fail == 1){
    						swal({
    							icon : "error",
    							title: "로그인 실패",
    							text : "아이디, 비밀번호를 확인해주세요.\n"
    							        + "\n또는 권한을 요청한 회원은 권한요청 승낙을 기다려 주세요. "
    						})
    						
    						$("#userId").val() == "";
    						$("#userPwd").val() == "";
    					}else{
    						location.href = "<%=request.getHeader("referer")%>";
    					}
    				}, error : function(){
    					console.log("ajax 통신 실패")
    				}
    			});
    		}
    		
    		return false;
    	}
    	
        function loginValidate() {
			
			if($("#userId").val().trim() == ""){
				alert("아이디를 입력해 주세요.");
				$("#userId").focus();
				return false;
			}
			if($("#userPwd").val().trim() == ""){
				alert("비밀번호를 입력해 주세요.");
				$("#userPwd").focus();
				return false; 
			}
			return true;	
			
		}
        
      
        

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