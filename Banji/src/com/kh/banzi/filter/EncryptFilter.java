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

import com.kh.banzi.wrapper.EncryptWrapper;

							// form action이 일어날 때
@WebFilter(urlPatterns = {"/user/signUp.do", "/userLogin/login.do", "/myPage/changePwd.do", "/myPage/secession.do"})
public class EncryptFilter implements Filter {


    public EncryptFilter() {

    }


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 매개변수로 전달받은 ServletRequest를 암호화를 위해 HttpServletRequest로 형변환(다운 캐스팅)
		HttpServletRequest hRequest = (HttpServletRequest)request;
		
		// 암호화 래퍼 객체 생성
		EncryptWrapper encWrapper = new EncryptWrapper(hRequest);
		
		// 암호화로 포장된 객체를 다음 필터 또는 servlet/jsp로 전달
		
		chain.doFilter(encWrapper, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
		
	}

}
