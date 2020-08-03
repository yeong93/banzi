<%@page import="com.kh.banzi.information.model.vo.Information"%>
<%@page import="com.kh.banzi.common.Attachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	Information information = (Information)request.getAttribute("information");
	List<Attachment> fList = (List<Attachment>)request.getAttribute("fList");
	String type = request.getParameter("type"); 
	String cp = request.getParameter("cp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 게시판 - 수정하기</title>
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
	#wrapper{
	width : 100%;
	height : 100%;
	position : relative;
	}
	#wrapper div{
	margin : 0 auto;'
	left : 50%;
	top : 50%;
	padding : 10px;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
		<section id="content">
		<div class="container my-5">

			<h3>게시글 수정</h3>
			<hr>
			
			<form action="<%=request.getContextPath()%>/information/update.do?type=<%=type%>&cp=<%=cp%>&no=<%=information.getInfoBoardNo()%>" method="post" 
				  enctype="multipart/form-data" role="form" onsubmit="return validate();">

				<div class="mb-2">
					<label class="input-group-addon mr-3 insert-label">카테고리</label> 
					<select	class="custom-select" id="category" name="category" style="width: 150px;">
						<option value="1" selected>음식</option>
						<option value="2">견종백과</option>
						<option value="3" >건강상식</option>
						<option value="4">교육/훈련</option>
					</select>
				</div>
				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">제목</label> 
					<input type="text" class="form-control" id="title" name="title" size="70" value="<%=information.getInfoBoardTitle()%>">
				</div>

				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">작성자</label>
					<h5 class="my-0" id="writer"><%= loginUser.getUserId() %></h5>
				</div>


				<!-- <div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">작성일</label>
					<h5 class="my-0" id="today"></h5>
				</div> -->

				<hr>
				<div id = "wrapper">
				<div>
					<div class="boardImg" id="titleImgArea">
						<img id="titleImg" width="700" height="300">
					</div>
				</div>
				<div>
					<label class="input-group-addon mr-3 insert-label"></label>
					<div id="contentImgArea1">
						<img id="contentImg1" width="700" height="300">
					</div>
					<div id="contentImgArea2">
						<img id="contentImg2" width="700" height="300">
					</div>
					<div id="contentImgArea3">
						<img id="contentImg3" width="700" height="300">
					</div>
				</div>
				</div>
				<!-- 파일 업로드 하는 부분 -->
				<div id="fileArea">
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
						rows="10" style="resize: none;"><%=information.getInfoBoardContent()%></textarea>
				</div>


				<hr class="mb-4">
				<div class="text-center">
					<button type="submit" class="btn btn-primary">수정</button>
					<button type="button" class="btn btn-primary"
					onclick="location.href='view.do?type=<%=type%>&cp=<%=cp%>&no=<%=information.getInfoBoardNo()%>'">이전으로</button>
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

			if ($("#content2").val().trim().length == 0) {
				alert("내용을 입력해 주세요.");
				$("#content2").focus();
				return false;
			}
		}
		
		// 카테고리 초기값 지정
													// item : 현재 접근한 요소
		$("#category > option").each(function(index, item){
			if($(item).val() == "<%=information.getCategoryName()%>"){
				$(item).prop("selected","true");
			}
		});
		
		// 이미지 배치
		$(function(){
			
			<% 
				String src = null;
			// 썸네일 이미지가 없는 경우 fList가 null이 되어 에러 발생
			if(fList != null){
					for(Attachment at : fList){
						src = request.getContextPath()+"/resources/uploadImages/"+at.getFileChangeName();
				%>
					var imgId;
					switch (<%=at.getFileLevel()%>) {
				    case 0: imgId = "#titleImg"; break;
				    case 1: imgId = "#contentImg1"; break;
				    case 2: imgId = "#contentImg2"; break;
				    case 3: imgId = "#contentImg3"; break;
			 		}
					
					if(imgId != undefined){
						$(imgId).attr("src", "<%=src%>")
					}
			<% } } %>
		});
		
		
		 // 이미지 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
	    $(function () {
	       $("#fileArea").hide();

	      $("#titleImgArea").click(function () {
	        $("#img1").click();
	      });
	      $("#contentImgArea1").click(function () {
	        $("#img2").click();
	      });
	      $("#contentImgArea2").click(function () {
	        $("#img3").click();
	      });
	      $("#contentImgArea3").click(function () {
	        $("#img4").click();
	      });

	      $(".boardImg").on("click",function(){
	    	  var index =  $(this).children("img").prop("id") + 1;
	        console.log(index);
	        $("#img" + index).click();
	      });

	        

	    });

	    // 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
	    function LoadImg(value, num) {
	      if (value.files && value.files[0]) {
	        var reader = new FileReader();
	        // 자바스크립트 FileReader
        	// 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는 File 혹은 Blob객체를 이용해 파일의 내용을 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 객체
					
        	// FileReader.onload
					// load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료 되었을 때마다 발생합니다.
	        reader.onload = function (e) {
	        	//console.log(e.target.result);
	        	// e.target.result
	        	// -> 파일 읽기 동작을 성공한 객체에(fileTag) 올라간 결과(이미지 또는 파일)
	        	
	          switch (num) {
	            case 1:
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
