<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

  <meta charset="UTF-8" />

  <!-- Required meta tags -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />
  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
  <title>head&nav</title>
  <style>
  
/* -------------- body -------------- */

* {
/* border: 1px solid black; */
margin: 0;
padding: 0;
}

.row>div {
width: 100%;
height: 100%;
}

/* -------------- header -------------- */
header{
position: fixed;
height : 157px;
width: 100%;
z-index : 100;
background-color : white;
}
#main-logo{
padding: 11px;
}
#header button {
float: right;
margin: 1em;
width: 6em;
height: 2em;
border: 1px solid lightgray;
font-family: "InfinitySans-RegularA1";
font-size: 0.7em;
color: lightgray;
background-color: transparent;
}



/* -------------- nav -------------- */
.dropdown:hover .dropdown-menu { 
display: block;
margin-top: 0;
}

#nav{
border-top: 1px solid lightgray;
border-bottom: 1px solid lightgray;
}

#nav * {
font-family: "InfinitySans-RegularA1";
text-align: center;
vertical-align: middle;
border: none;
}

#nav a {
width: 10rem;
}

#nav li {
width: 10%;
}

.nav-link {
color: #3a3847;
width: 100%;
}

.nav-link:hover {
color: #ffce54;
border: none;
border-bottom: 3px solid !important;
}

.dropdown-menu {
border-top: 5px solid transparent !important;
width: 10rem;
background-color: rgb(250, 250, 250);
box-shadow: 0 10px 10px lightgrey;
padding-bottom: 20px;
}

.dropdown-item {
font-size: small;
margin-top: 10px;
background-color: transparent;
opacity: 0.7;
}

.dropdown-item:hover {
background-color: #ffce54;
color: white;
}
  </style>
<body>
  <header>
    <div class="container-fluid">
      <!--  ------------ header ------------ -->
      <div class="row" id="header">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
          <a href="#">
            <img src="<%=request.getContextPath()%>/resources/img/logo_main.png" width="200px" class="mx-auto d-block" id="main-logo">
          </a>
        </div>
        <div class="col-md-4">
          <button type="button" onclick="location.href='<%=request.getContextPath()%>/user/signUpAssign1.do'">회원가입</button>
          <button type="button">로그인</button>
        </div>
      </div>
    </div>

    <!--  ------------ nav ------------ -->
    <div class="row" id="nav">
      <div class="col-md-12">
        <ul class="nav nav-tabs justify-content-center" id="nav-wrapper">
          <li class="nav-item dropdown">
            <a class="nav-link" href="<%=request.getContextPath()%>/user/introduce.do" role="button" aria-haspopup="true" aria-expanded="false">구성원 소개</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link" href="#" role="button" aria-haspopup="true" aria-expanded="false">정보</a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="#">음식</a>
              <a class="dropdown-item" href="#">견종백과</a>
              <a class="dropdown-item" href="#">건강상식</a>
              <a class="dropdown-item" href="#">교육/훈련</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link" href="community" role="button" aria-haspopup="true" aria-expanded="false">커뮤니티</a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="community">자유 게시판</a>
              <a class="dropdown-item" href="question">Q&A</a>
              <a class="dropdown-item" href="<%=request.getContextPath()%>/review/review.do">사용 후기</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link" href="#" role="button" aria-haspopup="true" aria-expanded="false">이벤트</a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="#">진행중인 이벤트</a>
              <a class="dropdown-item" href="#">종료된 이벤트</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link" href="#" role="button" aria-haspopup="true" aria-expanded="false">마이페이지</a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="#">회원정보 수정</a>
              <a class="dropdown-item" href="#">비밀번호 수정</a>
              <a class="dropdown-item" href="#">회원 탈퇴</a>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </header>



  <script>
    $(function () {
      $(".nav-link").on("mouseenter", function () {
        var idx = $(this).index(".nav-link");

        $(".nav-link").each(function (index, item) {
          if (index == idx) {
            $(item)
              .css("color", "#ffce54")
              .css("border-bottom", "3px solid #ffce54");
          } else {
            $(item).css("color", "#3a3847").css("border-bottom", "none");
          }
        });
      });

      $("#nav").on("mouseleave", function () {
        $(".nav-link")
          .css("color", "#3a3847")
          .css("border-bottom", "3px solid transparent");
      });
    });
  </script>



  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
    integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
    crossorigin="anonymous"></script>
</body>

</html>