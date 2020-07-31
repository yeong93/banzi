<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	String type = request.getParameter("type"); 
	String cp = request.getParameter("cp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 게시판  - 글쓰기</title>
<style>
    *{
    font-family: "InfinitySans-RegularA1";
    }
    .insert-label {
      display: inline-block;
      width: 80px;
      line-height: 40px
    }
    
    .boardImg{
    	cursor : pointer;
    }
    #insertBtn, #listBtn{
    background-color:#ffce54;
    border-color:white;
    }
    
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<section id="content">
		<div class="container my-5">

			<h3>게시글 등록</h3>
			<hr>
			<!-- 파일 업로드를 위한 라이브러리
			 cos.jar 라이브러리 다운로드(http://www.servlets.com/
			 -->
			
			<!-- 
				- enctype : form 태그 데이터가 서버로 제출 될 때 인코딩 되는 방법을 지정. (POST 방식일 때만 사용 가능)
				- application/x-www-form-urlencoded : 모든 문자를 서버로 전송하기 전에 인코딩 (form태그 기본값)	
				- multipart/form-data : 모든 문자를 인코딩 하지 않음.(원본 데이터가 유지되어 이미지, 파일등을 서버로 전송 할 수 있음.) 
			-->
			<form action="<%=request.getContextPath()%>/information/insert.do?type=<%=type%>&cp=<%=cp%>" method="post" 
				  enctype="multipart/form-data" role="form" onsubmit="return validate();">

				<div class="mb-2">
					<label class="input-group-addon mr-3 insert-label">카테고리</label> 
					<select	class="custom-select" id="category" name="category" style="width: 150px;">
						<option value="1">음식</option>
						<option value="2">견종백과</option>
						<option value="3">건강상식</option>
						<option value="4">교육,훈련</option>
					</select>
				</div>
				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">제목</label> 
					<input type="text" class="form-control" id="title" name="title" size="70">
				</div>

				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">작성자</label>
					<!-- h5 : input 태그 아니라서 form으로 넘어가지 않음, 세션으로 넘겨줄 것 -->
					<h5 class="my-0" id="writer"><%= loginUser.getUserId() %></h5>
				</div>


				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">작성일</label>
					<!-- 날짜 :sysdate로 설정 -->
					<h5 class="my-0" id="today"></h5>
				</div>

				<hr>

				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label"></label>
					<div class="boardImg" id="titleImgArea">
						<img id="titleImg" width="700" height="300">
					</div>
				</div>

				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label"></label>
					<div class="mr-2 boardImg" id="contentImgArea1">
						<img id="contentImg1" width="700" height="300">
					</div>
					<div class="mr-2 boardImg" id="contentImgArea2">
						<img id="contentImg2" width="700" height="300">
					</div>
					<div class="mr-2 boardImg" id="contentImgArea3">
						<img id="contentImg3" width="700" height="300">
					</div>
				</div>


				<!-- 파일 업로드 하는 부분 -->
				<div id="fileArea">
					<!--  multiple 속성
						- input 요소 하나에 둘 이상의 값을 입력할 수 있음을 명시 (파일 여러개 선택 가능)
					 -->									
					 			<!-- onclick : 클릭이 되었을 때  
					 				 onchange : input 태그의 value 값이 변했을 떄
					 				 LoadImg라는 함수를 호출한다. 
					 				 this : 이벤트가 발생한 요소 (input 태그)와 1을 전달 
					 			-->
					<input type="file" id="img1" name="img1" onchange="LoadImg(this,1)"> 
					<input type="file" id="img2" name="img2" onchange="LoadImg(this,2)"> 
					<input type="file" id="img3" name="img3" onchange="LoadImg(this,3)"> 
					<input type="file" id="img4" name="img4" onchange="LoadImg(this,4)">
				</div>

				<div class="form-group">
					<div>
						<label for="content">내용</label>
					</div>
					<textarea class="form-control" id="content2" name="content"
						rows="10" style="resize: none;"></textarea>
				</div>
				<hr class="mb-4">
				<div class="text-center">
					<button type="submit" class="btn btn-primary" id="insertBtn">등록</button>
					<button type="button" class="btn btn-primary" id="listBtn">목록으로</button>
				</div>
			</form>
		</div>
		</section>
		<%@ include file="../common/footer.jsp"%>

	<script>
		// 오늘 날짜 출력 
		var today = new Date();
		var month = (today.getMonth()+1);

		var str = today.getFullYear() + "-"
				+ (month < 10 ? "0"+month : month) + "-"
				+ today.getDate();
		$("#today").html(str);

		// 유효성 검사 
		function validate() {
			if ($("#title").val().trim().length == 0) {
				alert("제목을 입력해 주세요.");
				$("#title").focus();
				return false;
			}
			console.log($("#content2").val());
			if ($("#content2").val().trim().length == 0) {
				alert("내용을 입력해 주세요.");
				$("#content2").focus();
				return false;
			}
		}
		
		 // 이미지 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
	    $(function () {
	       $("#fileArea").hide(); // 숨김 처리함

	      $("#titleImgArea").click(function () {
	        $("#img1").click(); // 클릭 이벤트를 전달
	      });
	      $("#contentImgArea1").click(function () {
	        $("#img2").click(); // 클릭 이벤트를 전달
	      });
	      $("#contentImgArea2").click(function () {
	        $("#img3").click();
	      });
	      $("#contentImgArea3").click(function () {
	        $("#img4").click();
	      });

	   /*   $(".boardImg").on("click",function(){
	    	  var index =  $(this).children("img").prop("id") + 1;
	        console.log(index);
	        $("#img" + index).click();
	      });*/

	        

	    });

	    // 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
	    // value : this (input 태그 요소의 값 담김) , num : 전달받은 숫자 (1,2,3,4)
	    function LoadImg(value, num) {
	      if (value.files && value.files[0]) {
	    	  // value.files : 현재 요소에 파일이 업로드 되어 있는가? T/F
	    	  // 있으면 true, 없으면 false
	    	  // value.file[0] : multiple 속성 사용으로 인해 
	    	  // 여러 파일이 업로드 될 때 이미지 파일 여러개 선택할 때 배열로 받아옴.
	    	  // 이 때 첫번째 파일이 존재하는가?
	    	  // 파일 단일로만 쓸때는 value.files[0] 없어두 댬
	        var reader = new FileReader();
	        // 자바스크립트 FileReader
        	// 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는 File 혹은 Blob객체를 이용해 파일의 내용을 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 객체
					
        	// FileReader.onload
        	// 업로드 된 파일을 다 읽었을 때
					// load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료 되었을 때마다 발생합니다.
	        reader.onload = function (e) {
	        	// e : 다 읽어들인 이벤트
	        	// target : 읽어들인 파일 (this에 등록된)
	        	//console.log(e.target.result);
	        	// e.target.result
	        	// -> 파일 읽기 동작을 성공한 객체에(fileTag) 올라간 결과(이미지 또는 파일)
	        	// 읽어들인 파일의 결과를 반환함
	        	
	          switch (num) {
	            case 1:
	            	// 읽어들인 파일의 결과가 소스로 들어가서 미리보기로 띄워짐
	              $("#titleImg").attr("src", e.target.result);
	              break;
	            case 2:
	              $("#contentImg1").attr("src", e.target.result);
	              break;
	            case 3:
	              $("#contentImg2").attr("src", e.target.result);
	              break;
	            case 4:
	              $("#contentImg3").attr("src", e.target.result);
	              break;
	          }
	        }

	        reader.readAsDataURL(value.files[0]);
	        // FileReader.readAsDataURL()
	      	// 지정된의 내용을 읽기 시작합니다. Blob완료되면 result속성 data:에 파일 데이터를 나타내는 URL이 포함 됩니다.
	      }
	    }
		
	</script>
</body>
</html>
