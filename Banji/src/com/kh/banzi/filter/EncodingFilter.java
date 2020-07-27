package com.kh.banzi.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// @WebFilter :  어떤 요청에 대해 필터가 적용될지 작성하는 언어
// -filterName : 필터의 이름 지정(필터가 동작할 순서를 지정할 때 사용)
// -rulPatterns : 필트가 적용될 요청 주소 지정(/* : 현재 프로젝트 모든 url)


@WebFilter(filterName = "encoding", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    request.setCharacterEncoding("UTF-8");
	    
	   //response.setContentType("text/html; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    
		chain.doFilter(request, response);
	}
    @Override
	public void destroy() {
	}

}
