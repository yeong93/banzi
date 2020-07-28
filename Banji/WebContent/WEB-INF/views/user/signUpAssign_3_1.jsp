<%@page import="com.kh.banzi.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	User signUp = (User)session.getAttribute("signUpUser");
%>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 완료 - 사용자</title>
<style>
 @font-face {
      font-family: "InfinitySans-RegularA1";
      src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/InfinitySans-RegularA1.woff") format("woff");
      font-weight: normal;
      font-style: normal;
    }
   *{
	font-family : InfinitySans-RegularA1;}
	.header {width:800px; height:150px; margin:0 auto;background:white; }

    #section{
        text-align: center;
    }
    #main {margin: auto auto; margin-top:40px; margin-bottom:100px; clear:both; width:300px; height:45px;background-color :#808080; color:white
    }
</style>
</head>
<body>
		<div id="header">
        <a href="<%=request.getContextPath()%>">
          <img src="<%=request.getContextPath()%>/resources/img/logo_main.png" width="200px" class="mx-auto d-block" id="main-logo">
        </a>
        </div>
       <div id="section">
       <h1><%=signUp.getUserName()%>님의 회원가입이 완료되었습니다!</h1>
              <button type="button" id="main" onclick="location.href='<%=request.getContextPath()%>'">홈으로</button>
    </div> 
</body>
</html>