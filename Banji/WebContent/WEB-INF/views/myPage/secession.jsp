<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>

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
#mypage * {
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
#myPage-aside * {
	text-decoration: none;
	color: black;
	border-radius: 0;
	text-align: center;
}

#myPage-aside li:hover {
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
					<h3 id="title">비밀번호 수정</h3>

					<div class="col-sm-10 mt-5 mx-auto" id="myPage-aside">
						<ul class="list-group">
							<li class="list-group-item list-group-item-action"><a
								href="<%=request.getContextPath()%>/myPage/changeUserForm.do">회원정보
									수정</a></li>
							<li class="list-group-item list-group-item-action"><a
								href="<%=request.getContextPath()%>/myPage/changePwdForm.do">비밀번호
									수정</a></li>
							<li class="list-group-item list-group-item-action"><a
								href="<%=request.getContextPath()%>/myPage/secessionForm.do">회원
									탈퇴</a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-9">
					<div class="col-sm-8">

						<div class="bg-white shadow-sm container p-5">
							<form method="POST" action="secession.do"
								onsubmit="return validate();" class="form-horizontal"
								role="form">

								<!-- 현재 아이디 -->
								<div class="row mb-5 ml-3 mr-3">
									<div class="col-md-12">
										<input type="text" class="form-control" id="id" name="id"
											placeholder="아이디">
									</div>
								</div>

								<br> <br>
								<hr>
								<br> <br>

								<!-- 현재 비밀번호 -->
								<div class="row mb-5 ml-3 mr-3">
									<div class="col-md-12">
										<input type="password" class="form-control" id="pwd"
											name="pwd" placeholder="비밀번호">
									</div>
								</div>
								<hr>
								
								
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="form-group pull-left">
											<label class="control-label"> 회원 탈퇴 약관 </label>
											<div class="col-xs-12">
												<textarea class="form-control" readonly rows="10" cols="100">
제1조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4


제2조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4
</textarea>
											</div>
											<div class="checkbox pull-right">
												<div class="custom-checkbox">
													<div class="form-check">
														<input type="checkbox" name="agree" id="agree"
															class="form-check-input custom-control-input"> <br>
														<label class="form-check-label custom-control-label"
															for="agree">위 약관에 동의합니다.</label>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<hr>
								<br>
								<div class="row mb-5">
									<div class="col-md-12">
										<button class="btn btn-primary btn-lg btn-block" type="submit"
											id="myPage-btn">탈퇴</button>
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

			if($("#id").val().trim() == ""){
				alert("아이디를 입력해 주세요.");
				$("#id").focus();
				return false;
			}
			if($("#pwd").val().trim() == ""){
				alert("비밀번호를 입력해 주세요.");
				$("#pwd").focus();
				return false; 
			}
			
			if(!$("#agree").prop("checked")){
				alert("약관에 동의해 주세요.");
				return false;
			}else{
				return confirm("정말로 탈퇴하시겠습니까?");
			}			
			
			return true;	
		}
	</script>
</body>

</html>