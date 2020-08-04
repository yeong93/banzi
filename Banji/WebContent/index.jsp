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
            border: 1px solid black;
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
            height: 30px;
            margin-left: 550px;
            font-size: 20px;
            margin-top: 20px;

          }
          
          .title-text{
          	font-size: 30px;
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
            <p class="qna-title2 title-text"></p>]
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
	    		 $p = $("<p style='right: 0; left: 0; position: absolute; line-height: 150px;'>").text(map.iList[i].infoBoardTitle);
	    		  
	    	 	 $(".info-title"+i).append($p);
	    	 	 
	    	 	 console.log(map.iList[i]);
	    	 	 console.log(map.fList[i]);
	    	 	 
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
	    		  $p = $("<p style='right: 0; left: 0; position: absolute; line-height: 150px;'>").text(map.cList[i].title); 
	    		  $(".community-title"+i).append($p);
	    	 	// $(".community-title"+i).text(map.cList[i].title);
	    	 	
	    		  console.log("확인" + map.cList);
	    	 	 
	    	 	 src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[i].fileChangeName;
	             $(".community-title"+i).append("<img src="+src+">");
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
	    		  $p = $("<p style='right: 0; left: 0; position: absolute; line-height: 150px;'>").text(map.qList[i].title); 
	    		  $(".qna-title"+i).append($p);
	    	 	  // $(".qna-title"+i).text(map.qList[i].title);
	    	 	 
	    	 	 src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[i].fileChangeName;
	             $(".qna-title"+i).append("<img src="+src+">");
	    	 	 
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
	    		 $p = $("<p style='right: 0; left: 0; position: absolute; line-height: 150px;'>").text(map.rList[i].title); 
	    		 $(".review-title"+i).append($p);
	    	 	 //$(".review-title"+i).text(map.rList[i].reviewTitle);
	    	 	 
	    	 	 src ="<%=request.getContextPath()%>/resources/uploadImages/"+map.fList[i].fileChangeName;
	             $(".review-title"+i).append("<img src="+src+">");
	    	 	 
	    	  }
	      },error : function(){
	         console.log("ajax 통신 실패");
	      }
	});
});

</script>
</html>