/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.56
 * Generated at: 2020-08-03 07:57:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.kh.banzi.user.model.vo.User;
import com.kh.banzi.user.model.vo.User;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1596163292607L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1595989111466L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<title>비밀번호 변경</title>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("\r\n");
      out.write("<!-- Required meta tags -->\r\n");
      out.write("<meta name=\"viewport\"\r\n");
      out.write("\tcontent=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\r\n");
      out.write("<!-- Bootstrap CSS -->\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\"\r\n");
      out.write("\tintegrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\"\r\n");
      out.write("\tcrossorigin=\"anonymous\" />\r\n");
      out.write("<!-- jQuery -->\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.5.1.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- CSS -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/resources/css/login.css\">\r\n");
      out.write("\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@9\"></script>\r\n");
      out.write("<style>\r\n");
      out.write("#search-id {\r\n");
      out.write("\tline-height: 2.3em !important;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#search-area a:hover {\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tcolor: #ffce54;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#loginBtn {\r\n");
      out.write("\tfont-family: \"yg-jalnan\" !important;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body id=\"login\">\r\n");
      out.write("\t");
      out.write('\n');
      out.write('\n');
	
	User loginUser = (User)session.getAttribute("loginUser");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>Insert title here</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("  <meta charset=\"UTF-8\" />\n");
      out.write("\n");
      out.write("  <!-- Required meta tags -->\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\n");
      out.write("  <!-- Bootstrap CSS -->\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\"\n");
      out.write("    integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\" />\n");
      out.write("  <!-- jQuery -->\n");
      out.write("  <script src=\"https://code.jquery.com/jquery-3.5.1.min.js\"></script>\n");
      out.write("\n");
      out.write("    <!-- CSS -->\n");
      out.write("   <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/resources/css/style.css\">\n");
      out.write("\n");
      out.write("  <title>BAN JI</title>\n");
      out.write("  <style>\n");
      out.write("  \n");
      out.write("/* -------------- body -------------- */\n");
      out.write("\n");
      out.write("* {\n");
      out.write("/* border: 1px solid black; */\n");
      out.write("margin: 0;\n");
      out.write("padding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".row>div {\n");
      out.write("width: 100%;\n");
      out.write("height: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* -------------- header -------------- */\n");
      out.write("header{\n");
      out.write("position: fixed;\n");
      out.write("height : 157px;\n");
      out.write("width: 100%;\n");
      out.write("z-index : 100;\n");
      out.write("background-color : white;\n");
      out.write("top : 0px;\n");
      out.write("}\n");
      out.write("#main-logo{\n");
      out.write("padding: 11px;\n");
      out.write("}\n");
      out.write("#header button {\n");
      out.write("float: right;\n");
      out.write("margin: 1em;\n");
      out.write("width: 6em;\n");
      out.write("height: 2em;\n");
      out.write("border: 1px solid lightgray;\n");
      out.write("font-family: \"InfinitySans-RegularA1\";\n");
      out.write("font-size: 0.7em;\n");
      out.write("color: lightgray;\n");
      out.write("background-color: transparent;\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("/* -------------- nav -------------- */\n");
      out.write(".dropdown:hover .dropdown-menu { \n");
      out.write("display: block;\n");
      out.write("margin-top: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#nav{\n");
      out.write("border-top: 1px solid lightgray;\n");
      out.write("border-bottom: 1px solid lightgray;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#nav * {\n");
      out.write("font-family: \"InfinitySans-RegularA1\";\n");
      out.write("text-align: center;\n");
      out.write("vertical-align: middle;\n");
      out.write("border: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#nav a {\n");
      out.write("width: 10rem;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#nav li {\n");
      out.write("width: 10%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".nav-link {\n");
      out.write("color: #3a3847;\n");
      out.write("width: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".nav-link:hover {\n");
      out.write("color: #ffce54;\n");
      out.write("border: none;\n");
      out.write("border-bottom: 3px solid !important;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".dropdown-menu {\n");
      out.write("border-top: 5px solid transparent !important;\n");
      out.write("width: 10rem;\n");
      out.write("background-color: rgb(250, 250, 250);\n");
      out.write("box-shadow: 0 10px 10px lightgrey;\n");
      out.write("padding-bottom: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".dropdown-item {\n");
      out.write("font-size: small;\n");
      out.write("margin-top: 10px;\n");
      out.write("background-color: transparent;\n");
      out.write("opacity: 0.7;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".dropdown-item:hover {\n");
      out.write("background-color: #ffce54;\n");
      out.write("color: white;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#userNameArea{\n");
      out.write("\tborder: none !important;\n");
      out.write("\tcolor: #3a3847 !important;\n");
      out.write("}\n");
      out.write("  </style>\n");
      out.write("  \n");
      out.write("  <!--------------------------------- sweet alert ---------------------------------------- -->\n");
      out.write("  <script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>\n");
      out.write("  \n");
      out.write("  <script>\n");
      out.write("  ");

 	 String status = (String)(request.getSession().getAttribute("status"));
 	 String msg = (String)(request.getSession().getAttribute("msg"));
 	 String text = (String)(request.getSession().getAttribute("text"));
  
      out.write("\n");
      out.write("  \n");
      out.write("  ");
 if(msg != null){
      out.write("\n");
      out.write("\t\t \t swal({\n");
      out.write("\t\t \t\t icon : \"");
      out.print(status);
      out.write("\", \n");
      out.write("\t\t \t\t title : \"");
      out.print(msg);
      out.write("\",\n");
      out.write("\t\t \t\t text : \"");
      out.print(text != null ? text : "" );
      out.write("\" \n");
      out.write("\t\t \t });\n");
      out.write(" \t ");

 	 		session.removeAttribute("msg");
 	 		session.removeAttribute("status");
 	 		session.removeAttribute("text");
  	}
 	 
      out.write("\n");
      out.write("  \n");
      out.write("  </script>\n");
      out.write("  \n");
      out.write("<body>\n");
      out.write("  <header>\n");
      out.write("    <div class=\"container-fluid\">\n");
      out.write("      <!--  ------------ header ------------ -->\n");
      out.write("      <div class=\"row\" id=\"header\">\n");
      out.write("        <div class=\"col-md-4\">\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col-md-4\">\n");
      out.write("          <a href=\"");
      out.print(request.getContextPath());
      out.write("\">\n");
      out.write("            <img src=\"");
      out.print(request.getContextPath());
      out.write("/resources/img/logo_main.png\" width=\"200px\" class=\"mx-auto d-block\" id=\"main-logo\">\n");
      out.write("          </a>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col-md-4\">\n");
      out.write("          <button type=\"button\" onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/user/signUpAssign1.do'\">회원가입</button>\n");
      out.write("          \n");
      out.write("          ");
 if(loginUser == null) {
      out.write("\n");
      out.write("          \t <button type=\"button\" onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/userLogin/loginForm.do'\">로그인</button>\n");
      out.write("          ");
 } else{ 
      out.write("\n");
      out.write("          \t <button type=\"button\" onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/userLogin/logout.do'\">로그아웃</button>\n");
      out.write("          \t <button type=\"button\" id=\"userNameArea\" onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/myPage/changeUserForm.do'\">");
      out.print(loginUser.getUserName() );
      out.write(" 님</button>\n");
      out.write("          \t \n");
      out.write("          ");
 } 
      out.write("\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <!--  ------------ nav ------------ -->\n");
      out.write("    <div class=\"row\" id=\"nav\">\n");
      out.write("      <div class=\"col-md-12\">\n");
      out.write("        <ul class=\"nav nav-tabs justify-content-center\" id=\"nav-wrapper\">\n");
      out.write("          <li class=\"nav-item dropdown\">\n");
      out.write("            <a class=\"nav-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/user/introduce.do\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">구성원 소개</a>\n");
      out.write("          </li>\n");
      out.write("          <li class=\"nav-item dropdown\">\n");
      out.write("            <a class=\"nav-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/information/list.do?type=2\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">정보</a>\n");
      out.write("          </li>\n");
      out.write("          <li class=\"nav-item dropdown\">\n");
      out.write("            <a class=\"nav-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/community/list.do\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">커뮤니티</a>\n");
      out.write("            <div class=\"dropdown-menu\">\n");
      out.write("              <a class=\"dropdown-item\" href=\"community/notice.do\">공지사항</a>\n");
      out.write("              <a class=\"dropdown-item\" href=\"");
      out.print(request.getContextPath());
      out.write("/community/list.do\">자유 게시판</a>\n");
      out.write("              <a class=\"dropdown-item\" href=\"");
      out.print(request.getContextPath());
      out.write("/qna/list.do\">Q&A</a>\n");
      out.write("              <a class=\"dropdown-item\" href=\"");
      out.print(request.getContextPath());
      out.write("/review/review.do?type=3\">사용 후기</a>\n");
      out.write("            </div>\n");
      out.write("          </li>\n");
      out.write("          <li class=\"nav-item dropdown\">\n");
      out.write("            <a class=\"nav-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/event/eventList.do?type=1\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">이벤트</a>\n");
      out.write("            <div class=\"dropdown-menu\">\n");
      out.write("              <a class=\"dropdown-item\"  href=\"");
      out.print(request.getContextPath());
      out.write("/event/eventList.do?type=1\">진행중인 이벤트</a>\n");
      out.write("              <a class=\"dropdown-item\"  href=\"");
      out.print(request.getContextPath());
      out.write("/event/pastList.do?type=2\">종료된 이벤트</a>\n");
      out.write("              <a class=\"dropdown-item\"  href=\"");
      out.print(request.getContextPath());
      out.write("/event/winnerList.do?type=3\">당첨자 발표</a>\n");
      out.write("            </div>\n");
      out.write("          </li>\n");
      out.write("          <li class=\"nav-item dropdown\">\n");
      out.write("            <a class=\"nav-link\" href=\"");
      out.print(request.getContextPath());
      out.write("/myPage/changeUserForm.do\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">마이페이지</a>\n");
      out.write("            <div class=\"dropdown-menu\">\n");
      out.write("              <a class=\"dropdown-item\" href=\"");
      out.print(request.getContextPath());
      out.write("/myPage/changeUserForm.do\">회원정보 수정</a>\n");
      out.write("              <a class=\"dropdown-item\" href=\"");
      out.print(request.getContextPath());
      out.write("/myPage/changePwdForm.do\">비밀번호 수정</a>\n");
      out.write("              <a class=\"dropdown-item\" href=\"");
      out.print(request.getContextPath());
      out.write("/myPage/secessionForm.do\">회원 탈퇴</a>\n");
      out.write("            </div>\n");
      out.write("          </li>\n");
      out.write("        </ul>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("  </header>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("  <script>\n");
      out.write("    $(function () {\n");
      out.write("      $(\".nav-link\").on(\"mouseenter\", function () {\n");
      out.write("        var idx = $(this).index(\".nav-link\");\n");
      out.write("\n");
      out.write("        $(\".nav-link\").each(function (index, item) {\n");
      out.write("          if (index == idx) {\n");
      out.write("            $(item)\n");
      out.write("              .css(\"color\", \"#ffce54\")\n");
      out.write("              .css(\"border-bottom\", \"3px solid #ffce54\");\n");
      out.write("          } else {\n");
      out.write("            $(item).css(\"color\", \"#3a3847\").css(\"border-bottom\", \"none\");\n");
      out.write("          }\n");
      out.write("        });\n");
      out.write("      });\n");
      out.write("\n");
      out.write("      $(\"#nav\").on(\"mouseleave\", function () {\n");
      out.write("        $(\".nav-link\")\n");
      out.write("          .css(\"color\", \"#3a3847\")\n");
      out.write("          .css(\"border-bottom\", \"3px solid transparent\");\n");
      out.write("      });\n");
      out.write("    });\n");
      out.write("  </script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("  <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\"\n");
      out.write("    integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\"\n");
      out.write("    crossorigin=\"anonymous\"></script>\n");
      out.write("  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\"\n");
      out.write("    integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\"\n");
      out.write("    crossorigin=\"anonymous\"></script>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("    <div class=\"limiter\">\r\n");
      out.write("        <div class=\"container-login100\">\r\n");
      out.write("            <div class=\"wrap-login100 p-t-50 p-b-90\">\r\n");
      out.write("                <form class=\"form-signin\" method=\"POST\" action=\"");
      out.print(request.getContextPath());
      out.write("/userLogin/search.do\" onsubmit=\"return validate();\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    <span class=\"login100-form-title p-b-51\">비밀번호 변경</span>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"wrap-input100 validate-input m-b-16\">\r\n");
      out.write("                        <input class=\"input100\" type=\"text\" name=\"userId\" placeholder=\"아이디\" id=\"userId\">\r\n");
      out.write("                        <span class=\"focus-input100\"></span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"wrap-input100 validate-input m-b-16\">\r\n");
      out.write("                        <select class=\"input100\" id=\"userQuestion\" name=\"userQuestion\">\r\n");
      out.write("                        <option value=\"none1\">보안질문</option>\r\n");
      out.write("                        <option value=\"none2\">-------------------------------------------------------</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"first\">내 학창시절 별명은?</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"second\">가장 좋아했던 동화책의 제목은?</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"third\">첫 애완동물의 이름은?</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                     <div class=\"wrap-input100 validate-input m-b-16\">\r\n");
      out.write("                        <input class=\"input100\" type=\"text\" name=\"userAnswer\" placeholder=\"보안 답변\" id=\"userAnswer\">\r\n");
      out.write("                        <span class=\"focus-input100\"></span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    <hr>\r\n");
      out.write("                    \r\n");
      out.write("                     <div class=\"wrap-input100 validate-input m-b-16\">\r\n");
      out.write("                        <input class=\"input100\" type=\"password\" name=\"userPwd\" placeholder=\"새 비밀번호\" id=\"userPwd\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                        <span id=\"check-area1\"></span><br><br>\r\n");
      out.write("                    \r\n");
      out.write("                     <div class=\"wrap-input100 validate-input m-b-16\">\r\n");
      out.write("                        <input class=\"input100\" type=\"password\" name=\"userPwdCheck\" placeholder=\"새 비밀번호 확인\" id=\"userPwdCheck\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                      \t<span id=\"check-area2\"></span><br><br>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"container-login100-form-btn m-t-17\">\r\n");
      out.write("                        <button type=\"submit\" class=\"login100-form-btn\" id=\"loginBtn\">search</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
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
      out.write("\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\r\n");
      out.write("\t\tvar pwdCheck = { \r\n");
      out.write("\t\t\t\t\"userPwd\":false,\r\n");
      out.write("\t\t\t\t\"userPwdCheck\":false,\r\n");
      out.write("\t\t\t};\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar $changePwd = $(\"#userPwd, #userPwdCheck\");\r\n");
      out.write("\t\t$changePwd.on(\"input\", function(){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar regExp = /^[A-Za-z0-9]{6,12}$/;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif(!regExp.test($(\"#userPwd\").val())){ \r\n");
      out.write("\t\t\t\t$(\"#check-area1\").text(\"비밀번호 형식이 유효하지 않습니다.\").css(\"color\",\"red\");\r\n");
      out.write("\t\t\t\tpwdCheck.userPwd = false;\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t$(\"#check-area1\").text(\"유효한 비밀번호 형식입니다.\").css(\"color\",\"green\");\r\n");
      out.write("\t\t\t\tpwdCheck.userPwd = true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif(!pwdCheck.userPwd && $(\"#userPwdCheck\").val().length > 0){\r\n");
      out.write("\t\t\tswal(\"유효한 비밀번호를 작성해 주세요.\");\r\n");
      out.write("\t\t\t$(\"#userPwdCheck\").val(\"\");\r\n");
      out.write("\t\t\t$(\"#userPwd\").focus();\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}else if(pwdCheck.userPwd && $(\"#userPwdCheck\").val().length > 0){\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif($(\"#userPwd\").val().trim() != $(\"#userPwdCheck\").val().trim()){\r\n");
      out.write("\t\t\t\t$(\"#check-area2\").text(\"비밀번호 불일치\").css(\"color\",\"red\");\r\n");
      out.write("\t\t\t\tpwdCheck.checkPwd = false;\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t$(\"#check-area2\").text(\"비밀번호 일치\").css(\"color\",\"green\");\r\n");
      out.write("\t\t\t\tpwdCheck.checkPwd = true;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t\tfunction validate() {\r\n");
      out.write("\r\n");
      out.write("\t\t\tif ($(\"#userId\").val().trim() == \"\") {\r\n");
      out.write("\t\t\t\tswal({\r\n");
      out.write("\t\t\t\t    icon: \"info\", \r\n");
      out.write("\t\t\t\t    title: \"아이디를 입력해 주세요.\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#userId\").focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif (($(\"#userQuestion\").val() == \"none1\") || ($(\"#userQuestion\").val() == \"none2\")) {\r\n");
      out.write("\t\t\t\tswal({\r\n");
      out.write("\t\t\t\t    icon: \"info\", \r\n");
      out.write("\t\t\t\t    title: \"보안 질문을 선택해 주세요.\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#userQuestion\").focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif ($(\"#userAnswer\").val().trim() == \"\") {\r\n");
      out.write("\t\t\t\tswal({\r\n");
      out.write("\t\t\t\t    icon: \"info\", \r\n");
      out.write("\t\t\t\t    title: \"보안 답변을 입력해 주세요.\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#userAnswer\").focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif ($(\"#userPwd\").val().trim() == \"\") {\r\n");
      out.write("\t\t\t\tswal({\r\n");
      out.write("\t\t\t\t    icon: \"info\", \r\n");
      out.write("\t\t\t\t    title: \"새 비밀번호를 입력해 주세요.\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#userPwd\").focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif ($(\"#userPwdCheck\").val().trim() == \"\") {\r\n");
      out.write("\t\t\t\tswal({\r\n");
      out.write("\t\t\t\t    icon: \"info\", \r\n");
      out.write("\t\t\t\t    title: \"새 비밀번호 확인를 입력해 주세요.\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#userPwdCheck\").focus();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tfor(var key in pwdCheck){\r\n");
      out.write("\t\t\t\tif(!signUpCheck[key]){\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tvar msg;\r\n");
      out.write("\t\t\t\t\tswitch(key){\r\n");
      out.write("\t\t\t\t\tcase \"userPwd\" : msg=\"새 비밀번호가\";  break;\r\n");
      out.write("\t\t\t\t\tcase \"userPwdCheck\" : msg=\"새 비밀번호 확인이\";  break;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tswal({\r\n");
      out.write("\t\t\t\t\t    icon: \"info\", \r\n");
      out.write("\t\t\t\t\t    title: msg + \"유효하지 않습니다.\"\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\tvar el = \"#\"+key;\r\n");
      out.write("\t\t\t\t\t$(el).focus();\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("\t</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<script\r\n");
      out.write("\tsrc=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\"\r\n");
      out.write("\tintegrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\"\r\n");
      out.write("\tcrossorigin=\"anonymous\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\"\r\n");
      out.write("\tintegrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\"\r\n");
      out.write("\tcrossorigin=\"anonymous\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
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
