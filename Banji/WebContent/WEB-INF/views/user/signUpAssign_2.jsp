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
    </style>
    </head>
    <body>
        <div class="container"><!-- 좌우측의 공간 확보 -->
            <!-- 헤더 들어가는 부분 -->
            <div>
            <a href="<%=request.getContextPath()%>">
            <img src="<%=request.getContextPath()%>/resources/img/logo_main.png" class="mx-auto d-block" id="main-logo" width="200px">
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
            <form class="form-horizontal" role="form" method="post" action="<%=request.getContextPath()%>/user/signUp.do">
                <div class="form-group" id="divId">
                    <label for="inputId" class="col-lg-2 control-label">아이디</label>
                    <span id="checkId">&nbsp;</span>
                    <div class="col-lg-10">
                        <input type="text" class="form-control onlyAlphabetAndNumber" id="id" name="id" data-rule-required="true" placeholder="아이디는 첫글자 영어 소문자,이후 영어 대/소문자, 숫자로 12자 이내입니다." maxlength="30">
                    </div>
                </div>
                <div class="form-group" id="divPassword">
                    <label for="inputPassword" class="col-lg-2 control-label">비밀번호</label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id="password" name="pwd" data-rule-required="true" placeholder="비밀번호" maxlength="30">
                    </div>
                </div>
                <div class="form-group" id="divPasswordCheck">
                    <label for="inputPasswordCheck" class="col-lg-2 control-label">비밀번호 확인</label>
                    <span id="checkPwd">&nbsp;</span>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id="passwordCheck" name="password2" data-rule-required="true" placeholder="비밀번호 확인" maxlength="30">
                    </div>
                </div>
                <div class="form-group" id="divName">
                    <label for="inputName" class="col-lg-2 control-label">닉네임</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control onlyHangul" id="name" name="name" data-rule-required="true" placeholder="한글만 입력해주세요." maxlength="15">
                    </div>
                </div>

                <div class="form-group" id="divEmail">
                    <label for="inputEmail" class="col-lg-2 control-label">이메일</label>
                    <div class="col-lg-10">
                        <input type="email" class="form-control" id="email" name="email" data-rule-required="true" placeholder="이메일" maxlength="40">
                    </div>
                </div>
                <div class="form-group" id="divGrade">
                    <label for="inputEmail" class="col-lg-2 control-label">회원등급</label>
                    <div class="col-lg-10">
                        <input type="radio" id="user" name="grade" value="user">사용자
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
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="submit" class="btn btn-primary color" id="btn" >회원가입</button>
                        <button type="reset" class="btn btn-primary color" id="btn2">취소</button>
                    </div>
                </div>
            </form>
        
        <script>
        
            $(function(){
                //모달을 전역변수로 선언
                var modalContents = $(".modal-contents");
                var modal = $("#defaultModal");
                
                $('.onlyAlphabetAndNumber').keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val($(this).val().replace(/[^_a-z0-9]/gi,'')); //_(underscore), 영어, 숫자만 가능
                    }
                });
                
                $(".onlyHangul").keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val(inputVal.replace(/[a-z0-9]/gi,''));
                    }
                });
            
                $(".onlyNumber").keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val(inputVal.replace(/[^0-9]/gi,''));
                    }
                });
                
                //------- 검사하여 상태를 class에 적용
                $('#id').keyup(function(event){
                    
                    var divId = $('#divId');
                    
                    if($('#id').val()==""){
                        divId.removeClass("has-success");
                        divId.addClass("has-error");
                    }else{
                        divId.removeClass("has-error");
                        divId.addClass("has-success");
                    }
                });
                
                $('#password').keyup(function(event){
                    
                    var divPassword = $('#divPassword');
                    
                    if($('#password').val()==""){
                        divPassword.removeClass("has-success");
                        divPassword.addClass("has-error");
                    }else{
                        divPassword.removeClass("has-error");
                        divPassword.addClass("has-success");
                    }
                });
                
                $('#passwordCheck').keyup(function(event){
                    
                    var passwordCheck = $('#passwordCheck').val();
                    var password = $('#password').val();
                    var divPasswordCheck = $('#divPasswordCheck');
                    
                    if((passwordCheck=="") || (password!=passwordCheck)){
                        divPasswordCheck.removeClass("has-success");
                        divPasswordCheck.addClass("has-error");
                    }else{
                        divPasswordCheck.removeClass("has-error");
                        divPasswordCheck.addClass("has-success");
                    }
                });
                
                $('#name').keyup(function(event){
                    
                    var divName = $('#divName');
                    
                    if($.trim($('#name').val())==""){
                        divName.removeClass("has-success");
                        divName.addClass("has-error");
                    }else{
                        divName.removeClass("has-error");
                        divName.addClass("has-success");
                    }
                });

                $('#email').keyup(function(event){
                    
                    var divEmail = $('#divEmail');
                    
                    if($.trim($('#email').val())==""){
                        divEmail.removeClass("has-success");
                        divEmail.addClass("has-error");
                    }else{
                        divEmail.removeClass("has-error");
                        divEmail.addClass("has-success");
                    }
                });
                
                //------- validation 검사
                $( "form" ).submit(function( event ) {
                    
                    var divId = $('#divId');
                    var divPassword = $('#divPassword');
                    var divPasswordCheck = $('#divPasswordCheck');
                    var divName = $('#divName');
                    var divEmail = $('#divEmail');
                    
                    
                    //아이디 검사
                    if($('#id').val()==""){
                        modalContents.text("아이디를 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                        
                        divId.removeClass("has-success");
                        divId.addClass("has-error");
                        $('#id').focus();
                        return false;
                    }else{
                        divId.removeClass("has-error");
                        divId.addClass("has-success");
                    }
                    
                    //패스워드 검사
                    if($('#password').val()==""){
                        modalContents.text("패스워드를 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                        
                        divPassword.removeClass("has-success");
                        divPassword.addClass("has-error");
                        $('#password').focus();
                        return false;
                    }else{
                        divPassword.removeClass("has-error");
                        divPassword.addClass("has-success");
                    }
                    
                    //패스워드 확인
                    if($('#passwordCheck').val()==""){
                        modalContents.text("패스워드 확인을 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                        
                        divPasswordCheck.removeClass("has-success");
                        divPasswordCheck.addClass("has-error");
                        $('#passwordCheck').focus();
                        return false;
                    }else{
                        divPasswordCheck.removeClass("has-error");
                        divPasswordCheck.addClass("has-success");
                    }
                    
                    //패스워드 비교
                    if($('#password').val()!=$('#passwordCheck').val() || $('#passwordCheck').val()==""){
                        modalContents.text("패스워드가 일치하지 않습니다.");
                        modal.modal('show');
                        
                        divPasswordCheck.removeClass("has-success");
                        divPasswordCheck.addClass("has-error");
                        $('#passwordCheck').focus();
                        return false;
                    }else{
                        divPasswordCheck.removeClass("has-error");
                        divPasswordCheck.addClass("has-success");
                    }
                    
                    //이름
                    if($('#name').val()==""){
                        modalContents.text("이름을 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                        
                        divName.removeClass("has-success");
                        divName.addClass("has-error");
                        $('#name').focus();
                        return false;
                    }else{
                        divName.removeClass("has-error");
                        divName.addClass("has-success");
                    }
                    
                    //이메일
                    if($('#email').val()==""){
                        modalContents.text("이메일을 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                        
                        divEmail.removeClass("has-success");
                        divEmail.addClass("has-error");
                        $('#email').focus();
                        return false;
                    }else{
                        divEmail.removeClass("has-error");
                        divEmail.addClass("has-success");
                    }

                    // 보안답변
                    if($('#answer').val()==""){
                        modalContents.text("보안답변을 입력하여 주시기 바랍니다.");
                        modal.modal('show');
                        
                        divName.removeClass("has-success");
                        divName.addClass("has-error");
                        $('#answer').focus();
                        return false;
                    }else{
                        divName.removeClass("has-error");
                        divName.addClass("has-success");
                    }
                });

            });
            // 비밀번호 일치 여부 검사
    		$("#passwordCheck").on("input",function() {
    			if($("#password").val().trim()!= $("#passwordCheck").val().trim()){
    				$("#checkPwd").text("비밀번호가 일치하지 않습니다.").css("color","red");
    			}else{
    				$("#checkPwd").text("비밀번호가 일치합니다.").css("color","green");
    			}
    	  		});
            

            // id를 입력하는 경우 발생하는 이벤트
            var $id = $("#id");
            
         	$("#id").on("input", function(){
             // ajax를 이용한 아이디 유효성 검사
             var regExp = /^[a-z][a-zA-Z\d]{5,11}/;
             if(!regExp.test($id.val())){
                 $("#checkId").text("유효하지 않은 아이디 형식입니다.").css("color", "red");

             }else{ // 유효한 아이디 형식일 때
                 $.ajax({
                     url : "idDupCheck.do",
                     data: {"id" : $id.val()},
                     type: "get",

                     success : function(result){
                         if(result ==0) {
                             $("#checkId").text("사용 가능한 아이디입니다.").css("color", "green");
                         }else{
                             $("#checkId").text("이미 사용중인 아이디입니다.").css("color", "red");
                         }
                     }
                     ,error : function() {
                         console.log("ajax 통신 실패");
                     }
                 });
             }
         });

            // 입력된 값이 지워지면 텍스트 삭제 -------------- > 안됨
         	$(document).ready(function() { 
         		 if($id.val().trim() ==""){
               	 $("#checkId").text("");
                }
         	});
            
			
        </script>
            <hr/>
            <!-- 푸터 들어가는 부분 -->
            <%@ include file="/WEB-INF/views/common/footer.jsp" %>
            <!--// 푸터 끝나는 부분 -->
        </div>
    </body>
</html>
 