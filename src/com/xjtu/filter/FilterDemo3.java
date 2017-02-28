package com.xjtu.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//µ¥Àý
public class FilterDemo3 implements Filter {
	private FilterConfig filterConfig;
	public FilterDemo3(){
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	//WebÈÝÆ÷µ÷ÓÃ
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		Enumeration<String> enums = filterConfig.getInitParameterNames();
		while(enums.hasMoreElements()){
			String key = enums.nextElement();
			String value = filterConfig.getInitParameter(key);
			System.out.println(key+":"+value);
		}
		String encoding = filterConfig.getInitParameter("encoding");
		response.setContentType("text/html;charset="+encoding);
		chain.doFilter(request,response);
	}
	public void destroy() {
	}
}
