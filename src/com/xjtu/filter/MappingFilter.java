package com.xjtu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MappingFilter implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		System.out.println("MappingFilter::doFilter():A");
		chain.doFilter(request,response);
		System.out.println("MappingFilter::doFilter():B");
		System.out.println("模拟正在写日志Log4J");
	}
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
