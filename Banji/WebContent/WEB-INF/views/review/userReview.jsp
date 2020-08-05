<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.kh.banzi.review.model.vo.Attachment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.banzi.review.model.vo.PageInfo"%>
<%@page import="com.kh.banzi.review.model.vo.Review"%>
<%@page import="java.util.List"%>
<% 
 	PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");
 	List<Review> rList = (List<Review>)request.getAttribute("rList");
 	ArrayList<Attachment> fList = (ArrayList<Attachment>)request.getAttribute("fList");
	String type = request.getParameter("type");

	int currentPage = pInfo.getCurrentPage();
	int listCount = pInfo.getListCount();
	int maxPage = pInfo.getMaxPage();
	int startPage = pInfo.getStartPage();
	int endPage = pInfo.getEndPage();
	int boardType = pInfo.getBoardType();
	
	int prev = (currentPage-1)/10*10; // < 버튼
	int next = (currentPage+9)/10*10+1; // > 버튼
%>

<!DOCTYPE html>
<html lang="ko">
<head>  
    <meta charset="UTF-8">
     <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>사용후기</title>
<style>
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
        
        .review-button:hover>a {
            cursor: pointer;
            text-decoration:none;
            color: white;
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
            background-color: rgba(149, 149, 149, 0.1);
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
            margin: 4px 0px 0px 7px;
            width: 75%;
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
            margin: 7px 4px 3px 4px;
            
            border-radius: 2px;
            display: inline-flex;
            
        }
        
        .review-box-inside  img{
        	width: 100%;
        	height: 100%;
        	 display: inline-flex;
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

        /* 카테고리 button */
        #btn {
            background-color: #ffce54;
            border: none;
            float: right;
            margin-right: 240px;
        }
		
		/* 페이징바 */
    	.pagination {
            justify-content: center;
        }
        #searchForm{
            position: relative;
        }

        #searchForm>*{
            top : 0;
        }
        
        .boardTitle > img{
        	width: 50px;
        	height: 50px;
        }
      	
      	/* 평가 별*/
      	.reviewStar .rating {
      		color: #bababa;
      	}
      	
        .button-area {
            height: 0px;
            width: 65%;
            margin: auto;
        }
        
        .button-area .btn{
        	width: 80px;
		    height: 38px;
		    padding: 0px;
		    border-radius: 10px;
		    font-size: 18px;
		    
		    font-family: 'GmarketSansMedium'; 
        }
        
        .for-hidden{
       	 margin:0px;
        }
</style>
</head>

