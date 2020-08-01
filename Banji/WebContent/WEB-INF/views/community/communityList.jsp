<%@page import="com.kh.banzi.common.Attachment"%>
<%@page import="com.kh.banzi.community.model.vo.Community"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.banzi.community.model.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");
  List<Community> cList = (List<Community>)request.getAttribute("cList");
  List<Attachment> fList = (List<Attachment>)request.getAttribute("fList"); 

  
 int currentPage = pInfo.getCurrentPage();
 int listCount = pInfo.getListCount();
 int maxPage = pInfo.getMaxPage();
 int startPage = pInfo.getStartPage();
 int endPage = pInfo.getEndPage();
 int boardType = pInfo.getBoardType();
 
 int prev = (currentPage-1)/10*10;   // < 버튼 
 
 int next = (currentPage+9)/10*10+1; // > 버튼 
%>
	
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>게시판</title>
    <style>
       *{
      font-family: "InfinitySans-RegularA1";
      }
       h1{
        font-family: 'Roboto', sans-serif;
        font-weight:bold;
        text-align:center;
       }
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
        table *{
          text-align : center;
        }
        .container{
          padding-top:200px;
        }
        table th{
          background-color:#FFBA00;
          font-weight:bold;
        }
        table td{
          font-weight:400;
        }
        th:first-of-type{
        width:30%;
        }
	</style>
	
</head>
<body>
	<div class="boardTitle ">
		<%@ include file="../common/header.jsp"%>

		<div class="container">
		<h1>자유게시판</h1>
	        <div class="my">
	            <table class="table table-hover table-striped" id="list-table">
	                <thead>
	                    <tr>
	                        <th>제목</th>
	                        <th>작성자</th>
	                        <th>조회수</th>
	                        <th>작성일</th>
	                    </tr>
	                </thead>
	                <tbody>
	                
	                 <% if(cList.isEmpty()) {%>
	                   <tr><td colspan="6">존재하는 게시글이 없습니다.</td></tr>
                
                   <%}  else{%> 
                               <% for(Community c : cList) {%>
                                  <tr id="<%=c.getBoardNo()%>">
                                     <td class="boardTitle">
                                                                           <%
                                        String src = null;
                                        for(Attachment at : fList){
                                            if(at.getParentBoardNo() == c.getBoardNo()){
                                                src = request.getContextPath()+"/resources/uploadImages/"+at.getFileChangeName();
                                                %>
                                                  <img src="<%=src %>">
                                                <%
                                            }
                                        }
                                      %>
                                      <%=c.getTitle() %>
                                     </td>
                                     <td><%=c.getRegName() %></td>
                                     <td><%=c.getViews() %></td>
                                     <td><%=c.getRegDate() %></td>
                                    </tr>
                               <%} %>
                   <%} %>	          
                   
                  </tbody>
	            </table>
	        </div>
	
	        <hr>
	        
	        <%-- 로그인이 되어있는 경우 --%>
	        <% if(loginUser != null) {%>
	        <button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = 'insertForm.do?type=<%=boardType%>';">글쓰기</button>
	        <% } %> 
	        
	        <!-- 페이징바 -->
	          <div style="clear:both">
              <ul class ="pagination">
               <% if(currentPage > 10) { %>
                    <!--  맨 처음 페이지로 이동[<<] -->
                    <li>
                       <a class="page-link" href="<%=request.getContextPath()%>/community/list.do?&cp=1">&lt;&lt;</a>
                    </li>
                 
                    <!--  이전 순번의 페이징 바로 이동[<] -->
                    <li>
                       <a class = "page-link"
                       href = "<%=request.getContextPath()%>/community/list.do?cp=<%=prev%>">&lt;</a>
                    </li>
                    <%}%>
                    <!--  10개의 페이지 목록 -->
                    <% for(int p = startPage; p<=endPage; p++) {%>
                       
                       <%if (p== currentPage) {%>
                       
                    <li><a class="page-link"><%=p%></a></li>
                    
                    <%} else{%>
                    
                    <li>
                       <a class="page-link" href="<%=request.getContextPath()%>/community/list.do?cp=<%=p%>"><%=p %></a>
                    
                    <%} %>
                    
                    <%} %>
                    
                    
                    <% if((next < maxPage)) {%>
                       <!-- 다음 페이지[>] -->
                    
                    <li>
                       <a class="page-link" href="<%=request.getContextPath()%>/community/list.do?cp=<%=next%>">&gt;</a>
                    </li>
                    
                    <!--  마지막 페이지로 이동[>>] -->
                    
                    <li>
                       <a class="page-link" href="<%=request.getContextPath()%>/community/list.do?cp=<%=maxPage%>">&gt;&gt;</a>
                    </li>
                    
                    
                    <%} %>
                    
              
              </ul>
           </div>
	        
	        
	        
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
     $("#list-table td").on("click", function(){
         var boardNo = $(this).parent().attr("id");
         
         location.href = "<%=request.getContextPath()%>/community/view.do?cp=<%=currentPage%>&no="+boardNo;
      }).on("mouseenter",function(){
         $(this).parent().css("cursor","pointer")
      })
      ; 
		
		//------------------------------------------------------------------------------------------------------------
		// 검색
		
	</script>
	
	
	
</body>
</html>
