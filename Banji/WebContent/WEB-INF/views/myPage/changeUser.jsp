<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>회원정보 수정</title>
	
	<!-- Required meta tags -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	
	<style>
		/* 인피니티산스 Regular */
		@font-face {
        font-family: "InfinitySans-RegularA1";
        src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/InfinitySans-RegularA1.woff") format("woff");
        font-weight: normal;
        font-style: normal;
      	}

		/* 여기어때 잘난체 */
		@font-face {
			font-family: "yg-jalnan";
			src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff") format("woff");
			font-weight: normal;
			font-style: normal;
		}

		input[type="number"]::-webkit-outer-spin-button,
		input[type="number"]::-webkit-inner-spin-button {
			-webkit-appearance: none;
			margin: 0;
		}

		#title{
			font-family: "InfinitySans-RegularA1";
		}

		#mypage button {
			background-color: #FFCE54;
			border-radius: 0;
			border: none;
			font-family: "InfinitySans-RegularA1";
		}

		#mypage button:focus{
			border: 1px solid #ffffff;
			box-shadow: none;
		}

		#mypage button:hover{
			border: 1px solid #FFCE54;
			background-color: #ffffff;
			color: #FFCE54;
		}

		.readonly{
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

		.readonly:focus{
			border-color: #ced4da !important;
		}
		
		#mypage fieldset{
			border: 1px solid #ced4da;
			
		}

		#mypage legend{
			font-family: "InfinitySans-RegularA1";
			font-size: 1.1em;
			color:#ced4da;
		}

		#mypage label{
			font-family: "InfinitySans-RegularA1";
		}

	</style>
</head>

<body id="mypage">
	<%@ include file="../common/header.jsp"%>
	
	<section id="content ml-3">
		<div class="container mt-3">
		
			<!-- -------------------------- side ----------------------------------- -->
			<div class="row">
			<%@ include file="../myPage/myPageAside.jsp"%>
			
			<!-- -------------------------- main ----------------------------------- -->
			<div class="col-sm-8">
				<h3 id="title">회원정보 수정</h3>
				<hr>
				<div class="bg-white shadow-sm container p-5">
					<form method="POST" action="updateMember.do" onsubmit="return validate();" class="form-horizontal" role="form">
	
						<!-- 아이디 -->
						<div class="row mb-3 ml-3 mr-3">
							<div class="col-md-12">
								<input type="text" class="form-control readonly" id="id" name="id" value="user01" readonly>
							</div>
						</div>
	
						<!-- 이름 -->
						<div class="row mb-3 ml-3 mr-3">
							<div class="col-md-12">
								<input type="text" class="form-control readonly" id="name" name="name" value="홍길동" readonly>
							</div>
						</div>
	
						<!-- 보안질문 -->
						<div class="row mb-3 ml-4 mr-4">
							<fieldset class="col-md-12">
								<legend class="col-md-3 mb-4">보안질문</legend>
								<div class="col-md-12 mb-3">
									<select class="custom-select input" id="question" name="question" value="question">
										<option>강아지 이름은?</option>
										<option>강아지 이름은?</option>
									</select>
								</div>
								<div class="col-md-12 mb-5">
									<input type="text" class="form-control input" id="answer" name="answer" value="김복실" >
								</div>
							</fieldset>
						</div>
	
						<!-- 이메일 -->
						<div class="row mb-3 ml-3 mr-3">
							<div class="col-md-12">
								<input type="email" class="form-control input" id="email" name="email" value="bokzziri@bbb.com">
							</div>
						</div>
	
	
						<!-- 등급 -->
						<div class="row mb-3 ml-4 mr-4">
							<fieldset class="col-md-12">
								<legend class="col-md-3 mb-4">등급</legend>
	
								&nbsp;<input type="radio" id="user" name="grade" value="user">
								<label for="user">일반 회원</label>&nbsp;&nbsp;&nbsp;
								&nbsp;<input type="radio" id="editor" name="grade" value="editor">
								<label for="editor">에디터</label>&nbsp;&nbsp;&nbsp;
								&nbsp;<input type="radio" id="veterinarian" name="grade" value="veterinarian">
								<label for="veterinarian">수의사</label>&nbsp;&nbsp;&nbsp;
								&nbsp;<input type="radio" id="trainer" name="grade" value="trainer">
								<label for="trainer">훈련사</label>
								<br><br>
							</fieldset>
						</div>
					
						<hr><br>
						<div class="row mb-3">
							<div class="col-md-12">
								<button class="btn btn-primary btn-lg btn-block" type="submit">수정</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		</div>
	</section>
	<script>

	</script>
</body>

</html>