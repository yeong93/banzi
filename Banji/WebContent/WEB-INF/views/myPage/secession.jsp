<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>

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
				<li><a href="<%=request.getContextPath()%>/myPage/changePwdForm.do">비밀번호 수정</a></li>
				<li class="on"><a href="<%=request.getContextPath()%>/myPage/secessionForm.do">회원 탈퇴</a></li>
			</ul>
			
				<div class="col-md-9 mx-auto">
					<div class="col-sm-8 mx-auto">

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
제 11 조 (회원 탈퇴 및 자격 상실)

① 회원은 회사에 언제든지 회원 탈퇴를 요청할 수 있으며 회사는 요청을 받은 즉시 해당 회원의 회원 탈퇴를 위한 절차를 밟아 개인정보취급방침에 따라 회원 등록을 말소합니다.

② 회사의 모든 서비스에서 이용중인 서비스의 기간이 남아있는 회원이 탈퇴 요청하였을 경우 회원탈퇴로 인한 서비스의 중지 또는 피해는 회원탈퇴 이용자에게 있습니다.

③ 회원이 서비스 이용에 있어서 본 양관 제 제 10조 내용을 위반하거나, 다음 각 호의 사유에 해당하는 경우 회사는 직권으로 회원자격 상실 및 회원탈퇴의 조치를 할 수 있습니다.

1. 다른 사람의 명의를 사용하여 가입 신청한 경우

2. 신청 시 필수 작성 사항을 허위로 기재한 경우

3. 관계법령의 위반을 목적으로 신청하거나 그러한 행위를 하는 경우

4. 사회의 안녕질서 또는 미풍양속을 저해할 목적으로 신청하거나 그러한 행위를 하는 경우

5. 다른 사람의 회사 서비스 이용을 방해하거나 그 정보를 도용하는 등 전자거래질서를 위협하는 경우

6. 관계 법령 위배와 본 약관이 금지하는 행위를 하는 회원 경우

④ 회사가 직권으로 화원탈퇴 처리를 하고자 하는 경우에는 말소 전에 회원에게 소명의 기회를 부여합니다.
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