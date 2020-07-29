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
    #back{
   	height:10px;
   	}
</style>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />
  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
    <div id="back"></div>
    <div class="header">       
       <img src="<%=request.getContextPath()%>/resources/img/logo_main.png" class="mx-auto d-block" id="main-logo" width="190px">
    </div>
       <div id="section">
       <br>
       <h3>전문가 승인이 진행중입니다.<br> 관리자가 승인시 회원 가입이 완료됩니다.</h3>
              <button type="button" id="main" onclick="location.href='<%=request.getContextPath()%>'">홈으로</button>
    </div> 
</body>
</html>