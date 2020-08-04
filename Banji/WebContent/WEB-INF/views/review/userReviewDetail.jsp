<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.kh.banzi.review.model.vo.Attachment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.banzi.review.model.vo.Review"%>
<% 
	Review review = (Review)request.getAttribute("review");
	ArrayList<Attachment> fList = (ArrayList<Attachment>)request.getAttribute("fList");
	String cp = request.getParameter("cp");
	String type = request.getParameter("type");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용 후기 게시글</title>
<style>
	#board-area{ margin-bottom:100px;}
	#board-content{ padding-bottom:150px;}
	.boardImgArea{ height: 300px;}
	*{ font-family: "InfinitySans-RegularA1"; }
	
	.boardImg{
		width : 100%;
		height: 100%;
		max-width : 300px;
		max-height: 300px;
		margin : auto;
	}
	
	.carousel-control-prev-icon {
 		background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E") !important;
	}
	
	.carousel-control-next-icon {
  		background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E") !important;
	}
	
	.replyWrite > table{
		width: 90%;
		align: center;
	}
	
	#replyContentArea{ width: 90%; }
	
	#replyContentArea > textarea{
	    resize: none;
    	width: 100%;
	}
	
	#replyBtnArea{
	    width: 100px;
	    text-align: center;
	}
	
	.rWriter{ margin-right: 30px;}
	.rDate{
		font-size: 0.7em;
		color : gray;
	}
	
	#replyListArea{ list-style-type: none; }

	#listBtn, #deleteBtn,#updateBtn {
    background-color:#ffce54;
    border-color:white;
    }
    #imgArea{
    	width:700px;
    	height:300px;
    }
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
  	<section id="content">
	<div class="container">
		<div>
			<div id="board-area">
				
				<!-- Category -->
				<% String category= null;
				switch(review.getReviewCategory()){
				case 1: category="병원"; break;
				case 2: category="사료"; break;
				case 3: category="간식"; break;
				case 4: category="용품"; break;
				}%>
				<h6 class="mt-4">카테고리 : [<%=category %>]</h6>
				
				<!-- Title -->
				<h3 class="mt-4"><%= review.getReviewTitle() %></h3>

				<!-- Writer -->
				<p class="lead">
					작성자 : <%= review.getUserName()%>
				</p>

				<hr>

				<!-- Date -->
				<p>
					<%=review.getReviewCreateDate() %>
			 		<span class="float-right">조회수 <%= review.getReadCount() %></span>
				</p>

								<hr>
               <% if(fList != null){ %>
                <div class="carousel slide m-3" id="carousel-325626">
                    
                    <div class="carousel-inner boardImgArea">
                     <% 
                        String src = null;
                        boolean flag = true;
                        for(int i=0; i<3 ; i++) {
                         for(Attachment at : fList){
                          if(at.getFileLevel() == i){
                           src = request.getContextPath()+"/resources/uploadImages/"+at.getFileChangeName();
                           String imgClass = "carousel-item";
                           
                           if(flag){
                            imgClass += " active";
                            flag=false;
                           }
                     %>     
                          <div class="<%=imgClass%>">
                            <img class="d-block w-100 boardImg" src="<%= src %>"/>
                            <input type="hidden" value=<%=at.getFileNo() %>>
                           </div> 
                        
                      <%  } } } %>
                      
                    </div> 
                    <a class="carousel-control-prev" href="#carousel-325626" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span></a> <a class="carousel-control-next" href="#carousel-325626" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a>
                </div>
                <% } %>	


				<!-- Content -->
				<div id="board-content"><%= review.getReviewContent() %></div>
				<hr>
				<div>
					<% if(loginUser != null && (review.getUserId().equals(loginUser.getUserId()))) {%>
					<button id="deleteBtn" class="btn btn-primary float-right" id="deleteBtn">삭제</button>
					<!--  삭제 버튼 클릭시 해당 게시글 상태를 'N'으로 바꾸고 목록으로 돌아가기 -->
					<a href="updateReview.do?type=<%=type%>&cp=<%=cp%>&no=<%=review.getReviewBoardNo()%>" class="btn btn-primary float-right ml-1 mr-1" id="updateBtn">수정</a>
					<% } %>
					
					<a href="review.do?type=<%=type%>" class="btn btn-primary float-right" id="listBtn">목록으로</a>
				</div>
					<%@ include file="reviewRelpy.jsp" %>
			</div>
	</section>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
	
	<script>
		$("#deleteBtn").on("click",function() {
			if(confirm("정말 삭제하시겠습니까?")){
				location.href="delete.do?type=<%=type%>&no=<%=review.getReviewBoardNo()%>";
			}
			
		});
	

	</script>
</body>
</html>
