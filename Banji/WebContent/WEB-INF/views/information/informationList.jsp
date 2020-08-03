<%@page import="com.kh.banzi.common.PageInfo"%>
<%@page import="com.kh.banzi.information.model.vo.Information"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");
	List<Information> bList = (List<Information>)request.getAttribute("bList");
	
	int currentPage = pInfo.getCurrentPage();
	int listCount = pInfo.getListCount();                                                                                
	int maxPage = pInfo.getMaxPage();
	int startPage = pInfo.getStartPage();
	int endPage = pInfo.getEndPage();
	int boardType = pInfo.getBoardType(); // boardType=2
	String category = request.getParameter("category");

	// 이전 페이지
	int prev = (currentPage-1)/10 * 10; // < 버튼
	int next = (currentPage+9)/10 * 10+1; // > 버튼
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 게시판</title>
    <style>
	    *{
	    font-family: "InfinitySans-RegularA1";
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
        #insertBtn{
        background-color:#ffce54;
        border-color:white;
        }
        #image_area{
        width:1000px;
        height:200px;
        }
        #back{
        height:20px;
        }
        .page-link {
        color:#ffce54;
        }
        #selectArea{
        width:150px;
        }
	</style>
	
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
  <section id="content">
  	<div id="back"></div>
  	<div id="image_area" class="mx-auto">
  	<img src="<%=request.getContextPath()%>/resources/img/information_banner.jpg">
  	</div>
       <div class="form-group">
       <div id="selectArea" class="mx-auto">
        <label for="inputQuestion" class="col-lg-2 control-label"></label>
            <select class="form-control" id="category" name="category" >
                <option value="1">음식</option>
                <option value="2">견종백과</option>
                <option value="3">건강상식</option>
                <option value="4">교육/훈련</option>
            </select>
		<script>
			$(function () {
				$("#category").change(function() {
					var click = $("#category option:selected").val();
					location.href="<%=request.getContextPath()%>/information/list.do?type=2&category="+click;
				});
				
				$("#category > option").each(function(index, item){
					if($(item).val() == <%=request.getParameter("category")%>){
						$(item).prop("selected", true);
					}
				})
			});
		</script>
        </div>
    </div>
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
	                <!-- 조회되는 값이 없어도 쿼리문은 성공함, 에러 없이 비어있는 arrayList가 반환됨 -->
	                	<% if(bList.isEmpty()) { %>
	                		<tr><td colspan="6">존재하는 게시글이 없습니다.</td></tr>
	                	<% } else {%>
	                		<% for(Information information : bList)  {%>
	                		<tr>
	                			<td><%=information.getInfoBoardNo() %></td>
	                			<td><%=information.getCategoryName() %></td>
	                			<td class="boardTitle">
	                				<%=information.getInfoBoardTitle() %>
	                			</td>
	                			<td><%=information.getUserId() %></td>
	                			<td><%=information.getReadCount() %></td>
	                			<td><%=information.getInfoBoardModifyDate() %></td>
	                		</tr>
	                	<% } %>
	                	<% } %>
	                </tbody>
	            </table>
	        </div>
	        <hr>
	        <%-- 로그인이 되어있으면서 유저 등급이 에디터인 경우에만 글쓰기 버튼이 보임 --%>
	        <%  if(loginUser != null && loginUser.getUserGrade().trim().equals("editor")) {%>
	        <button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href ='insertForm.do?type=<%=boardType%>';">글쓰기</button>
	        <% } %>
	        
	        <!-- 페이징바 -->
	        <div style="clear : both" id="page">
	        	<ul class="pagination">
	        		<% if(currentPage > 10) { %>
	        			<!-- 맨 처음 페이지로 이동[<<] -->
	        			<li>	        														<!-- & : 파라미터 이어서 추가 -->
	        				<a class="page-link" href="<%=request.getContextPath()%>/information/list.do?type=<%=boardType%>&cp=1&category=<%=category %>">&lt;&lt;</a>
	        			</li>
	        			<!-- 이전 순번의 페이징바로 이동[<] -->
	        			<li>
	        			<a class="page-link" href="<%=request.getContextPath()%>/information/list.do?type=<%=boardType%>&cp=<%=prev%>&category=<%=category %>">&lt;</a>
	        			<% } %>
	        			<!--  10개의 페이지 목록 -->
	        			<% for(int p = startPage; p<=endPage; p++)  {%>
	        			<!--  내가 보고 있는 페이지가 p와 같은 숫자일 때 -->
	        			<%if(p == currentPage)  { %>
	        			<!--  a태그의 href 속성을 설정하지 않음, 클릭 안됨 -->
	        				<li><a class="page-link"><%=p%></a></li>
	        				<% }else { %>
	        					<li>
	        					<a class="page-link" href="<%=request.getContextPath()%>/information/list.do?type=<%=boardType%>&cp=<%=p%>&category=<%=category %>"><%=p %></a>
	        					</li>
	        				<% } %>
	        			<% } %>
	        			<% if((next < maxPage))  { %>
	        			<!-- 다음 페이징바[>] -->
	        			<li>
	        				<a class="page-link" href="<%=request.getContextPath()%>/information/list.do?type=<%=boardType%>&cp=<%=next %>&category=<%=category %>">&gt;</a>
	        			</li>

	        			<!--  마지막 페이지로 이동 [>>] -->
	        			<li>
	        				<a class="page-link" href="<%=request.getContextPath()%>/information/list.do?type=<%=boardType%>&cp=<%=maxPage %>&category=<%=category %>">&gt;&gt;</a>
	        			</li>		
	        		<% } %>
	        	</ul>
	        </div>
	</div>
	
	<script>
		//------------------------------------------------------------------------------------------------------------
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		$("#list-table td").on("click", function() {													
			var infoBoardNo = $(this).parent().children().eq(0).text();
			// location.href : 요청을 보내서 이동하는 jquery 내부 객체
			location.href =
			"<%=request.getContextPath()%>/information/view.do?type=<%=boardType%>&cp=<%=currentPage%>&no=" + infoBoardNo;
		// .으로 메소드 체이닝
		}).on("mouseenter", function() {
			// cursor를 pointer로 변경시킴
			$(this).parent().css("cursor" , "pointer");
		});
	
		
		</script>
	  </section>
	  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	    integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
	  </script>
	  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
	  </script>
	  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	    integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous">
	  </script>
	  <%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
