/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.56
 * Generated at: 2020-08-05 00:22:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class signUpAssign_005f2_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1595989111466L));
  }

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
      out.write("<html lang=\"ko\">\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta charset=\"utf-8\">\r\n");
      out.write("        <!-- meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0\"/ -->\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("        <title>회원가입</title>\r\n");
      out.write("        <!-- Bootstrap -->\r\n");
      out.write("        <link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("        <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->\r\n");
      out.write("        <script src=\"http://code.jquery.com/jquery.js\"></script>\r\n");
      out.write("        <!-- 모든 합쳐진 플러그인을 포함하거나 (아래) 필요한 각각의 파일들을 포함하세요 -->\r\n");
      out.write("        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n");
      out.write("        <!-- Respond.js 으로 IE8 에서 반응형 기능을 활성화하세요 (https://github.com/scottjehl/Respond) -->\r\n");
      out.write("        <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>\r\n");
      out.write("    <style>\r\n");
      out.write("    /* 인피니티산스 Regular */\r\n");
      out.write("    @font-face {\r\n");
      out.write("        font-family: \"InfinitySans-RegularA1\";\r\n");
      out.write("        src: url(\"https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/InfinitySans-RegularA1.woff\") format(\"woff\");\r\n");
      out.write("        font-weight: normal;\r\n");
      out.write("        font-style: normal;\r\n");
      out.write("        }\r\n");
      out.write("        *{\r\n");
      out.write("            font-family: \"InfinitySans-RegularA1\";\r\n");
      out.write("        }\r\n");
      out.write("        #btn, #btn2{\r\n");
      out.write("            background-color:  #ffce54; \r\n");
      out.write("            border: none;\r\n");
      out.write("        }\r\n");
      out.write("        #back{\r\n");
      out.write("        \theight:10px;\r\n");
      out.write("        }\r\n");
      out.write("        #group{\r\n");
      out.write("        \tmargin:auto;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div class=\"container\"><!-- 좌우측의 공간 확보 -->\r\n");
      out.write("            <!-- 헤더 들어가는 부분 -->\r\n");
      out.write("            <div id=\"back\"></div>\r\n");
      out.write("            <div>\r\n");
      out.write("            <a href=\"");
      out.print(request.getContextPath());
      out.write("\">\r\n");
      out.write("            <img src=\"");
      out.print(request.getContextPath());
      out.write("/resources/img/logo_main.png\" class=\"mx-auto d-block\" id=\"main-logo\" width=\"180px\">\r\n");
      out.write("            </a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <!--// 헤더 들어가는 부분 -->\r\n");
      out.write("            <!-- 모달창 -->\r\n");
      out.write("            <div class=\"modal fade\" id=\"defaultModal\">\r\n");
      out.write("                <div class=\"modal-dialog\">\r\n");
      out.write("                    <div class=\"modal-content\">\r\n");
      out.write("                        <div class=\"modal-header\">\r\n");
      out.write("                            <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×</button>\r\n");
      out.write("                            <h5 class=\"modal-title\"></h5>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"modal-body\">\r\n");
      out.write("                            <p class=\"modal-contents\"></p>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"modal-footer\">\r\n");
      out.write("                            <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">닫기</button>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div><!-- /.modal-content -->\r\n");
      out.write("                </div><!-- /.modal-dialog -->\r\n");
      out.write("            </div><!-- /.modal -->\r\n");
      out.write("            <!--// 모달창 -->\r\n");
      out.write("            <hr/>\r\n");
      out.write("            <!-- 본문 들어가는 부분 -->\r\n");
      out.write("            <h2>2. 회원가입</h2>\r\n");
      out.write("            <br>\r\n");
      out.write("            <form class=\"form-horizontal\" role=\"form\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/user/signUp.do\">\r\n");
      out.write("                <div class=\"form-group\" id=\"divId\">\r\n");
      out.write("                    <label for=\"inputId\" class=\"col-lg-2 control-label\">아이디</label>\r\n");
      out.write("                    <span id=\"checkId\">&nbsp;</span>\r\n");
      out.write("                    <div class=\"col-lg-10\">\r\n");
      out.write("                        <input type=\"text\" class=\"form-control onlyAlphabetAndNumber\" id=\"id\" name=\"id\" data-rule-required=\"true\" placeholder=\"아이디는 첫글자 영어 소문자,이후 영어 대/소문자, 6글자 이상 숫자로 12자 이내입니다.\" maxlength=\"30\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\" id=\"divPassword\">\r\n");
      out.write("                    <label for=\"inputPassword\" class=\"col-lg-2 control-label\">비밀번호</label>\r\n");
      out.write("                    <div class=\"col-lg-10\">\r\n");
      out.write("                        <input type=\"password\" class=\"form-control\" id=\"password\" name=\"pwd\" data-rule-required=\"true\" placeholder=\"비밀번호\" maxlength=\"30\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\" id=\"divPasswordCheck\">\r\n");
      out.write("                    <label for=\"inputPasswordCheck\" class=\"col-lg-2 control-label\">비밀번호 확인</label>\r\n");
      out.write("                    <span id=\"checkPwd\">&nbsp;</span>\r\n");
      out.write("                    <div class=\"col-lg-10\">\r\n");
      out.write("                        <input type=\"password\" class=\"form-control\" id=\"passwordCheck\" name=\"password2\" data-rule-required=\"true\" placeholder=\"비밀번호 확인\" maxlength=\"30\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\" id=\"divName\">\r\n");
      out.write("                    <label for=\"inputName\" class=\"col-lg-2 control-label\">닉네임</label>\r\n");
      out.write("                    <div class=\"col-lg-10\">\r\n");
      out.write("                        <input type=\"text\" class=\"form-control onlyHangul\" id=\"name\" name=\"name\" data-rule-required=\"true\" placeholder=\"닉네임을 입력해주세요.\" maxlength=\"15\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"form-group\" id=\"divEmail\">\r\n");
      out.write("                    <label for=\"inputEmail\" class=\"col-lg-2 control-label\">이메일</label>\r\n");
      out.write("                    <div class=\"col-lg-10\">\r\n");
      out.write("                        <input type=\"email\" class=\"form-control\" id=\"email\" name=\"email\" data-rule-required=\"true\" placeholder=\"이메일\" maxlength=\"40\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\" id=\"divGrade\">\r\n");
      out.write("                    <label for=\"inputEmail\" class=\"col-lg-2 control-label\">회원등급</label>\r\n");
      out.write("                    <div class=\"col-lg-10\">\r\n");
      out.write("                        <input type=\"radio\" id=\"user\" name=\"grade\" value=\"user\" checked=\"checked\">사용자\r\n");
      out.write("                        <input type=\"radio\" id=\"user\" name=\"grade\" value=\"veterinarian\">수의사\r\n");
      out.write("                        <input type=\"radio\" id=\"user\" name=\"grade\" value=\"animaltrainer\">훈련사\r\n");
      out.write("                        <input type=\"radio\" id=\"user\" name=\"grade\" value=\"editor\">에디터\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    <label for=\"inputQuestion\" class=\"col-lg-2 control-label\">보안질문</label>\r\n");
      out.write("                    <div class=\"col-lg-10\">\r\n");
      out.write("                        <select class=\"form-control\" id=\"question\" name=\"question\">\r\n");
      out.write("                            <option value=\"first\">내 학창시절 별명은?</option>\r\n");
      out.write("                            <option value=\"second\">가장 좋아했던 동화책의 제목은?</option>\r\n");
      out.write("                            <option value=\"third\">첫 애완동물 이름은?</option>\r\n");
      out.write("                        </select>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\" id=\"divAnswer\">\r\n");
      out.write("                    <label for=\"inputAnswer\" class=\"col-lg-2 control-label\">질문답변</label>\r\n");
      out.write("                    <div class=\"col-lg-10\">\r\n");
      out.write("                        <input type=\"text\" class=\"form-control onlyHangul\" name=\"answer\" id=\"answer\" data-rule-required=\"true\" placeholder=\"답변을 작성해주세요.\" maxlength=\"15\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                \t<div id=\"group\">\r\n");
      out.write("                    <div class=\"col-lg-offset-2 col-lg-10 text-center\">\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-primary color\" id=\"btn\" >회원가입</button>\r\n");
      out.write("                        <button type=\"reset\" class=\"btn btn-primary color\" id=\"btn2\">취소</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("            </form>\r\n");
      out.write("        \r\n");
      out.write("        <script>\r\n");
      out.write("        \r\n");
      out.write("            $(function(){\r\n");
      out.write("                //모달을 전역변수로 선언\r\n");
      out.write("                var modalContents = $(\".modal-contents\");\r\n");
      out.write("                var modal = $(\"#defaultModal\");\r\n");
      out.write("\r\n");
      out.write("                //------- 검사하여 상태를 class에 적용\r\n");
      out.write("                $('#id').keyup(function(event){\r\n");
      out.write("                    \r\n");
      out.write("                    var divId = $('#divId');\r\n");
      out.write("                    \r\n");
      out.write("                    if($('#id').val()==\"\"){\r\n");
      out.write("                        divId.removeClass(\"has-success\");\r\n");
      out.write("                        divId.addClass(\"has-error\");\r\n");
      out.write("                    }else{\r\n");
      out.write("                        divId.removeClass(\"has-error\");\r\n");
      out.write("                        divId.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("                });\r\n");
      out.write("                \r\n");
      out.write("                $('#password').keyup(function(event){\r\n");
      out.write("                    \r\n");
      out.write("                    var divPassword = $('#divPassword');\r\n");
      out.write("                    \r\n");
      out.write("                    if($('#password').val()==\"\"){\r\n");
      out.write("                        divPassword.removeClass(\"has-success\");\r\n");
      out.write("                        divPassword.addClass(\"has-error\");\r\n");
      out.write("                    }else{\r\n");
      out.write("                        divPassword.removeClass(\"has-error\");\r\n");
      out.write("                        divPassword.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("                });\r\n");
      out.write("                \r\n");
      out.write("                $('#passwordCheck').keyup(function(event){\r\n");
      out.write("                    \r\n");
      out.write("                    var passwordCheck = $('#passwordCheck').val();\r\n");
      out.write("                    var password = $('#password').val();\r\n");
      out.write("                    var divPasswordCheck = $('#divPasswordCheck');\r\n");
      out.write("                    \r\n");
      out.write("                    if((passwordCheck==\"\") || (password!=passwordCheck)){\r\n");
      out.write("                        divPasswordCheck.removeClass(\"has-success\");\r\n");
      out.write("                        divPasswordCheck.addClass(\"has-error\");\r\n");
      out.write("                    }else{\r\n");
      out.write("                        divPasswordCheck.removeClass(\"has-error\");\r\n");
      out.write("                        divPasswordCheck.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("                });\r\n");
      out.write("                \r\n");
      out.write("                $('#name').keyup(function(event){\r\n");
      out.write("                    \r\n");
      out.write("                    var divName = $('#divName');\r\n");
      out.write("                    \r\n");
      out.write("                    if($.trim($('#name').val())==\"\"){\r\n");
      out.write("                        divName.removeClass(\"has-success\");\r\n");
      out.write("                        divName.addClass(\"has-error\");\r\n");
      out.write("                    }else{\r\n");
      out.write("                        divName.removeClass(\"has-error\");\r\n");
      out.write("                        divName.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("                });\r\n");
      out.write("\r\n");
      out.write("                $('#email').keyup(function(event){\r\n");
      out.write("                    \r\n");
      out.write("                    var divEmail = $('#divEmail');\r\n");
      out.write("                    \r\n");
      out.write("                    if($.trim($('#email').val())==\"\"){\r\n");
      out.write("                        divEmail.removeClass(\"has-success\");\r\n");
      out.write("                        divEmail.addClass(\"has-error\");\r\n");
      out.write("                    }else{\r\n");
      out.write("                        divEmail.removeClass(\"has-error\");\r\n");
      out.write("                        divEmail.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("                });\r\n");
      out.write("                \r\n");
      out.write("                //------- validation 검사\r\n");
      out.write("                $( \"form\" ).submit(function( event ) {\r\n");
      out.write("                    \r\n");
      out.write("                    var divId = $('#divId');\r\n");
      out.write("                    var divPassword = $('#divPassword');\r\n");
      out.write("                    var divPasswordCheck = $('#divPasswordCheck');\r\n");
      out.write("                    var divName = $('#divName');\r\n");
      out.write("                    var divEmail = $('#divEmail');\r\n");
      out.write("                    \r\n");
      out.write("                    var regExp = /^[a-z][a-zA-Z\\d]{5,11}/;\r\n");
      out.write("                    \r\n");
      out.write("                    //아이디 검사\r\n");
      out.write("                    if($('#id').val()==\"\"){\r\n");
      out.write("                        modalContents.text(\"아이디를 입력하여 주시기 바랍니다.\");\r\n");
      out.write("                        modal.modal('show');\r\n");
      out.write("                        \r\n");
      out.write("                        divId.removeClass(\"has-success\");\r\n");
      out.write("                        divId.addClass(\"has-error\");\r\n");
      out.write("                        $('#id').focus();\r\n");
      out.write("                        return false;\r\n");
      out.write("                    }else if($(!regExp.test($(\"#id\").val()))){\r\n");
      out.write("                    \tmodalContents.text(\"유효한 아이디를 입력해주세요.\");\r\n");
      out.write("\t\t\t\t\t\tmodal.modal('show');\r\n");
      out.write("                        \r\n");
      out.write("                        divId.removeClass(\"has-success\");\r\n");
      out.write("                        divId.addClass(\"has-error\");\r\n");
      out.write("                    \treturn false;\r\n");
      out.write("                    }\r\n");
      out.write("  \t\t\r\n");
      out.write("                    else{\r\n");
      out.write("                        divId.removeClass(\"has-error\");\r\n");
      out.write("                        divId.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("                    \r\n");
      out.write("                    //패스워드 검사\r\n");
      out.write("                    if($('#password').val()==\"\"){\r\n");
      out.write("                        modalContents.text(\"패스워드를 입력하여 주시기 바랍니다.\");\r\n");
      out.write("                        modal.modal('show');\r\n");
      out.write("                        \r\n");
      out.write("                        divPassword.removeClass(\"has-success\");\r\n");
      out.write("                        divPassword.addClass(\"has-error\");\r\n");
      out.write("                        $('#password').focus();\r\n");
      out.write("                        return false;\r\n");
      out.write("                    }else{\r\n");
      out.write("                        divPassword.removeClass(\"has-error\");\r\n");
      out.write("                        divPassword.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("                    \r\n");
      out.write("                    //패스워드 확인\r\n");
      out.write("                    if($('#passwordCheck').val()==\"\"){\r\n");
      out.write("                        modalContents.text(\"패스워드 확인을 입력하여 주시기 바랍니다.\");\r\n");
      out.write("                        modal.modal('show');\r\n");
      out.write("                        \r\n");
      out.write("                        divPasswordCheck.removeClass(\"has-success\");\r\n");
      out.write("                        divPasswordCheck.addClass(\"has-error\");\r\n");
      out.write("                        $('#passwordCheck').focus();\r\n");
      out.write("                        return false;\r\n");
      out.write("                    }else{\r\n");
      out.write("                        divPasswordCheck.removeClass(\"has-error\");\r\n");
      out.write("                        divPasswordCheck.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("                    \r\n");
      out.write("                    //패스워드 비교\r\n");
      out.write("                    if($('#password').val()!=$('#passwordCheck').val() || $('#passwordCheck').val()==\"\"){\r\n");
      out.write("                        modalContents.text(\"패스워드가 일치하지 않습니다.\");\r\n");
      out.write("                        modal.modal('show');\r\n");
      out.write("                        \r\n");
      out.write("                        divPasswordCheck.removeClass(\"has-success\");\r\n");
      out.write("                        divPasswordCheck.addClass(\"has-error\");\r\n");
      out.write("                        $('#passwordCheck').focus();\r\n");
      out.write("                        return false;\r\n");
      out.write("                    }else{\r\n");
      out.write("                        divPasswordCheck.removeClass(\"has-error\");\r\n");
      out.write("                        divPasswordCheck.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("                    \r\n");
      out.write("                    //이름\r\n");
      out.write("                    if($('#name').val()==\"\"){\r\n");
      out.write("                        modalContents.text(\"이름을 입력하여 주시기 바랍니다.\");\r\n");
      out.write("                        modal.modal('show');\r\n");
      out.write("                        \r\n");
      out.write("                        divName.removeClass(\"has-success\");\r\n");
      out.write("                        divName.addClass(\"has-error\");\r\n");
      out.write("                        $('#name').focus();\r\n");
      out.write("                        return false;\r\n");
      out.write("                    }else{\r\n");
      out.write("                        divName.removeClass(\"has-error\");\r\n");
      out.write("                        divName.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("                    \r\n");
      out.write("                    //이메일\r\n");
      out.write("                    if($('#email').val()==\"\"){\r\n");
      out.write("                        modalContents.text(\"이메일을 입력하여 주시기 바랍니다.\");\r\n");
      out.write("                        modal.modal('show');\r\n");
      out.write("                        \r\n");
      out.write("                        divEmail.removeClass(\"has-success\");\r\n");
      out.write("                        divEmail.addClass(\"has-error\");\r\n");
      out.write("                        $('#email').focus();\r\n");
      out.write("                        return false;\r\n");
      out.write("                    }else{\r\n");
      out.write("                        divEmail.removeClass(\"has-error\");\r\n");
      out.write("                        divEmail.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("\r\n");
      out.write("                    // 보안답변\r\n");
      out.write("                    if($('#answer').val()==\"\"){\r\n");
      out.write("                        modalContents.text(\"보안답변을 입력하여 주시기 바랍니다.\");\r\n");
      out.write("                        modal.modal('show');\r\n");
      out.write("                        \r\n");
      out.write("                        divName.removeClass(\"has-success\");\r\n");
      out.write("                        divName.addClass(\"has-error\");\r\n");
      out.write("                        $('#answer').focus();\r\n");
      out.write("                        return false;\r\n");
      out.write("                    }else{\r\n");
      out.write("                        divName.removeClass(\"has-error\");\r\n");
      out.write("                        divName.addClass(\"has-success\");\r\n");
      out.write("                    }\r\n");
      out.write("                });\r\n");
      out.write("\r\n");
      out.write("            });\r\n");
      out.write("            // 비밀번호 일치 여부 검사\r\n");
      out.write("    \t\t$(\"#passwordCheck\").on(\"input\",function() {\r\n");
      out.write("    \t\t\tif($(\"#password\").val().trim()!= $(\"#passwordCheck\").val().trim()){\r\n");
      out.write("    \t\t\t\t$(\"#checkPwd\").text(\"비밀번호가 일치하지 않습니다.\").css(\"color\",\"red\");\r\n");
      out.write("\r\n");
      out.write("    \t\t\t}else{\r\n");
      out.write("    \t\t\t\t$(\"#checkPwd\").text(\"비밀번호가 일치합니다.\").css(\"color\",\"green\");\r\n");
      out.write("    \t\t\t\t}\r\n");
      out.write("    \t  \t\t});\r\n");
      out.write("            \r\n");
      out.write("\r\n");
      out.write("            // id를 입력하는 경우 발생하는 이벤트\r\n");
      out.write("            var $id = $(\"#id\");\r\n");
      out.write("            \r\n");
      out.write("         \t$(\"#id\").on(\"input\", function(){\r\n");
      out.write("             // ajax를 이용한 아이디 유효성 검사\r\n");
      out.write("             var regExp = /^[a-z][a-zA-Z\\d]{5,11}/;\r\n");
      out.write("             if(!regExp.test($id.val())){\r\n");
      out.write("            \t \r\n");
      out.write("                 $(\"#checkId\").text(\"유효하지 않은 아이디 형식입니다.\").css(\"color\", \"red\");\r\n");
      out.write("\r\n");
      out.write("                 if($id.val().length == 0)\t$(\"#checkId\").text(\"\");\r\n");
      out.write("\r\n");
      out.write("             }else{ // 유효한 아이디 형식일 때\r\n");
      out.write("                 $.ajax({\r\n");
      out.write("                     url : \"idDupCheck.do\",\r\n");
      out.write("                     data: {\"id\" : $id.val()},\r\n");
      out.write("                     type: \"get\",\r\n");
      out.write("\r\n");
      out.write("                     success : function(result){\r\n");
      out.write("                         if(result ==0) {\r\n");
      out.write("                             $(\"#checkId\").text(\"사용 가능한 아이디입니다.\").css(\"color\", \"green\");\r\n");
      out.write("                         }else{\r\n");
      out.write("                             $(\"#checkId\").text(\"이미 사용중인 아이디입니다.\").css(\"color\", \"red\");\r\n");
      out.write("                         }\r\n");
      out.write("                     }\r\n");
      out.write("                     ,error : function() {\r\n");
      out.write("                         console.log(\"ajax 통신 실패\");\r\n");
      out.write("                     }\r\n");
      out.write("                 });\r\n");
      out.write("             }\r\n");
      out.write("         });\r\n");
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("        </script>\r\n");
      out.write("            <hr/>\r\n");
      out.write("            <!-- 푸터 들어가는 부분 -->\r\n");
      out.write("            ");
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
      out.write("            <!--// 푸터 끝나는 부분 -->\r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
      out.write(" ");
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
