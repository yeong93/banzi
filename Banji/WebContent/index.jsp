


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BAN JI</title>
  <meta charset="UTF-8">
  <title>BAN JI</title>
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
        .empty { width: 100%; height: 100px;}
          
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
          }  
        .img-box:hover{
            cursor: pointer;
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
            height: 26px;
            margin-left: 390px;
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
        	<img src="<%=request.getContextPath()%>/resources/img/banner01.png">
          <div class="carousel-caption d-none d-md-block">

          </div>
        </div>
        <div class="carousel-item">
        	<img src="<%=request.getContextPath()%>/resources/img/banner02.jpg">
          <div class="carousel-caption d-none d-md-block">

          </div>
        </div>
        <div class="carousel-item">
        	<img src="<%=request.getContextPath()%>/resources/img/banner03.jpg">
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
        <div class="category">-음식 정보</div>
        <div class="container">
        
            <div class="img-box">
            	<p class="info-title title-text"></p>
            </div>
        </div>
        
        <div class="category">-견종백과 정보</div>
        <div class="container">
        
            <div class="img-box">
            	<p class="info-title2 title-text"></p>
            </div>
        </div>
        
        <div class="category">-건강상식 정보</div>
        <div class="container">
        
            <div class="img-box">
            	<p class="info-title3 title-text"></p>
            </div>
        </div>
        
        <div class="category">-교육/훈련 정보</div>
        <div class="container">
        
            <div class="img-box">
            	<p class="info-title4 title-text"></p>
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
// 정보 게시판용-카테고리1
$(function(){
	$.ajax({
		  url : "<%=request.getContextPath()%>/main/selectInfo.do",
	      type : "POST",
	      dataType : "JSON",
	      success : function(map){
	    		
	    		  
	    		 // 하이퍼링크
	    		 var hrefNo = map.iList[0].infoBoardNo;
	    	 	 $a = $("<a>");
	    	 	 $a.attr("href", "<%=request.getContextPath()%>/information/view.do?type=2&category=1&no=" + hrefNo);
	    	 	 $a.css({"width" : "100%", "height" : "100%" , "position" : "absolute", "margin" : "auto", "top" : "0", "bottom":"0", "right":"0", "left":"0", "z-index":"2"});
	    	 	// $(".info-title").append($a);
	    	 	
	    	 	 // 텍스트용
	    		/* $p = $("<p>");
	    		 $p.css({"right" : "0", "left" : "0" , "position" : "absolute", "line-height" : "150px",  "z-index":"1"});
	    		 $p.text(map.iList[0].infoBoardTitle);
	    	 	 $(".info-title").append($p);
	    	 	 */
	    	 	 // 이미지
	    	 	 if(map.iList[0].infoBoardNo == map.fList[0].parentBoardNo){
		    	 src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[0].fileChangeName;
		    	 $img = $("<img>");
		    	 $img.attr("src" , src).css({"width":"100%", "height":"100%"});
		    	 
		    	 $a.append($img);
		         $(".info-title").append($a);
	    	 	
	    	  }
	    	 	 
	      },error : function(){
	         console.log("ajax 통신 실패");
	      }
	});
});

//정보 게시판용-카테고리2
$(function(){
	$.ajax({
		  url : "<%=request.getContextPath()%>/main/selectInfo2.do",
	      type : "POST",
	      dataType : "JSON",
	      success : function(map){
	    		  console.log(map.iList);
	    		  console.log(map.fList);
	    		  
	    		 // 하이퍼링크
	    		 var hrefNo = map.iList[0].infoBoardNo;
	    	 	 $a = $("<a>");
	    	 	 $a.attr("href", "<%=request.getContextPath()%>/information/view.do?type=2&category=1&no=" + hrefNo);
	    	 	 $a.css({"width" : "100%", "height" : "100%" , "position" : "absolute", "margin" : "auto", "top" : "0", "bottom":"0", "right":"0", "left":"0", "z-index":"2"});
	    	 	// $(".info-title").append($a);
	    	 	
	    	 	 // 텍스트용
	    		/* $p = $("<p>");
	    		 $p.css({"right" : "0", "left" : "0" , "position" : "absolute", "line-height" : "150px",  "z-index":"1"});
	    		 $p.text(map.iList[0].infoBoardTitle);
	    	 	 $(".info-title").append($p);
	    	 	 */
	    	 	 // 이미지
	    	 	 if(map.iList[0].infoBoardNo == map.fList[0].parentBoardNo){
		    	 src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[0].fileChangeName;
		    	 $img = $("<img>");
		    	 $img.attr("src" , src).css({"width":"100%", "height":"100%"});
		    	 
		    	 $a.append($img);
		         $(".info-title2").append($a);
	    	 	
	    	  }
	    	 	 
	      },error : function(){
	         console.log("ajax 통신 실패");
	      }
	});
});

//정보 게시판용-카테고리3
$(function(){
	$.ajax({
		  url : "<%=request.getContextPath()%>/main/selectInfo3.do",
	      type : "POST",
	      dataType : "JSON",
	      success : function(map){
	    		  console.log(map.iList);
	    		  console.log(map.fList);
	    		  
	    		 // 하이퍼링크
	    		 var hrefNo = map.iList[0].infoBoardNo;
	    	 	 $a = $("<a>");
	    	 	 $a.attr("href", "<%=request.getContextPath()%>/information/view.do?type=2&category=1&no=" + hrefNo);
	    	 	 $a.css({"width" : "100%", "height" : "100%" , "position" : "absolute", "margin" : "auto", "top" : "0", "bottom":"0", "right":"0", "left":"0", "z-index":"2"});
	    	 	// $(".info-title").append($a);
	    	 	
	    	 	 // 텍스트용
	    		/* $p = $("<p>");
	    		 $p.css({"right" : "0", "left" : "0" , "position" : "absolute", "line-height" : "150px",  "z-index":"1"});
	    		 $p.text(map.iList[0].infoBoardTitle);
	    	 	 $(".info-title").append($p);
	    	 	 */
	    	 	 // 이미지
	    	 	 if(map.iList[0].infoBoardNo == map.fList[0].parentBoardNo){
		    	 src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[0].fileChangeName;
		    	 $img = $("<img>");
		    	 $img.attr("src" , src).css({"width":"100%", "height":"100%"});
		    	 
		    	 $a.append($img);
		         $(".info-title3").append($a);
	    	 	
	    	  }
	    	 	 
	      },error : function(){
	         console.log("ajax 통신 실패");
	      }
	});
});

//정보 게시판용-카테고리4
$(function(){
	$.ajax({
		  url : "<%=request.getContextPath()%>/main/selectInfo4.do",
	      type : "POST",
	      dataType : "JSON",
	      success : function(map){
	    		  
	    		 // 하이퍼링크
	    		 var hrefNo = map.iList[0].infoBoardNo;
	    	 	 $a = $("<a>");
	    	 	 $a.attr("href", "<%=request.getContextPath()%>/information/view.do?type=2&category=1&no=" + hrefNo);
	    	 	 $a.css({"width" : "100%", "height" : "100%" , "position" : "absolute", "margin" : "auto", "top" : "0", "bottom":"0", "right":"0", "left":"0", "z-index":"2"});
	    	 	// $(".info-title").append($a);
	    	 	
	    	 	 // 텍스트용
	    		/* $p = $("<p>");
	    		 $p.css({"right" : "0", "left" : "0" , "position" : "absolute", "line-height" : "150px",  "z-index":"1"});
	    		 $p.text(map.iList[0].infoBoardTitle);
	    	 	 $(".info-title").append($p);
	    	 	 */
	    	 	 // 이미지
	    	 	 if(map.iList[0].infoBoardNo == map.fList[0].parentBoardNo){
		    	 src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[0].fileChangeName;
		    	 $img = $("<img>");
		    	 $img.attr("src" , src).css({"width":"100%", "height":"100%"});
		    	 
		    	 $a.append($img);
		         $(".info-title4").append($a);
	    	 	
	    	  }
	    	 	 
	      },error : function(){
	         console.log("ajax 통신 실패");
	      }
	});
});



</script>
</html>