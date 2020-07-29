<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 완료 - 전문가</title>
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
    <!-- sweetAlert창 추가 -->
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script>
		<%
  		String msg = (String)(request.getSession().getAttribute("msg"));
  		String status = (String)(request.getSession().getAttribute("status"));
  		String text = (String)(request.getSession().getAttribute("text"));
  		%>
  		
  		<% if (msg != null){ %>
	  		swal({
	  			icon : "<%=status%>",
	  			title : "<%=msg%>",
	  			text : "<%=text != null ? text : ""%>"
	  		});
  		<%
  			// Session에 존재하는 특정 키값의 속성 제거
  			session.removeAttribute("msg");
  			session.removeAttribute("status");
  			session.removeAttribute("text");
  		}
  		%>
    </script>
    <div class="header">       
       <img src="<%=request.getContextPath()%>/resources/img/logo_main.png" class="mx-auto d-block" id="main-logo" width="200px">
    </div>
       <div id="section">
       <h1>전문가 승인이 진행중입니다.<br> 관리자가 승인시 회원 가입이 완료됩니다.</h1>
              <button type="button" id="main" onclick="location.href='<%=request.getContextPath()%>'">홈으로</button>
    </div> 
</body>
</html>