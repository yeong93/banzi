<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">

<!-- jQuery -->
<script src="//code.jquery.com/jquery.min.js"></script>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script
	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>


<style>
	@font-face {
		font-family: "InfinitySans-RegularA1";
		src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/InfinitySans-RegularA1.woff") format("woff");
		font-weight: normal;
		font-style: normal;
	}
	
      @font-face { 
            font-family: 'GmarketSansMedium'; 
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
            font-weight: normal; 
            font-style: normal; 
        }
	
	.empty {
		width: 100%;
		height: 200px;
	}
	
	body {
		font-family: "InfinitySans-RegularA1";
	}

	.btn, #btn2 {
		background-color: #ffce54;
		border: none;
	}
	
	.empty {
		width: 100%;
		height: 220px;
	}
	
	.container {
		max-width: 720px;
	}
	
	h3 {
	  font-family: 'GmarketSansMedium'; 
	}
	
	/* 별css */
	.rating {
		display: inline-flex;
		flex-direction: row-reverse;
		justify-content: center;
	}
	
	.rating label {
		margin-bottom: 0px;
	}
	
	.rating>input {
		display: none
	}
	
	.rating>label {
		position: relative;
		width: 1em;
		font-size: 2vw;
		color: #FFD600;
		cursor: pointer
	}
	
	.rating>label::before {
		content: "\2605";
		position: absolute;
		opacity: 0
	}
	
	.rating>label:hover:before, .rating>label:hover ~label:before {
		opacity: 1 !important
	}
	
	.rating>input:checked ~label:before {
		opacity: 1
	}
	
	/* 이미지 업로드 부분*/
	.boardImg {
		cursor: pointer;
	}
</style>
<title>글쓰기폼</title>
</head>

<body>
	<%@ include file="../common/header.jsp"%>
	<div class="empty"></div>
	<div class="container">
		<h3>글쓰기</h3>
		<hr>
		<form action="" method="post">
			<div class="form-group">
				<label for="exampleFormControlInput1">제목</label> <input type="text"
					class="form-control" id="exampleFormControlInput1" name="title" 
					placeholder="제목을 작성해주세요.">
			</div>

			<div class="form-group">
				<label for="exampleFormControlInput1">작성자</label> <input type="text"
					class="form-control" id="exampleFormControlInput1" name="crea_id">
			</div>

			<div class="form-group">
				<label for="exampleFormControlInput1">작성일</label> <input type="text"
					class="form-control" id="exampleFormControlInput1" name="crea_id">
			</div>

			<div class="form-group">
				<label for="exampleFormControlInput1">카테고리</label> <br> <input
					type="radio" name="review-category" value="hospital" id="hospital">
				<label for="hospital">병원</label> &nbsp; <input type="radio"
					name="review-category" value="feed" id="feed"> <label
					for="feed">사료</label> &nbsp; <input type="radio"
					name="review-category" value="snack" id="snack"> <label
					for="snack">간식</label> &nbsp; <input type="radio"
					name="review-category" value="goods" id="goods"> <label
					for="goods">용품</label>
			</div>

			<div class="form-group"></div>
			<label>평가</label><br>
			<div class="rating">
				<input type="radio" name="rating" value="5" id="star5"><label
					for="star5">☆</label> <input type="radio" name="rating" value="4"
					id="star4"><label for="star4">☆</label> <input type="radio"
					name="rating" value="3" id="star3"><label for="star3">☆</label>
				<input type="radio" name="rating" value="2" id="star2"><label
					for="star2">☆</label> <input type="radio" name="rating" value="1"
					id="star1"><label for="star1">☆</label>
			</div>
			<div class="form-group">

				<label>이미지</label>
				<!-- 이미지 업로드 부분 -->
				<div class="form-inline mb-2">
					
					<div class="mr-2 boardImg" id="contentImgArea1">
						<img id="contentImg1" width="100" height="100">
					</div>

					<div class="mr-2 boardImg" id="contentImgArea2">
						<img id="contentImg2" width="100" height="100">
					</div>

					<div class="mr-2 boardImg" id="contentImgArea3">
						<img id="contentImg3" width="100" height="100">
					</div>
				</div>

				<label for="exampleFormControlTextarea1">내용</label>
				<textarea class="form-control" id="exampleFormControlTextarea1"
					name="contents" rows="10" style="resize: none;"></textarea>
			</div>
	
			<br>
			<button type="button" class="btn btn-secondary">목록으로</button>
			&nbsp;
			<button type="submit" class="btn btn-info">등록하기</button>






			<!-- 숨긴 input태그 -->
			<div id="fileArea">
				<!--  multiple 속성
					- input 요소 하나에 둘 이상의 값을 입력할 수 있음을 명시 (파일 여러개 선택 가능)
				 -->
				<input type="file" id="img1" name="img1" onchange="LoadImg(this,1)">
				<input type="file" id="img2" name="img2" onchange="LoadImg(this,2)">
				<input type="file" id="img3" name="img3" onchange="LoadImg(this,3)">
			</div>

		</form>
	</div>


	<script>
		// 시간 출력용 함수ss
		var today = new Date();
		var month = (today.getMonth() + 1);

		var str = today.getFullYear() + "-"
				+ (month < 10 ? "0" + month : month) + "-" + today.getDate();
		$("#today").html(str);

		// 페이지 열자마자 실행되는 함수
		$(function() {
			$("#fileArea").hide();

			$("#contentImgArea1").click(function() {
				$("#img1").click();
			});
			$("#contentImgArea2").click(function() {
				$("#img2").click();
			});
			$("#contentImgArea3").click(function() {
				$("#img3").click();
			});
		});
	</script>
</body>

</html>