<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BAN JI</title>
  <meta charset="UTF-8">
  <title>07.시맨틱태그</title>
  <style>
  		@font-face { 
            font-family: 'GmarketSansMedium'; 
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
            font-weight: normal; 
            font-style: normal; 
        }
        
        @font-face { 
        	font-family: 'InfinitySans-RegularA1'; 
	        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/InfinitySans-RegularA1.woff') format('woff');
	        font-weight: normal; font-style: normal; 
	    }
        
    	body { margin: 0; padding: 0;}
        .empty { width: 100%; height: 200px;}
        
        .container {
            position: relative;
            width: 85%;
            height: 180px;
            margin: auto;
            display: flex;
            justify-content: center;
        }

        .img-box{
            width: 33%;
            height: 90%;
            box-sizing: border-box;
            
            margin: 15px;
            display: inline-block;
            position: relative;
        }

        .img-box:hover{
            cursor: pointer;
            background-color: rgba(165, 165, 161, 0.2);
        }
       
        .img-box > svg{
            transform: translate(170%, 150%);
            color: orange;
        }
        
        .img-box > img{
           width: 100%;
           height: 100%;
        }

        .category{
            height: 18px;
            margin-left: 520px;
            font-size: 18px;
            margin-top: 20px;
			font-family: "InfinitySans-RegularA1";
			color: #5d5d5d;
          }
          
          .title-text{
         	font-family: 'GmarketSansMedium'; 
          	font-size: 20px;
          	text-align:center;
          	position: absolute;
          	margin:auto;
          	top:0;
          	bottom:0;
          	right:0;
          	left:0;
          }
          
          .title-text> img{
          	 width: 100%;
            height: 100%;
          }
          
          .title-text > img{
          	opacity: 0.7;
          }
  </style>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css" type="text/css">
  
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

</head>
<body>
  <%@ include file="WEB-INF/views/common/header.jsp" %>
  <section id="content">
    <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
        <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <div class="carousel-caption d-none d-md-block">

          </div>
        </div>
        <div class="carousel-item">
          <div class="carousel-caption d-none d-md-block">

          </div>
        </div>
        <div class="carousel-item">
          <div class="carousel-caption d-none d-md-block">

          </div>
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </section>
  
      <div class="empty"></div>
        <div class="category">-정보</div>
        <div class="container">
        
            <div class="img-box">
            	<p class="info-title0 title-text"></p>
            </div>

            <div class="img-box">
            <p class="info-title1 title-text"></p>
            </div>
  
            <div class="img-box">
            <p class="info-title2 title-text"></p>
            </div>
        </div>


        <div class="category">-자유게시판</div>
        <div class="container">
            <div class="img-box">
                <p class="community-title0 title-text"></p>
                
            </div>
            <div class="img-box">
            <p class="community-title1 title-text"></p>
                
            </div>
            <div class="img-box">
            <p class="community-title2 title-text"></p>
            </div>
        </div>
        
        
        <div class="category">-Q&A</div>
        <div class="container">
            <div class="img-box">
                <p class="qna-title0 title-text"></p>
               
            </div>
            <div class="img-box">
            <p class="qna-title1 title-text"></p>
                
            </div>
            <div class="img-box">
            <p class="qna-title2 title-text"></p>
            </div>
        </div>
        
        <div class="category">-사용후기</div>
        <div class="container">
            <div class="img-box">
                <p class="review-title0 title-text"></p>
            </div>
            
            <div class="img-box">
            <p class="review-title1 title-text"></p>
            </div>
            
            <div class="img-box">
            <p class="review-title2 title-text"></p>
            </div>
        </div>
     
     
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"
    integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
  </script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
  </script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
    integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous">
  </script>
  
  <%@ include file="WEB-INF/views/common/footer.jsp" %>
</body>
<script>
// 정보 게시판용
$(function(){
	$.ajax({
		  url : "<%=request.getContextPath()%>/main/selectInfo.do",
	      type : "POST",
	      dataType : "JSON",
	      success : function(map){
	    	  for(var i=0; i<map.iList.length ; i++){
	    		  
	    		 // 하이퍼링크
	    		 var hrefNo = map.iList[i].infoBoardNo;
	    	 	 $a = $("<a>");
	    	 	 $a.attr("href", "<%=request.getContextPath()%>/information/view.do?type=2&no=" + hrefNo);
	    	 	 $a.css({"width" : "100%", "height" : "100%" , "position" : "absolute", "margin" : "auto", "top" : "0", "bottom":"0", "right":"0", "left":"0", "z-index":"2"});
	    	 	 $(".info-title"+i).append($a);
	    	 	
	    	 	 // 텍스트용
	    		 $p = $("<p>");
	    		 $p.css({"right" : "0", "left" : "0" , "position" : "absolute", "line-height" : "150px",  "z-index":"1"});
	    		 $p.text(map.iList[i].infoBoardTitle);
	    	 	 $(".info-title"+i).append($p);
	    	 	 
	    	 	 // 이미지
	    	 	 if(map.iList[i].infoBoardNo == map.fList[i].parentBoardNo){
		    	 	 src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[i].fileChangeName;
		             $(".info-title"+i).append("<img src="+src+">");
		             
	    	 	 }  
	    	  }
	      },error : function(){
	         console.log("ajax 통신 실패");
	      }
	});
});

