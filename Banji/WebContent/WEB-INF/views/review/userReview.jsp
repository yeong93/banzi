<%@page import="com.kh.banzi.review.model.vo.PageInfo"%>
<%@page import="com.kh.banzi.review.model.vo.Review"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<% 
 	PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");
 	List<Review> rList = (List<Review>)request.getAttribute("rList");
	String type = request.getParameter("type");
%>

<!DOCTYPE html>
<html lang="ko">
<head>  
    <meta charset="UTF-8">
<title></title>
<style>
	        /* 여기어때 잘난체 */
        @font-face {
            font-family: "yg-jalnan";
            src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff") format("woff");
            font-weight: normal;
            font-style: normal;
        }

        @font-face {
            font-family: 'KyoboHand';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/KyoboHand.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        @font-face { 
            font-family: 'IBMPlexSansKR-Light';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Light.woff') format('woff');
            font-weight: normal; 
            font-style: normal; 
        }

        @font-face { 
            font-family: 'GmarketSansMedium'; 
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
            font-weight: normal; 
            font-style: normal; 
        }

        
        body { margin: 0; padding: 0;}
        .empty { width: 100%; height: 200px;}

        /* title */
        .review-1 {
            font-size: 30px;
            text-align: center;
            margin: 10px;
            font-weight: bolder;
            font-family: "yg-jalnan";
            color: rgb(87, 87, 87);
            margin-bottom: 0px;
        }

        .line-height {
            margin: auto;
            width: 130px;
            border: 1px solid grey;
            margin-bottom: 20px;
        }

        /* button */
        .review-button-area {
            width: 100%;
            height: 80px;
            display: flex;
            justify-content: center;
        }

        .review-button {
            width: 70px;
            height: 26px;
            border-radius: 13px;
            position: relative;
            background-color: #ffce54;
            color: white;
            margin: 20px 16px 20px 16px;
        }

        .review-button:hover {
            background-color: rgb(250, 156, 88);
            cursor: pointer;
        }

        .review-button>a {
            position: absolute;
            color: white;
            font-family: 'KyoboHand';
            font-size: 20px;
            line-height: 26px;
            text-align: center;
            margin: auto;
            top: 0; bottom: 0; left: 0; right: 0;
        }


        /* review-box */
        .container {
            position: relative;
            width: 100%;
            height: 400px;
            margin-bottom: 0px;
            display: flex;
            justify-content: center;
        }

        .review-box {
            width: 260px;
            height: 350px;
            border-radius: 14px;
            box-sizing: border-box;
            background-color: rgba(238, 238, 238, 0.2);
            border: 4px solid rgba(250, 156, 88, 0.3);
            margin: 25px;
            display: inline-block;
        }

        .review-box:hover {
            background-color: rgba(165, 165, 161, 0.1)
        }

        .review-box-top {
            height: 22%;
            box-sizing: border-box;
            position: relative;
            border-bottom: 3px solid rgba(250, 156, 88, 0.3);
        }

        .circle-img {
            border: 1px solid black;
            width: 60px;
            height: 60px;
            position: relative;
            float: left;
            border-radius: 100%;
            margin-top: 6px;
            margin-left: 20px;
        }

        .circle-img>img {
            margin: 4px 0px 0px 6px;
            width: 80%;
        }

        .review-title {
            text-align: left;
            height: 100%;
            width: 60%;
            box-sizing: border-box;
            float: left;
            margin-left: 18px;
        }

        .review-title-name {
            height: 60%;
            width: 100%;
            font-size: 18px;
            line-height: 48px;
            font-family: 'GmarketSansMedium';
        }

        .review-title-heart {
            height: 40%;
            width: 100%;
            line-height: 15px;
            display: flex;
            font-family: 'GmarketSansMedium';
        }

        .review-box-center {
            height: 26%;
            width: 100%;
            text-align: center;
            padding-top: 5px;
        }

        .review-box-inside {
            width: 64px;
            height: 64px;
            display: inline-block;
            margin: 7px 4px 3px 4px;
            border: 0.5px solid rgb(150, 150, 150);
            border-radius: 2px;
        }

        .review-box-bottom {
            height: 50%;
            box-sizing: border-box;
            text-align: left;
        }

        .review-box-bottom>p {
            font-family: "IBMPlexSansKR-Light";
            margin: 0px 14px 14px 14px;
            font-size: 14px;
        }

        /* button */
        .button-area {
            height: 100px;
            width: 52%;
            margin: auto;
        }

        #btn {
            background-color: #ffce54;
            border: none;
            margin-top: 40px;
            margin-right: 110px;
            float: right;
        }

        /* modal*/
        
        .modal-header {
            padding-bottom: 4px;
        }

        .modal-title {
            width: 80%;
            height: 100%;
        }

        .modal-writer {
            width: 20%;
            height: 100%;
            text-align: right;
            display: inline-block;
        }

        .reply-writer {
            display: inline-block;
            width: 12%;
        }

        .reply-content {
            /* margin-left: 5%; */
            width: 85%;
            display: inline-block;
        }

        .reply-input {
            float: left;
            width: 85%;
        }

        .modal-footer {
            display: inline-block;
        }

        /* heart */
        label {
            font-size: 1.1rem !important;
            float: right;
            color: #bababa;
            padding-right: 2px;
            flex-direction: row;
        }
        
        input[type="radio"] {
            display: none;
        }
        
        input[type="radio"]:checked ~ label {
            color: red;
        }
        
        /*
        .ratingbox:hover input[type="radio"]:checked ~ label  {
            color: #eed490;
        }



        label:hover, label:hover ~ label {
            color: red !important;
            
        }
        */
