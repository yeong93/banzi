/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.56
 * Generated at: 2020-07-29 05:20:10 UTC
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

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1595999928850L));
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
      out.write("\t\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<title>로그인</title>\r\n");
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
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/resources/css/style.css\">\r\n");
      out.write("  \r\n");
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
      out.write("\r\n");
      out.write("    <div class=\"limiter\">\r\n");
      out.write("        <div class=\"container-login100\">\r\n");
      out.write("            <div class=\"wrap-login100 p-t-50 p-b-90\">\r\n");
      out.write("                <form class=\"form-signin\" method=\"POST\" action=\"");
      out.print(request.getContextPath());
      out.write("/login/login.do\" onsubmit=\"return fnLogin();\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    <span class=\"login100-form-title p-b-51\">로그인</span>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"wrap-input100 validate-input m-b-16\">\r\n");
      out.write("                        <input class=\"input100\" type=\"text\" name=\"userId\" placeholder=\"아이디\" id=\"userId\" value=\"");
      out.print(rememberId);
      out.write("\">\r\n");
      out.write("                        <span class=\"focus-input100\"></span>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"wrap-input100 validate-input m-b-16\">\r\n");
      out.write("                        <input class=\"input100\" type=\"password\" name=\"userPwd\" placeholder=\"비밀번호\" id=\"userPwd\">\r\n");
      out.write("                        <span class=\"focus-input100\"></span>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"flex-sb-m w-full p-t-3 p-b-24\">\r\n");
      out.write("                        <div class=\"contact100-form-checkbox\">\r\n");
      out.write("                            <div id=\"remember-area\">\r\n");
      out.write("                                <input class=\"input-checkbox100\" type=\"checkbox\" id=\"rememberId\"  name=\"rememberId\" ");
      out.print( isRemember ? "checked" : "" );
      out.write(">\r\n");
      out.write("                                <label class=\"label-checkbox100\" for=\"rememberId\">아이디 기억하기</label>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div id=\"search-area\">\r\n");
      out.write("                                <a href=\"");
      out.print(request.getContextPath());
      out.write("/userLogin/searchIdForm.do\" class=\"search\" name=\"search-id\">아이디 찾기&nbsp;&nbsp;</a>\r\n");
      out.write("                                <a href=\"");
      out.print(request.getContextPath());
      out.write("/userLogin/searchPwdForm.do\" class=\"search\" name=\"seearch-Pwd\">비밀번호 찾기</a>\r\n");
      out.write("                            </div>\r\n");
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"container-login100-form-btn m-t-17\">\r\n");
      out.write("                        <button type=\"submit\" class=\"login100-form-btn\" id=\"loginBtn\">Login</button>\r\n");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <script>\r\n");
      out.write("\t\r\n");
      out.write("    \tfunction fnLogin(){\r\n");
      out.write("    \t\tif(loginValidate()){\r\n");
      out.write("    \t\t\t$.ajax({\r\n");
      out.write("    \t\t\t\turl: \"");
      out.print(request.getContextPath());
      out.write("/userLogin/login.do\",\r\n");
      out.write("    \t\t\t\tdata: {\"userId\":$(\"#userId\").val(),\r\n");
      out.write("    \t\t\t\t\t   \"userPwd\":$(\"#userPwd\").val()},\r\n");
      out.write("    \t\t\t\ttype: \"POST\",\r\n");
      out.write("    \t\t\t\tdataType: \"JSON\",\r\n");
      out.write("    \t\t\t\tsuccess: function(fail){\r\n");
      out.write("    \t\t\t\t\tif(fail == 1){\r\n");
      out.write("    \t\t\t\t\t\tswal({\r\n");
      out.write("    \t\t\t\t\t\t\ticon : \"error\",\r\n");
      out.write("    \t\t\t\t\t\t\ttitle: \"로그인 실패\",\r\n");
      out.write("    \t\t\t\t\t\t\ttext : \"아이디, 비밀번호를 확인해주세요.\\n\"\r\n");
      out.write("    \t\t\t\t\t\t\t        + \"\\n또는 권한을 요청한 회원은 권한요청 승낙을 기다려 주세요. \"\r\n");
      out.write("    \t\t\t\t\t\t})\r\n");
      out.write("    \t\t\t\t\t\t\r\n");
      out.write("    \t\t\t\t\t\t$(\"#userId\").val() == \"\";\r\n");
      out.write("    \t\t\t\t\t\t$(\"#userPwd\").val() == \"\";\r\n");
      out.write("    \t\t\t\t\t}else{\r\n");
      out.write("    \t\t\t\t\t\tlocation.href = \"");
      out.print(request.getHeader("referer"));
      out.write("\";\r\n");
      out.write("    \t\t\t\t\t}\r\n");
      out.write("    \t\t\t\t}, error : function(){\r\n");
      out.write("    \t\t\t\t\tconsole.log(\"ajax 통신 실패\")\r\n");
      out.write("    \t\t\t\t}\r\n");
      out.write("    \t\t\t});\r\n");
      out.write("    \t\t}\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\treturn false;\r\n");
      out.write("    \t}\r\n");
      out.write("    \t\r\n");
      out.write("        function loginValidate() {\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t\tif($(\"#userId\").val().trim() == \"\"){\r\n");
      out.write("\t\t\t\t\t\talert(\"아이디를 입력해 주세요.\");\r\n");
      out.write("\t\t\t\t\t\t$(\"#userId\").focus();\r\n");
      out.write("\t\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tif($(\"#userPwd\").val().trim() == \"\"){\r\n");
      out.write("\t\t\t\t\t\talert(\"비밀번호를 입력해 주세요.\");\r\n");
      out.write("\t\t\t\t\t\t$(\"#userPwd\").focus();\r\n");
      out.write("\t\t\t\t\t\treturn false; \r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\treturn true;\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("        \r\n");
      out.write("      \r\n");
      out.write("        \r\n");
      out.write("\r\n");
      out.write("    </script>\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("<script\r\n");
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
