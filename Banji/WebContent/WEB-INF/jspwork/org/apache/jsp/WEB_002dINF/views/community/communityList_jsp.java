/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.56
 * Generated at: 2020-07-29 05:18:23 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.community;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.kh.banzi.community.model.vo.Community;
import java.util.List;
import com.kh.banzi.community.model.vo.PageInfo;
import com.kh.banzi.user.model.vo.User;

public final class communityList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/community/../common/header.jsp", Long.valueOf(1595999872397L));
    _jspx_dependants.put("/WEB-INF/views/community/../common/footer.jsp", Long.valueOf(1595989111466L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.kh.banzi.community.model.vo.PageInfo");
    _jspx_imports_classes.add("com.kh.banzi.community.model.vo.Community");
    _jspx_imports_classes.add("com.kh.banzi.user.model.vo.User");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

  PageInfo pInfo = (PageInfo)request.getAttribute("pInfo");
  List<Community> cList = (List<Community>)request.getAttribute("cList");
  
 int currentPage = pInfo.getCurrentPage();
 int listCount = pInfo.getListCount();
 int maxPage = pInfo.getMaxPage();
 int startPage = pInfo.getStartPage();
 int endPage = pInfo.getEndPage();
 
 int prev = (currentPage-1)/10*10;   // < 버튼 
 
 int next = (currentPage+9)/10*10+1; // > 버튼 

      out.write("\n");
      out.write("\t\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>게시판</title>\n");
      out.write("    <style>\n");
      out.write("    \t.pagination {\n");
      out.write("            justify-content: center;\n");
      out.write("        }\n");
      out.write("        #searchForm{\n");
      out.write("            position: relative;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #searchForm>*{\n");
      out.write("            top : 0;\n");
      out.write("        }\n");
      out.write("        .boardTitle > img{\n");
      out.write("        \twidth: 50px;\n");
      out.write("        \theight: 50px;\n");
      out.write("        }\n");
      out.write("        table *{\n");
      out.write("          text-align : center;\n");
      out.write("        }\n");
      out.write("\t</style>\n");
      out.write("\t\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<div class=\"boardTitle \">\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\r\n");
	
	User loginUser = (User)session.getAttribute("loginUser");

	boolean isRemember = false;
	String rememberId = "";
	Cookie[] cookies = request.getCookies();
	
	if(cookies != null){
		for(Cookie c : cookies){
			if("remeberId".equals(c.getName())){
				rememberId = c.getValue();
				isRemember = true;
			}
		}
	}

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("  <meta charset=\"UTF-8\" />\r\n");
      out.write("\r\n");
      out.write("  <!-- Required meta tags -->\r\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\r\n");
      out.write("  <!-- Bootstrap CSS -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\"\r\n");
      out.write("    integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\" />\r\n");
      out.write("  <!-- jQuery -->\r\n");
      out.write("  <script src=\"https://code.jquery.com/jquery-3.5.1.min.js\"></script>\r\n");
      out.write("  <!-- CSS -->\r\n");
      out.write("   <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/resources/css/style.css\">\r\n");
      out.write("  <title>BAN JI</title>\r\n");
      out.write("  <style>\r\n");
      out.write("  \r\n");
      out.write("/* -------------- body -------------- */\r\n");
      out.write("\r\n");
      out.write("* {\r\n");
      out.write("/* border: 1px solid black; */\r\n");
      out.write("margin: 0;\r\n");
      out.write("padding: 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".row>div {\r\n");
      out.write("width: 100%;\r\n");
      out.write("height: 100%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* -------------- header -------------- */\r\n");
      out.write("header{\r\n");
      out.write("position: fixed;\r\n");
      out.write("height : 157px;\r\n");
      out.write("width: 100%;\r\n");
      out.write("z-index : 100;\r\n");
      out.write("background-color : white;\r\n");
      out.write("}\r\n");
      out.write("#main-logo{\r\n");
      out.write("padding: 11px;\r\n");
      out.write("}\r\n");
      out.write("#header button {\r\n");
      out.write("float: right;\r\n");
      out.write("margin: 1em;\r\n");
      out.write("width: 6em;\r\n");
      out.write("height: 2em;\r\n");
      out.write("border: 1px solid lightgray;\r\n");
      out.write("font-family: \"InfinitySans-RegularA1\";\r\n");
      out.write("font-size: 0.7em;\r\n");
      out.write("color: lightgray;\r\n");
      out.write("background-color: transparent;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("/* -------------- nav -------------- */\r\n");
      out.write(".dropdown:hover .dropdown-menu { \r\n");
      out.write("display: block;\r\n");
      out.write("margin-top: 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#nav{\r\n");
      out.write("border-top: 1px solid lightgray;\r\n");
      out.write("border-bottom: 1px solid lightgray;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#nav * {\r\n");
      out.write("font-family: \"InfinitySans-RegularA1\";\r\n");
      out.write("text-align: center;\r\n");
      out.write("vertical-align: middle;\r\n");
      out.write("border: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#nav a {\r\n");
      out.write("width: 10rem;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#nav li {\r\n");
      out.write("width: 10%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".nav-link {\r\n");
      out.write("color: #3a3847;\r\n");
      out.write("width: 100%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".nav-link:hover {\r\n");
      out.write("color: #ffce54;\r\n");
      out.write("border: none;\r\n");
      out.write("border-bottom: 3px solid !important;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-menu {\r\n");
      out.write("border-top: 5px solid transparent !important;\r\n");
      out.write("width: 10rem;\r\n");
      out.write("background-color: rgb(250, 250, 250);\r\n");
      out.write("box-shadow: 0 10px 10px lightgrey;\r\n");
      out.write("padding-bottom: 20px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-item {\r\n");
      out.write("font-size: small;\r\n");
      out.write("margin-top: 10px;\r\n");
      out.write("background-color: transparent;\r\n");
      out.write("opacity: 0.7;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-item:hover {\r\n");
      out.write("background-color: #ffce54;\r\n");
      out.write("color: white;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#userNameArea{\r\n");
      out.write("\tborder: none !important;\r\n");
      out.write("\tcolor: #3a3847 !important;\r\n");
      out.write("}\r\n");
      out.write("  </style>\r\n");
      out.write("  \r\n");
      out.write("  <!--------------------------------- sweet alert ---------------------------------------- -->\r\n");
      out.write("  <script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>\r\n");
      out.write("  \r\n");
      out.write("  <script>\r\n");
      out.write("  ");

 	 String status = (String)(request.getSession().getAttribute("status"));
 	 String msg = (String)(request.getSession().getAttribute("msg"));
 	 String text = (String)(request.getSession().getAttribute("text"));
  
      out.write("\r\n");
      out.write("  \r\n");
      out.write("  ");
 if(msg != null){
      out.write("\r\n");
      out.write("\t\t \t swal({\r\n");
      out.write("\t\t \t\t icon : \"");
      out.print(status);
      out.write("\", \r\n");
      out.write("\t\t \t\t title : \"");
      out.print(msg);
      out.write("\",\r\n");
      out.write("\t\t \t\t text : \"");
      out.print(text != null ? text : "" );
      out.write("\" \r\n");
      out.write("\t\t \t });\r\n");
      out.write(" \t ");

 	 		session.removeAttribute("msg");
 	 		session.removeAttribute("status");
 	 		session.removeAttribute("text");
  	}
 	 
      out.write("\r\n");
      out.write("  \r\n");
      out.write("  </script>\r\n");
      out.write("  \r\n");
      out.write("<body>\r\n");
      out.write("  <header>\r\n");
      out.write("    <div class=\"container-fluid\">\r\n");
      out.write("      <!--  ------------ header ------------ -->\r\n");
      out.write("      <div class=\"row\" id=\"header\">\r\n");
      out.write("        <div class=\"col-md-4\">\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-4\">\r\n");
      out.write("          <a href=\"");
      out.print(request.getContextPath());
      out.write("\">\r\n");
      out.write("            <img src=\"");
      out.print(request.getContextPath());
      out.write("/resources/img/logo_main.png\" width=\"200px\" class=\"mx-auto d-block\" id=\"main-logo\">\r\n");
      out.write("          </a>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-4\">\r\n");
      out.write("          <button type=\"button\" onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/user/signUpAssign1.do'\">회원가입</button>\r\n");
      out.write("          \r\n");
      out.write("          ");
 if(loginUser == null) {
      out.write("\r\n");
      out.write("          \t <button type=\"button\" onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/userLogin/loginForm.do'\">로그인</button>\r\n");
      out.write("          ");
 } else{ 
      out.write("\r\n");
      out.write("          \t <button type=\"button\" onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/userLogin/logout.do'\">로그아웃</button>\r\n");
      out.write("          \t <button type=\"button\" id=\"userNameArea\" onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/myPage/changeUserForm.do'\">");
      out.print(loginUser.getUserName() );
      out.write(" 님</button>\r\n");
      out.write("          \t \r\n");
      out.write("          ");
 } 
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <!--  ------------ nav ------------ -->\r\n");
      out.write("    <div class=\"row\" id=\"nav\">\r\n");
      out.write("      <div class=\"col-md-12\">\r\n");
      out.write("        <ul class=\"nav nav-tabs justify-content-center\" id=\"nav-wrapper\">\r\n");
      out.write("          <li class=\"nav-item dropdown\">\r\n");
      out.write("            <a class=\"nav-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/user/introduce.do\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">구성원 소개</a>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"nav-item dropdown\">\r\n");
      out.write("            <a class=\"nav-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/user/introduce.do\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">정보</a>\r\n");
      out.write("            <div class=\"dropdown-menu\">\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"#\">음식</a>\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"#\">견종백과</a>\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"#\">건강상식</a>\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"#\">교육/훈련</a>\r\n");
      out.write("            </div>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"nav-item dropdown\">\r\n");
      out.write("            <a class=\"nav-link\" href=\"community/list.do\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">커뮤니티</a>\r\n");
      out.write("            <div class=\"dropdown-menu\">\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"community/notice.do\">공지사항</a>\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"community/list.do\">자유 게시판</a>\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"question\">Q&A</a>\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"");
      out.print(request.getContextPath());
      out.write("/review/review.do\">사용 후기</a>\r\n");
      out.write("            </div>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"nav-item dropdown\">\r\n");
      out.write("            <a class=\"nav-link\" href=\"#\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">이벤트</a>\r\n");
      out.write("            <div class=\"dropdown-menu\">\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"#\">진행중인 이벤트</a>\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"#\">종료된 이벤트</a>\r\n");
      out.write("            </div>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"nav-item dropdown\">\r\n");
      out.write("            <a class=\"nav-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/myPage/changeUserForm.do\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">마이페이지</a>\r\n");
      out.write("            <div class=\"dropdown-menu\">\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"");
      out.print(request.getContextPath());
      out.write("/myPage/changeUserForm.do\">회원정보 수정</a>\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"");
      out.print(request.getContextPath());
      out.write("/myPage/changePwdForm.do\">비밀번호 수정</a>\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"");
      out.print(request.getContextPath());
      out.write("/myPage/secessionForm.do\">회원 탈퇴</a>\r\n");
      out.write("            </div>\r\n");
      out.write("          </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </header>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  <script>\r\n");
      out.write("    $(function () {\r\n");
      out.write("      $(\".nav-link\").on(\"mouseenter\", function () {\r\n");
      out.write("        var idx = $(this).index(\".nav-link\");\r\n");
      out.write("\r\n");
      out.write("        $(\".nav-link\").each(function (index, item) {\r\n");
      out.write("          if (index == idx) {\r\n");
      out.write("            $(item)\r\n");
      out.write("              .css(\"color\", \"#ffce54\")\r\n");
      out.write("              .css(\"border-bottom\", \"3px solid #ffce54\");\r\n");
      out.write("          } else {\r\n");
      out.write("            $(item).css(\"color\", \"#3a3847\").css(\"border-bottom\", \"none\");\r\n");
      out.write("          }\r\n");
      out.write("        });\r\n");
      out.write("      });\r\n");
      out.write("\r\n");
      out.write("      $(\"#nav\").on(\"mouseleave\", function () {\r\n");
      out.write("        $(\".nav-link\")\r\n");
      out.write("          .css(\"color\", \"#3a3847\")\r\n");
      out.write("          .css(\"border-bottom\", \"3px solid transparent\");\r\n");
      out.write("      });\r\n");
      out.write("    });\r\n");
      out.write("  </script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\"\r\n");
      out.write("    integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\"\r\n");
      out.write("    crossorigin=\"anonymous\"></script>\r\n");
      out.write("  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\"\r\n");
      out.write("    integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\"\r\n");
      out.write("    crossorigin=\"anonymous\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
      out.write("\n");
      out.write("\n");
      out.write("\t\t<div class=\"container\">\n");
      out.write("\t        <div class=\"my-5\">\n");
      out.write("\t            <table class=\"table table-hover table-striped\" id=\"list-table\">\n");
      out.write("\t                <thead>\n");
      out.write("\t                    <tr>\n");
      out.write("\t                        <th>제목</th>\n");
      out.write("\t                        <th>작성자</th>\n");
      out.write("\t                        <th>조회수</th>\n");
      out.write("\t                        <th>작성일</th>\n");
      out.write("\t                    </tr>\n");
      out.write("\t                </thead>\n");
      out.write("\t                <tbody>\n");
      out.write("\t                \n");
      out.write("\t                 ");
 if(cList.isEmpty()) {
      out.write("\n");
      out.write("\t                   <tr><td colspan=\"6\">존재하는 게시글이 없습니다.</td></tr>\n");
      out.write("                \n");
      out.write("                   ");
}  else{
      out.write(" \n");
      out.write("                               ");
 for(Community c : cList) {
      out.write("\n");
      out.write("                                  <tr id=\"");
      out.print(c.getBoardNo());
      out.write("\">\n");
      out.write("                                     <td class=\"boardTitle\">\n");
      out.write("                                      ");
      out.print(c.getTitle() );
      out.write("\n");
      out.write("                                     </td>\n");
      out.write("                                     <td>");
      out.print(c.getRegName() );
      out.write("</td>\n");
      out.write("                                     <td>");
      out.print(c.getViews() );
      out.write("</td>\n");
      out.write("                                     <td>");
      out.print(c.getRegDate() );
      out.write("</td>\n");
      out.write("                                    </tr>\n");
      out.write("                               ");
} 
      out.write("\n");
      out.write("                   ");
} 
      out.write("\t          \n");
      out.write("                   \n");
      out.write("                  </tbody>\n");
      out.write("\t            </table>\n");
      out.write("\t        </div>\n");
      out.write("\t\n");
      out.write("\t        <hr>\n");
      out.write("\t        \n");
      out.write("\t        ");
      out.write('\n');
      out.write("\n");
      out.write("\t        \n");
      out.write("\t        <!-- 페이징바 -->\n");
      out.write("\t          <div style=\"clear:both\">\n");
      out.write("              <ul class =\"pagination\">\n");
      out.write("               ");
 if(currentPage > 10) { 
      out.write("\n");
      out.write("                    <!--  맨 처음 페이지로 이동[<<] -->\n");
      out.write("                    <li>\n");
      out.write("                       <a class=\"page-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/community/list.do?&cp=1\">&lt;&lt;</a>\n");
      out.write("                    </li>\n");
      out.write("                 \n");
      out.write("                    <!--  이전 순번의 페이징 바로 이동[<] -->\n");
      out.write("                    <li>\n");
      out.write("                       <a class = \"page-link\"\n");
      out.write("                       href = \"");
      out.print(request.getContextPath());
      out.write("/community/list.do?cp=");
      out.print(prev);
      out.write("\">&lt;</a>\n");
      out.write("                    </li>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    <!--  10개의 페이지 목록 -->\n");
      out.write("                    ");
 for(int p = startPage; p<=endPage; p++) {
      out.write("\n");
      out.write("                       \n");
      out.write("                       ");
if (p== currentPage) {
      out.write("\n");
      out.write("                       \n");
      out.write("                    <li><a class=\"page-link\">");
      out.print(p);
      out.write("</a></li>\n");
      out.write("                    \n");
      out.write("                    ");
} else{
      out.write("\n");
      out.write("                    \n");
      out.write("                    <li>\n");
      out.write("                       <a class=\"page-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/community/list.do?cp=");
      out.print(p);
      out.write('"');
      out.write('>');
      out.print(p );
      out.write("</a>\n");
      out.write("                    \n");
      out.write("                    ");
} 
      out.write("\n");
      out.write("                    \n");
      out.write("                    ");
} 
      out.write("\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                    ");
 if((next < maxPage)) {
      out.write("\n");
      out.write("                       <!-- 다음 페이지[>] -->\n");
      out.write("                    \n");
      out.write("                    <li>\n");
      out.write("                       <a class=\"page-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/community/list.do?cp=");
      out.print(next);
      out.write("\">&gt;</a>\n");
      out.write("                    </li>\n");
      out.write("                    \n");
      out.write("                    <!--  마지막 페이지로 이동[>>] -->\n");
      out.write("                    \n");
      out.write("                    <li>\n");
      out.write("                       <a class=\"page-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/community/list.do?cp=");
      out.print(maxPage);
      out.write("\">&gt;&gt;</a>\n");
      out.write("                    </li>\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                    ");
} 
      out.write("\n");
      out.write("                    \n");
      out.write("              \n");
      out.write("              </ul>\n");
      out.write("           </div>\n");
      out.write("\t        \n");
      out.write("\t        \n");
      out.write("\t        \n");
      out.write("\t        <!-- 검색 -->\n");
      out.write("\t        <div>\n");
      out.write("\t            <form action=\"search\" method=\"GET\" class=\"text-center\" id=\"searchForm\">\n");
      out.write("\t                <select name=\"searchKey\" class=\"form-control\" style=\"width:100px; display: inline-block;\">\n");
      out.write("\t                    <!-- <option value=\"title\" selected>글제목</option> -->\n");
      out.write("\t                    <option value=\"title\">글제목</option>\n");
      out.write("\t                    <option value=\"content\">내용</option>\n");
      out.write("\t                    <option value=\"titcont\">제목+내용</option>\n");
      out.write("\t                </select>\n");
      out.write("\t                <input type=\"text\" name=\"searchValue\" class=\"form-control\" style=\"width:25%; display: inline-block;\">\n");
      out.write("\t                <button class=\"form-control btn btn-primary\" style=\"width:100px; display: inline-block;\">검색</button>\n");
      out.write("\t            </form>\n");
      out.write("\t            \n");
      out.write("\t        </div>\n");
      out.write("    \t</div>\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"ko\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>마이페이지</title>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <style>\r\n");
      out.write("           .footer {\r\n");
      out.write("            background: #ffffff;\r\n");
      out.write("            font-family: \"yg-jalnan\";\r\n");
      out.write("            color: #3a3847;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .footer .col-xs-12{\r\n");
      out.write("            margin-top: 1em;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .footer .contact i {\r\n");
      out.write("            font-size: 18px;\r\n");
      out.write("            font-family: \"InfinitySans-RegularA1\";\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .footer .copyright p {\r\n");
      out.write("            border-top: 1px solid lightgray;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        .footer img{\r\n");
      out.write("            width: 10em;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .footer p{\r\n");
      out.write("            font-family: \"InfinitySans-RegularA1\";\r\n");
      out.write("            color: #3a3847;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .footer h4{\r\n");
      out.write("            margin-bottom: 0.5em;\r\n");
      out.write("        }\r\n");
      out.write("       \r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("    <div class=\"footer\">\r\n");
      out.write("        <div class=\"container-fluid\">\r\n");
      out.write("            <div class=\"row mt-5\">\r\n");
      out.write("                <div class=\"col copyright\">\r\n");
      out.write("                    <p></p>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"row mt-3\">\r\n");
      out.write("            \t<div class=\"col-md-2\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-md-3\">\r\n");
      out.write("                    <img src=\"");
      out.print(request.getContextPath());
      out.write("/resources/img/logo_footer.png\"\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-md-3\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-md-4 contact\">\r\n");
      out.write("                    <h4 class=\"mt-md-0 mt-sm-4\">contact us</h4>\r\n");
      out.write("                    <p>서울특별시 중구 남대문로 120 대일빌딩 2F</p>\r\n");
      out.write("                    <p>1544-9970</p>\r\n");
      out.write("                    <p>info@email.com</p>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"row mt-3\">\r\n");
      out.write("                <div class=\"col copyright\">\r\n");
      out.write("                    <p class=\"\"><small>© 2020. All Rights Reserved.</small></p>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\"\r\n");
      out.write("        integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\"\r\n");
      out.write("        crossorigin=\"anonymous\"></script>\r\n");
      out.write("    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\"\r\n");
      out.write("        integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\"\r\n");
      out.write("        crossorigin=\"anonymous\"></script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
      out.write("\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("\t<script>\n");
      out.write("\t\t//------------------------------------------------------------------------------------------------------------\n");
      out.write("\t\t// 게시글 상세보기 기능 (jquery를 통해 작업)\n");
      out.write("     $(\"#list-table td\").on(\"click\", function(){\n");
      out.write("         var boardNo = $(this).parent().attr(\"id\");\n");
      out.write("         \n");
      out.write("         location.href = \"");
      out.print(request.getContextPath());
      out.write("/community/view.do?cp=");
      out.print(currentPage);
      out.write("&no=\"+boardNo;\n");
      out.write("      }).on(\"mouseenter\",function(){\n");
      out.write("         $(this).parent().css(\"cursor\",\"pointer\")\n");
      out.write("      })\n");
      out.write("      ; \n");
      out.write("\t\t\n");
      out.write("\t\t//------------------------------------------------------------------------------------------------------------\n");
      out.write("\t\t// 검색\n");
      out.write("\t\t\n");
      out.write("\t</script>\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}