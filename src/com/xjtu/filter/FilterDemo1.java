package com.xjtu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//自定义Filter过渡器
public class FilterDemo1 implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		//放行请求[类似于函数调用]
		chain.doFilter(request,response);
	}
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
