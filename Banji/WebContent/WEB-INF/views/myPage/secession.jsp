<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>

<!-- mypage CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/mypage.css">
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

							 <hr>

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
										<button class="btn btn-primary btn-lg btn-block" type="submit" id="mypage-btn">탈퇴</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	
	<script>
		function validate() {

			if($("#id").val().trim() == ""){
				swal({
				    icon: "info", 
				    title: "아이디를 입력해주세요."
				});
				$("#id").focus();
				return false;
			}
			if($("#pwd").val().trim() == ""){
				swal({
				    icon: "info", 
				    title: "비밀번호를 입력해주세요."
				});
				$("#pwd").focus();
				return false; 
			}
			
			if(!$("#agree").prop("checked")){
				swal({
				    icon: "info", 
				    title: "약관에 동의해주세요."
				});
				return false;
			}else{
				return confirm("정말로 탈퇴하시겠습니까?")
			}			
				
			return true;	
		}
	</script>
	
</body>

</html>