<body>
	<%@ include file="../common/header.jsp"%>

	<p class="empty"></p>
    <div class="review-1">사용후기</div>
    <p class="line-height"></p>

    <!-- 버튼부분 -->
    <div class="review-button-area">
        <div class="review-button"><a href="<%=request.getContextPath()%>/review/reviewCategory.do?type=<%=type%>&category=1">병 원</a></div>
        <div class="review-button"><a href="<%=request.getContextPath()%>/review/reviewCategory.do?type=<%=type%>&category=2">사 료</a></div>
        <div class="review-button"><a href="<%=request.getContextPath()%>/review/reviewCategory.do?type=<%=type%>&category=3">간 식</a></div>
        <div class="review-button"><a href="<%=request.getContextPath()%>/review/reviewCategory.do?type=<%=type%>&category=4">용 품</a></div>
    </div>

	<%if(rList.isEmpty()){ %>
    	<table><tr><td colspan="6" align="center">존재하는 게시글이 없습니다.</td></tr></table>
  	<% }else{ %>
   	
    <!-- 1 -->
	<%int num=0; %>
	   	<%for(int i=0; i<3; i++){ %>
	    <div class="container">
	    	<%for(int j=0; j<3; j++){ %>
   		
        <!--review box 1-1-->
        <div class="review-box">
	    <p class="for-hidden" id="<%=rList.get(num).getReviewBoardNo() %>">
            <div class="review-box-top">
            	<% if(rList.get(num).getReviewBoardNo() %2 == 0){ %>
                <div class="circle-img"><img src="<%=request.getContextPath()%>/resources/img/개아이콘.png"></div>
                <%}else{ %>
                <div class="circle-img"><img src="<%=request.getContextPath()%>/resources/img/개아이콘2.png"></div>
                <%} %>
                <div class="review-title">
                    <div class="review-title-name"><%=rList.get(num).getUserName()%>님</div>
                    
                    <div class="review-title-heart">
                    <div class="reviewStar">
						<div class="rating" data-rate="<%=rList.get(num).getReviewRating()%>">
							<i class="fas fa-star"></i>
							<i class="fas fa-star"></i>
							<i class="fas fa-star"></i>
							<i class="fas fa-star"></i>
							<i class="fas fa-star"></i>	
						</div>
					</div>
                    </div>
                </div>
            </div> <!-- review_top end -->
			
			
            <div class="review-box-center">
            <!-- 이미지 미리보기 3개 -->
           		<% 
           			String path = request.getContextPath() + "/resources/uploadImages/";
           			String[] img = new String[3];
           			for(Attachment at : fList ) {
           			if(at.getParentBoardNo() == rList.get(num).getReviewBoardNo()){
           				switch(at.getFileLevel()){
           				case 0 : img[0] = path + at.getFileChangeName(); break;
           				case 1 : img[1] = path + at.getFileChangeName(); break;
           				case 2 : img[2] = path + at.getFileChangeName(); break;
           				}
           			}
           			
           		}%>
                <div class="review-box-inside">
                	<% if(img[0] != null) {%>
                		<img src = "<%=img[0] %>">
                	<% } %>
                </div>
                <div class="review-box-inside">
                	<% if(img[1] != null) {%>
                		<img src = "<%=img[1] %>">
                	<% } %>
                </div>
                <div class="review-box-inside">
                	<% if(img[2] != null) {%>
                		<img src = "<%=img[2] %>">
                	<% } %>
                </div>
            </div>
            
            <div class="review-box-bottom">
                <p><%=rList.get(num).getReviewContent() %></p>
            </div>
    	</p> <!-- 연결위한 div end -->
        </div> <!-- review-box end -->
		<%num++; } %>
    	</div> <!-- container end -->
       <%} %>
      <%} %>

  
		<% if(loginUser != null){ %>
   			 <div class="button-area">
		        <button type="button" class="btn btn-info btn-lg" id="btn" onclick="location.href='writeReviewForm.do?type=<%=type%>';">글쓰기</button>
            </div>
		<%} %>
	
			<!-- 페이징바 -->
	        <div style="clear:both">
	        	<ul class="pagination">
	        		<%if(currentPage>10){ %>
	        			<li>
	        				<a class="page-link"  href="<%=request.getContextPath()%>/review/review.do?type=<%=type%>&cp=1">&lt;&lt;</a>
	        			</li>
	        			<li>
	        			<a class="page-link" href="<%=request.getContextPath()%>/review/review.do?type=<%=type%>&cp=<%=prev%>">&lt;</a>
	        			</li>
	        			<%} %>

	        			<%for(int p=startPage; p<endPage; p++){ %>
	        				<%if(p == currentPage){ %>
	        					<li><a class="page-link"><%=p%></a></li>
	        				<%}else{ %>
	        					<li>
	        						<a class="page-link" href="<%=request.getContextPath()%>/review/review.do?type=<%=type%>&cp=<%=p%>"><%=p %></a>
	        					</li>
	        					
	        				<%} %>
	        			<%} %>
	        			
	        			<%if(next <maxPage){ %>
	        				<li>
	        					<a class="page-link" href="<%=request.getContextPath()%>/review/review.do?type=<%=type%>&cp=<%=next%>">&gt;</a>
	        				</li>
	        				<li>
	        					<a class="page-link" href="<%=request.getContextPath()%>/review/review.do?type=<%=type%>&cp=<%=maxPage%>">&gt;&gt;</a>
	        				</li>
	        			<%} %>
	        	</ul>
	        </div> <!-- 페이징바 end -->

	<%@ include file="../common/footer.jsp"%>
</body>

<script>
	// 평가 등급 (+targetScore값이 변경값)
	$(function(){
	    var rating = $('.reviewStar .rating');
	    rating.each(function(){
	        var targetScore = $(this).attr('data-rate');
	        console.log(targetScore);
	        $(this).find('svg:nth-child(-n+' + targetScore +')').css({color:'#FFD600'})
	    });
	});
	
	// 페이지 이전
	$(".review-box").on("click", function(){
	    var boardNo = $(this).children().attr("id");
        
        // boardType이 안보내지면 null뜸 why?
        location.href = "<%=request.getContextPath()%>/review/detailReview.do?type=<%=boardType%>&no=" + boardNo;
     }).on("mouseenter", function(){
   	  $(this).parent().css("cursor", "pointer");
     });

</script>
</html>