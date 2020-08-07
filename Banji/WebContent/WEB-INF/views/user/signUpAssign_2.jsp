<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8">
        <!-- meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/ -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>회원가입</title>
        <!-- Bootstrap -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
        <script src="http://code.jquery.com/jquery.js"></script>
        <!-- 모든 합쳐진 플러그인을 포함하거나 (아래) 필요한 각각의 파일들을 포함하세요 -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <!-- Respond.js 으로 IE8 에서 반응형 기능을 활성화하세요 (https://github.com/scottjehl/Respond) -->
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <style>
    /* 인피니티산스 Regular */
    @font-face {
        font-family: "InfinitySans-RegularA1";
        src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/InfinitySans-RegularA1.woff") format("woff");
        font-weight: normal;
        font-style: normal;
        }
        *{
            font-family: "InfinitySans-RegularA1";
        }
        #btn, #btn2{
            background-color:  #ffce54; 
            border: none;
        }
        #back{
           height:10px;
        }
        #group{
           margin:auto;
        }
    </style>
    <!-- sweetalert : alert창을 꾸밀 수 있게 해주는 라이브러리 https://sweetalert.js.org/ -->
  		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <div class="container"><!-- 좌우측의 공간 확보 -->
            <!-- 헤더 들어가는 부분 -->
            <div id="back"></div>
            <div>
            <a href="<%=request.getContextPath()%>">
            <img src="<%=request.getContextPath()%>/resources/img/logo_main.png" class="mx-auto d-block" id="main-logo" width="180px">
            </a>
            </div>
            <!--// 헤더 들어가는 부분 -->
            <!-- 모달창 -->
            <div class="modal fade" id="defaultModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h5 class="modal-title"></h5>
                        </div>
                        <div class="modal-body">
                            <p class="modal-contents"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            <!--// 모달창 -->
            <hr/>
            <!-- 본문 들어가는 부분 -->
            <h2>2. 회원가입</h2>
            <br>
            <form class="form-horizontal" role="form" method="post" action="<%=request.getContextPath()%>/user/signUp.do" onsubmit="return validate();">
                <div class="form-group" id="divId">
                    <label for="inputId" class="col-lg-2 control-label">아이디</label>
                    <span id="checkId">&nbsp;</span>
                    <div class="col-lg-10">
                        <input type="text" class="form-control onlyAlphabetAndNumber" id="id" name="id" data-rule-required="true" placeholder="아이디는 첫글자 영어 소문자,이후 영어 대/소문자, 6글자 이상 숫자로 12자 이내입니다." maxlength="30">
                    </div>
                </div>
                <div class="form-group" id="divPassword">
                    <label for="password" class="col-lg-2 control-label">비밀번호</label>
                    <span id="checkPwd">&nbsp;</span>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id="password" name="pwd" data-rule-required="true" placeholder="비밀번호는 영어 대,소문자 + 숫자, 총 6~12글자입니다." maxlength="30">
                    </div>
                </div>
                <div class="form-group" id="divPasswordCheck">
                    <label for="password2" class="col-lg-2 control-label">비밀번호 확인</label>
                    <span id="checkPwd2">&nbsp;</span>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id="password2" name="pwd2" data-rule-required="true" placeholder="비밀번호 확인" maxlength="30">
                    </div>
                </div>
                <div class="form-group" id="divName">
                    <label for="inputName" class="col-lg-2 control-label">닉네임</label>
                    <span id="checkName">&nbsp;</span>
                    <div class="col-lg-10">
                        <input type="text" class="form-control onlyHangul" id="name" name="name" data-rule-required="true" placeholder="한글 세글자 이상으로 입력해주세요." maxlength="15">
                    </div>
                </div>

                <div class="form-group" id="divEmail">
                    <label for="inputEmail" class="col-lg-2 control-label">이메일</label>
                    <span id="checkEmail">&nbsp;</span>
                    <div class="col-lg-10">
                        <input type="email" class="form-control" id="email" name="email" data-rule-required="true" placeholder="이메일" maxlength="40">
                    </div>
                </div>
                <div class="form-group" id="divGrade">
                    <label for="inputEmail" class="col-lg-2 control-label">회원등급</label>
                    <div class="col-lg-10">
                        <input type="radio" id="user" name="grade" value="user" checked="checked">사용자
                        <input type="radio" id="user" name="grade" value="veterinarian">수의사
                        <input type="radio" id="user" name="grade" value="animaltrainer">훈련사
                        <input type="radio" id="user" name="grade" value="editor">에디터
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputQuestion" class="col-lg-2 control-label">보안질문</label>
                    <div class="col-lg-10">
                        <select class="form-control" id="question" name="question">
                            <option value="first">내 학창시절 별명은?</option>
                            <option value="second">가장 좋아했던 동화책의 제목은?</option>
                            <option value="third">첫 애완동물 이름은?</option>
                        </select>
                    </div>
                </div>
                <div class="form-group" id="divAnswer">
                    <label for="inputAnswer" class="col-lg-2 control-label">질문답변</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control onlyHangul" name="answer" id="answer" data-rule-required="true" placeholder="답변을 작성해주세요." maxlength="15">
                    </div>
                </div>
                <div class="form-group">
                   <div id="group">
                    <div class="col-lg-offset-2 col-lg-10 text-center">
                        <button type="submit" class="btn btn-primary color" id="btn" >회원가입</button>
                        <button type="reset" class="btn btn-primary color" id="btn2">취소</button>
                    </div>
                    </div>
                    </div>
            </form>
        
        <script>
        
        var signUpCheck = { 
   				"id":false,
				"password":false,
				"password2":false,
				"name":false
			};

	 	//********** 실시간 유효성 검사  ************/
		// 정규표현식
		// jQuery 변수 : 변수에 직접적으로 jQuery메소드를 사용할 수 있음.
		
        var $id = $("#id");
		var $password = $("#password");
		var $password2 = $("#password2");
		var $name = $("#name");
		var $email = $("#email");
		
        // id를 입력하는 경우 발생하는 이벤트
        $("#id").on("input", function(){
            // ajax를 이용한 아이디 유효성 검사
            var regExp = /^[a-z][a-zA-Z\d]{5,11}/;
            if(!regExp.test($id.val())){
                $("#checkId").text("유효하지 않은 아이디 형식입니다.").css("color", "red");
                signUpCheck.id=false;
                if($id.val().length == 0)   $("#checkId").text("");
            
            }else{ // 유효한 아이디 형식일 때
                $.ajax({
                    url : "idDupCheck.do",
                    data: {"id" : $id.val()},
                    type: "get",

                    success : function(result){
                        if(result == 0) {
                        	console.log(result);
                            $("#checkId").text("사용 가능한 아이디입니다.").css("color", "green");
                            signUpCheck.id=true;
                        }else{
                            $("#checkId").text("이미 사용중인 아이디입니다.").css("color", "red");
                            signUpCheck.id=false;
                        }
                    }
                    ,error : function() {
                        console.log("ajax 통신 실패");
                    }
                });
            }
        });

		// 비밀번호 유효성 및 일치 검사
		$("#password, #password2").on("input", function(){
			//영어 대,소문자 + 숫자, 총 6~12글자
			var regExp = /^[A-Za-z0-9]{6,12}$/;
			
			// 비밀번호1 유효성 검사
			if(!regExp.test($password.val())){ 
    		$("#checkPwd").text("비밀번호 형식이 유효하지 않습니다.").css("color","red");
    		signUpCheck.password = false;
    		if($password.val().length == 0)   $("#checkPwd").text("");
	   		 }else{
	    	$("#checkPwd").text("유효한 비밀번호 형식입니다.").css("color","green");
	    	signUpCheck.password = true;
    		}
			
			// 비밀번호1이 유효하지 않은 상태로 비밀번호 2를 작성하는 경우
			if(!signUpCheck.password && $password2.val().length > 0){
				swal("유효한 비밀번호를 작성해 주세요.");
				$password2.val("");
				$password.focus();
			}else if(signUpCheck.password && $("#password2").val().length > 0){
				if($("#password").val().trim() != $("#password2").val().trim()){
					$("#checkPwd2").text("비밀번호 불일치입니다.").css("color","red");
					signUpCheck.password2 = false;

					if($("#password2").val().length == 0)   $("#checkPwd2").text("");
					
				}else{
					$("#checkPwd2").text("비밀번호가 일치합니다.").css("color","green");
					signUpCheck.password2 = true;
				}
			}
			
		});

		// 이름 유효성 검사
		$name.on("input", function(){
			var regExp =  /^[가-힣]{3,}$/; // 한글 세 글자 이상
			
			if(!regExp.test($(this).val())){ // 입력한 이름이 유효하지 않은 경우
				$("#checkName").text("한글 세글자 이상을 입력하세요.").css("color","red");
				if($name.val().length == 0)   $("#checkName").text("");
			}else{
				$("#checkName").text("정상적으로 입력되었습니다.").css("color","green");
				signUpCheck.name = true;
			}
			
		});
		

    
		// 이메일 유효성 검사
		$email.on("input", function(){
			var regExp =  /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/; // 4글자 아무단어 @ 아무단어 . * 3
			if(!regExp.test($email.val())){
				$("#checkEmail").text("이메일 형식이 유효하지 않습니다.").css("color","red");
				signUpCheck.email = false;
			}else{
				$("#checkEmail").text("정상 입력").css("color","green");
				signUpCheck.email = true;
			}
		});
		
		
	// submit 동작
	function validate(){
		
		for(var key in signUpCheck){
			if(!signUpCheck[key]){
				
				var msg;
				switch(key){
				case "id" : msg="아이디가 ";  break;
				case "password" : case "password2": msg="비밀번호가 ";  break;
				case "name" : msg="이름이 ";  break;
				case "email" : msg="이메일이 ";  break;
				}
				
				alert(msg + "유효하지 않습니다.");
				var el = "#"+key;
				$(el).focus();
				return false;
			}
		}
	}
	
        </script>
            <hr/>
            <!-- 푸터 들어가는 부분 -->
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
            <!--// 푸터 끝나는 부분 -->
        </div>
    </body>
</html>
 