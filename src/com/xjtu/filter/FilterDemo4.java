package com.xjtu.filter;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//针对POST请求和响应的编码方式处理
public class FilterDemo4 implements Filter {
	private FilterConfig filterConfig;
	public FilterDemo4(){
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	//Web容器调用
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		String encoding = filterConfig.getInitParameter("encoding");
		//POST请求编码设置
		request.setCharacterEncoding(encoding);
		//响应编码设置
		response.setContentType("text/html;charset="+encoding);
		chain.doFilter(request,response);
	}
	public void destroy() {
	}
}
