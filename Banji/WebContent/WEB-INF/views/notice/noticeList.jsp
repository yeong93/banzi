<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
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
	</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<div class="container">
    <div class="my-5">
    	<h1>공지사항</h1>
      	<table class="table table-hover table-striped my-5" id="list-table">
          <thead>
            <tr>
                <th>글번호 </th>
                <th>제목</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>작성일</th>
            </tr>
           </thead>
            
					<tbody>
<%-- 						<% if(list.isEmpty()){ %>
							<tr>
								<td colspan="5">존재하는 공지사항이 없습니다.</td>
							</tr>
						<% }else{ %>
						
						<% } %> --%>
            </tbody>
          </table>
       </div>
	
	        
       <div class="my-5" style="clear: both;">
           <ul class="pagination">
               <li>
                   <a class="page-link" href="#">&lt;</a>
               </li>
               <li>
                   <a class="page-link" href="#">1</a>
               </li>
               <li>
                   <a class="page-link" href="#">2</a>
               </li>
               <li>
                   <a class="page-link" href="#">3</a>
               </li>
               <li>
                   <a class="page-link" href="#">4</a>
               </li>
               <li>
                   <a class="page-link" href="#">5</a>
               </li>
               <li>
                   <a class="page-link" href="#">&gt;</a>
               </li>
           </ul>
       </div>
       
       <div class="mb-5">
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
	
	<script>
		// 공지사항 상세보기 기능 (jquery를 통해 작업)
		$(function(){
			$("#list-table td").click(function(){
			
			}).mouseenter(function(){
				$(this).parent().css("cursor", "pointer");
			
			});
			
		});
		
		
	</script>
	
	
	
</body>
</html>
