<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
    <style>
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
        
	</style>
	
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp"%>

		<div class="container">
	        <div class="my-5">
	            <table class="table table-hover table-striped" id="list-table">
	                <thead>
	                    <tr>
	                        <th>글번호 </th>
	                        <th>카테고리 </th>
	                        <th>제목</th>
	                        <th>작성자</th>
	                        <th>조회수</th>
	                        <th>작성일</th>
	                    </tr>
	                </thead>
	                <tbody>
	                </tbody>
	            </table>
	        </div>
	
	        <hr>
	        
	        <%-- 로그인이 되어있는 경우 --%>
<%-- 	        <% if(loginMember != null) {%>
	        <button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = 'insertForm';">글쓰기</button>
	        <% } %> --%>
	        
	        <!-- 페이징바 -->
	        
	        
	        
	        <!-- 검색 -->
	        <div>
	            <form action="search" method="GET" class="text-center" id="searchForm">
	                <select name="searchKey" class="form-control" style="width:100px; display: inline-block;">
	                    <!-- <option value="title" selected>글제목</option> -->
	                    <option value="title">글제목</option>
	                    <option value="content">내용</option>
	                    <option value="titcont">제목+내용</option>
	                </select>
	                <input type="text" name="searchValue" class="form-control" style="width:25%; display: inline-block;">
	                <button class="form-control btn btn-primary" style="width:100px; display: inline-block;">검색</button>
	            </form>
	            
	        </div>
    	</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	
	<script>
		//------------------------------------------------------------------------------------------------------------
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		
		//------------------------------------------------------------------------------------------------------------
		// 검색
		
	</script>
	
	
	
</body>
</html>