<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>



    <style>
           .footer {
            background: #ffffff;
            font-family: "yg-jalnan";
            color: #3a3847;
        }

        .footer .col-xs-12{
            margin-top: 1em;
        }

        .footer .contact i {
            font-size: 18px;
            font-family: "InfinitySans-RegularA1";
        }

        .footer .copyright p {
            border-top: 1px solid lightgray;
        }


        .footer img{
            width: 10em;
        }

        .footer p{
            font-family: "InfinitySans-RegularA1";
            color: #3a3847;
        }

        .footer h4{
            margin-bottom: 0.5em;
        }
       
    </style>
</head>

<body>
    

    <div class="footer">
        <div class="container-fluid">
            <div class="row mt-5">
                <div class="col copyright">
                    <p></p>
                </div>
            </div>
            <div class="row mt-3">
            	<div class="col-md-2">
                </div>
                <div class="col-md-3">
                    <img src="<%=request.getContextPath()%>/resources/img/logo_footer.png"">
                </div>
                <div class="col-md-3">
                </div>
                <div class="col-md-4 contact">
                    <h4 class="mt-md-0 mt-sm-4">contact us</h4>
                    <p>서울특별시 중구 남대문로 120 대일빌딩 2F</p>
                    <p>1544-9970</p>
                    <p>info@email.com</p>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col copyright">
                    <p class=""><small>© 2020. All Rights Reserved.</small></p>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>

</body>

</html>