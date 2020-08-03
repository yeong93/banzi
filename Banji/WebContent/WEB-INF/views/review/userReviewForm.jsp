<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String type = request.getParameter("type"); 
	String cp = request.getParameter("cp");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">

<!-- jQuery -->
<script src="//code.jquery.com/jquery.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<!-- 별점용 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<style>
	@font-face {
		font-family: "InfinitySans-RegularA1";
		src:
			url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/InfinitySans-RegularA1.woff")
			format("woff");
		font-weight: normal;
		font-style: normal;
	}
	
	@font-face {
		font-family: 'GmarketSansMedium';
		src:
			url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
			format('woff');
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
	
	.container {max-width: 720px;}
	
	h3 {font-family: 'GmarketSansMedium';}
	
	
	/* 이미지 업로드 부분*/
	.boardImg { cursor: pointer; }
</style>
<title>글쓰기폼</title>
</head>

<body>
	<%@ include file="../common/header.jsp"%>
	<div class="empty"></div>
	
	<div class="container">
		<h3>글쓰기</h3>
		<hr>
		
		<form action="insertReview.do?type=<%=type%>" method="post"
			 enctype="multipart/form-data" role="form" onsubmit="return validate();">
			<div class="form-group">
				<label for="exampleFormControlInput1">제목</label> 
				<input type="text" class="form-control" id="title" name="title"
					placeholder="제목을 작성해주세요." >
			</div>
			
			<div class="form-group">
				<label for="exampleFormControlInput1">작성자</label> 
				<span class="writer-area"><%=loginUser.getUserName()%>님</span>
			</div>
			
			<div class="form-group">
			<label for="exampleFormControlInput1">작성날짜</label> 
				<span class="my-0" id="today" ></span>
			</div>

			<div class="form-group">
				<label for="exampleFormControlInput1">카테고리</label> <br>
				<select class="custom-select" id="category" name="category" style="width: 150px;">
						<option value="1">병원</option>
						<option value="2">사료</option>
						<option value="3">간식</option>
						<option value="4">용품</option>
					</select>
			</div>
				
			<div class="form-group">
			<label>평가</label><br>
				
		        <div class="make_star">
		            <div class="rating" data-rate="targetNum" >
		                <i class="fas fa-star"></i>
		                <i class="fas fa-star"></i>
		                <i class="fas fa-star"></i>
		                <i class="fas fa-star"></i>
		                <i class="fas fa-star"></i>
		            </div>
		        </div>
		        
		        <input type="hidden" id="rating" name="rating" value="0">
			</div>

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
			<textarea class="form-control" id="content" name="content" rows="10" style="resize: none;"></textarea>
			
			<br>
			<button type="button" class="btn btn-secondary">목록으로</button>
			&nbsp;
			<button type="submit" class="btn btn-info">등록하기</button>
		
		</div>


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
		// 오늘 날짜 출력 
		var today = new Date();
		var month = (today.getMonth()+1);
	
		var str = today.getFullYear() + "-"
				+ (month < 10 ? "0"+month : month) + "-"
				+ today.getDate();
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
		
		// 유효성 검사 
		function validate() {
			if ($("#title").val().trim().length == 0) {
				alert("제목을 입력해 주세요.");
				$("#title").focus();
				return false;
			}

			if ($("#content").val().trim().length == 0) {
				alert("내용을 입력해 주세요.");
				$("#content").focus();
				return false;
			}
		}

		 
	    function LoadImg(value, num) {
	      if (value.files && value.files[0]) {
	        
    		var reader = new FileReader();
					
	        reader.onload = function (e) { 
	        	
	          switch (num) {
	            case 1:
	              $("#contentImg1").attr("src", e.target.result);
	              break;
	            case 2:
	              $("#contentImg2").attr("src", e.target.result);
	              break;
	            case 3:
	              $("#contentImg3").attr("src", e.target.result);
	              break;
	          }
	        }
	        reader.readAsDataURL(value.files[0]);
	      }
	    }
		
	    
	    // 별점용
	    $(function(){
		    $('.make_star svg').click(function(){
		        var targetNum = $(this).index()+1;
		        $('.make_star svg').css({color:'#000'});
		        $('.make_star svg:nth-child(-n+' + targetNum +')').css({color:'#F05522'});
		        // console.log(targetNum);
		        
		      	$("#rating").val(targetNum);
		    })
		});
	    
	</script>
</body>

</html>