</style>
</head>

<body>
	<%@ include file="../common/header.jsp"%>

	<p class="empty"></p>

    <!-- title-->
    <div class="review-1">사용후기</div>
    <p class="line-height"></p>

    <!-- 버튼부분 -->
    <div class="review-button-area">
        <div class="review-button"><a>병 원</a></div>
        <div class="review-button"><a>사 료</a></div>
        <div class="review-button"><a>간 식</a></div>
        <div class="review-button"><a>용 품</a></div>
    </div>

	<%if(rList.isEmpty()){ %>
    	<table><tr><td colspan="6">존재하는 게시글이 없습니다.</td></tr></table>
  	<% }else{ %>
   	
   	
    <!-- 1 -->
	<%int num=0; %>
	   	<%for(int i=0; i<3; i++){ %>
	    <div class="container">
	    	<%for(int j=0; j<3; j++){ %>
   		
        <!--review box 1-1-->
        <div class="review-box" data-target="#myModal" data-toggle="modal">
            <div class="review-box-top">
                <div class="circle-img"><img src="<%=request.getContextPath()%>/resources/img/개아이콘.png"></div>
                
                <div class="review-title">
                    <div class="review-title-name"><%=rList.get(num).getReviewWriterNo()%>님</div>
                    <div class="review-title-heart">
                       <div class="ratingbox clearfix">
                           <input type="radio" name="rating" id="rating-1" value="1"/><label for="rating-1" class="fa fa-heart">♥</label>
                           <input type="radio" name="rating" id="rating-2" value="2"/><label for="rating-2" class="fa fa-heart">♥</label>
                           <input type="radio" name="rating" id="rating-3" value="3"/><label for="rating-3" class="fa fa-heart">♥</label>
                           <input type="radio" name="rating" id="rating-4" value="4"/><label for="rating-4" class="fa fa-heart">♥</label>
                           <input type="radio" name="rating" id="rating-5" value="5"/><label for="rating-5" class="fa fa-heart">♥</label>
                        </div>
                    </div>
                </div>
            </div> <!-- review_top end -->

            <div class="review-box-center">
                <div class="review-box-inside"></div>
                <div class="review-box-inside"></div>
                <div class="review-box-inside"></div>
            </div>

            <div class="review-box-bottom">
                <p>
                	<%=rList.get(num).getReviewContent() %>
                </p>
            </div>
        </div> <!-- review-box end -->
		<%num++; } %>
    	</div> <!-- container end -->
       <%} %>
      <%} %>

   <% if(loginUser != null){ %>
    <div class="button-area">
        <button type="button" class="btn btn-info btn-lg" id="btn"
            onclick="location.href='writeReviewForm.do?type=<%=type%>';">글쓰기</button>
    </div>
    <%} %>



    <!-- Modal -->
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">글제목</h4>
                    <p class="modal-writer">글쓴이</p>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <img width="100px" height="100px" src="98_세미프로젝트/2931786.jpg">
                    <img width="100px" height="100px" src="98_세미프로젝트/2931786.jpg">
                    <img width="100px" height="100px" src="98_세미프로젝트/2931786.jpg">
                    <br><br>

                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart-fill" fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                            d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z" />
                    </svg>
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart-fill" fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                            d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z" />
                    </svg>
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart-fill" fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                            d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z" />
                    </svg>
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart-fill" fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                            d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z" />
                    </svg>
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart" fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                            d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z" />
                    </svg>
                    <p>
                        내용 section
                    </p>
                    <hr>
                    <p class="reply-writer">홍길동</p>
                    <p class="reply-content">좋은 정보 감사합니다.</p> <br>
                    <p class="reply-writer">홍길동</p>
                    <p class="reply-content">도움이 되는 글입니다. 덕분에 좋은 정보 얻어가요~</p>
                </div>
                <div class="modal-footer">
                    <input type="text" class="reply-input">
                    <button type="button">write</button>
                </div>
            </div>

        </div>
    </div>

	<%@ include file="../common/footer.jsp"%>
</body>
<script>

//하트 체크 (아직 fix못시킴)

$(function() {
	$("#rating-" + <%= rList.get(0).getReviewRating()%>).prop("checked", true);

	});


</script>
</html>