// 자유게시판
$(function(){
	$.ajax({
		  url : "<%=request.getContextPath()%>/main/selectCommunity.do",
	      type : "POST",
	      dataType : "JSON",
	      success : function(map){
	    	  for(var i=0; i<map.cList.length ; i++){
		    		 // 하이퍼링크
		    		 var hrefNo = map.cList[i].boardNo;
		    	 	 $a = $("<a>");
		    	 	 $a.attr("href", "<%=request.getContextPath()%>/community/view.do?type=3&no=" + hrefNo);
		    	 	 $a.css({"width" : "100%", "height" : "100%" , "position" : "absolute", "margin" : "auto", "top" : "0", "bottom":"0", "right":"0", "left":"0", "z-index":"2"});
		    	 	 $(".community-title"+i).append($a);
		    	 	
		    	 	 // 텍스트용
		    		 $p = $("<p>");
		    		 $p.css({"right" : "0", "left" : "0" , "position" : "absolute", "line-height" : "150px",  "z-index":"1"});
		    		 $p.text(map.cList[i].title);
		    	 	 $(".community-title"+i).append($p);
		    	 	 
		    	 	 // 이미지
		    	 	 if(map.cList[i].boardNo == map.fList[i].parentBoardNo){
			    	 	 src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[i].fileChangeName;
			             $(".community-title"+i).append("<img src="+src+">");
			             
		    	 	 }  
	    	  }
	      },error : function(){
	         console.log("ajax 통신 실패");
	      }
	});
});

//Q&A
$(function(){
	$.ajax({
		  url : "<%=request.getContextPath()%>/main/selectQNA.do",
	      type : "POST",
	      dataType : "JSON",
	      success : function(map){
	    	  for(var i=0; i<map.qList.length ; i++){
		    		 // 하이퍼링크
		    		 var hrefNo = map.qList[i].boardNo;
		    	 	 $a = $("<a>");
		    	 	 $a.attr("href", "<%=request.getContextPath()%>/qna/list.do?type=5&no=" + hrefNo);
		    	 	 $a.css({"width" : "100%", "height" : "100%" , "position" : "absolute", "margin" : "auto", "top" : "0", "bottom":"0", "right":"0", "left":"0", "z-index":"2"});
		    	 	 $(".qna-title"+i).append($a);
		    	 	
		    	 	 // 텍스트용
		    		 $p = $("<p>");
		    		 $p.css({"right" : "0", "left" : "0" , "position" : "absolute", "line-height" : "150px",  "z-index":"1"});
		    		 $p.text(map.qList[i].title);
		    	 	 $(".qna-title"+i).append($p);
		    	 	 
		    	 	 // 이미지
		    	 	 if(map.qList[i].boardNo == map.fList[i].parentBoardNo){
		    	 		 src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[i].fileChangeName;
			             $(".qna-title"+i).append("<img src="+src+">");
	    		  
		    	 	 }
	    	  }
	      },error : function(){
	         console.log("ajax 통신 실패");
	      }
	});
});

//사용후기
$(function(){
	$.ajax({
		  url : "<%=request.getContextPath()%>/main/selectReview.do",
	      type : "POST",
	      dataType : "JSON",
	      success : function(map){
	    	  for(var i=0; i<map.rList.length ; i++){
		    		 // 하이퍼링크
		    		 console.log(map.rList[0]);
		    		 console.log(map.fList[0]);
		    		 var hrefNo = map.rList[i].reviewBoardNo;
		    	 	 $a = $("<a>");
		    	 	 $a.attr("href", "<%=request.getContextPath()%>/review/detailReview.do?type=6&no=" + hrefNo);
		    	 	 $a.css({"width" : "100%", "height" : "100%" , "position" : "absolute", "margin" : "auto", "top" : "0", "bottom":"0", "right":"0", "left":"0", "z-index":"2"});
		    	 	 $(".review-title"+i).append($a);
		    	 	
		    	 	 // 텍스트용
		    		 $p = $("<p>");
		    		 $p.css({"right" : "0", "left" : "0" , "position" : "absolute", "line-height" : "150px",  "z-index":"1"});
		    		 $p.text(map.rList[i].reviewTitle);
		    	 	 $(".review-title"+i).append($p);
		    	 	 
		    	 	 // 이미지
		    	 	 if(map.rList[i].reviewBoardNo == map.fList[i].parentBoardNo){
		    	 		src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[i].fileChangeName;
			             $(".review-title"+i).append("<img src="+src+">");
	    		  
		    	 	 }
	    	  }
	      },error : function(){
	         console.log("ajax 통신 실패");
	      }
	});
});

</script>
</html>