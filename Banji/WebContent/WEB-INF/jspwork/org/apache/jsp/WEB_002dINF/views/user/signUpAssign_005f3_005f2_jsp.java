/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.56
 * Generated at: 2020-08-06 06:16:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class signUpAssign_005f3_005f2_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>회원 가입 완료 - 전문가</title>\r\n");
      out.write("<style>\r\n");
      out.write(" @font-face {\r\n");
      out.write("      font-family: \"InfinitySans-RegularA1\";\r\n");
      out.write("      src: url(\"https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/InfinitySans-RegularA1.woff\") format(\"woff\");\r\n");
      out.write("      font-weight: normal;\r\n");
      out.write("      font-style: normal;\r\n");
      out.write("    }\r\n");
      out.write("   *{\r\n");
      out.write("\tfont-family : InfinitySans-RegularA1;}\r\n");
      out.write("\t.header {width:800px; height:150px; margin:0 auto;background:white; }\r\n");
      out.write("\r\n");
      out.write("    #section{\r\n");
      out.write("        text-align: center;\r\n");
      out.write("    }\r\n");
      out.write("    #main {margin: auto auto; margin-top:40px; margin-bottom:100px; clear:both; width:300px; height:45px; background-color :#ffce54; color:white; border:none;\r\n");
      out.write("    }\r\n");
      out.write("    #back{\r\n");
      out.write("   \theight:10px;\r\n");
      out.write("   \t}\r\n");
      out.write("</style>\r\n");
      out.write("  <!-- Bootstrap CSS -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\"\r\n");
      out.write("    integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\" />\r\n");
      out.write("  <!-- jQuery -->\r\n");
      out.write("  <script src=\"https://code.jquery.com/jquery-3.5.1.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <!-- sweetAlert창 추가 -->\r\n");
      out.write("    <script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>\r\n");
      out.write("    <script>\r\n");
      out.write("\t\t");

  		String msg = (String)(request.getSession().getAttribute("msg"));
  		String status = (String)(request.getSession().getAttribute("status"));
  		String text = (String)(request.getSession().getAttribute("text"));
  		
      out.write("\r\n");
      out.write("  \t\t\r\n");
      out.write("  \t\t");
 if (msg != null){ 
      out.write("\r\n");
      out.write("\t  \t\tswal({\r\n");
      out.write("\t  \t\t\ticon : \"");
      out.print(status);
      out.write("\",\r\n");
      out.write("\t  \t\t\ttitle : \"");
      out.print(msg);
      out.write("\",\r\n");
      out.write("\t  \t\t\ttext : \"");
      out.print(text != null ? text : "");
      out.write("\"\r\n");
      out.write("\t  \t\t});\r\n");
      out.write("  \t\t");

  			// Session에 존재하는 특정 키값의 속성 제거
  			session.removeAttribute("msg");
  			session.removeAttribute("status");
  			session.removeAttribute("text");
  		}
  		
      out.write("\r\n");
      out.write("    </script>\r\n");
      out.write("    <div id=\"back\"></div>\r\n");
      out.write("    <div class=\"header\">       \r\n");
      out.write("       <img src=\"");
      out.print(request.getContextPath());
      out.write("/resources/img/logo_main.png\" class=\"mx-auto d-block\" id=\"main-logo\" width=\"190px\">\r\n");
      out.write("    </div>\r\n");
      out.write("       <div id=\"section\">\r\n");
      out.write("       <br>\r\n");
      out.write("       <h3>전문가 승인이 진행중입니다.<br> 관리자가 승인시 회원 가입이 완료됩니다.</h3>\r\n");
      out.write("              <button type=\"button\" id=\"main\" onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("'\">홈으로</button>\r\n");
      out.write("    </div> \r\n");
      out.write("</body>\r\n");
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
