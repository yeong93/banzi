package com.kh.banzi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.banzi.user.model.vo.User;

@WebFilter(urlPatterns= {"/myPage/changeUserForm.do", "/myPage/changePwdForm.do", "/myPage/secessionForm.do"})
public class LoginFilter implements Filter {

    public LoginFilter() { }
	public void init(FilterConfig fConfig) throws ServletException {

	} 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		
		 User user = (User)session.getAttribute("loginUser");
		if(user == null) { 
			session.setAttribute("status", "error");
			session.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			session.setAttribute("text", "로그인 후 이용바랍니다.");
			res.sendRedirect(req.getContextPath()); 
		}else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {}
}
