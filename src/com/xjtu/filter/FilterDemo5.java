package com.xjtu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//禁止浏览器缓存动态资源，例如JSP资源
public class FilterDemo5 implements Filter {
	private FilterConfig filterConfig;
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {

		//NO将父子接口强转
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//NO1取得客户端访问的资源的URI，形式/day19/login.jsp
		String uri = request.getRequestURI();
		
		//NO2判段是否以jsp结尾，即动态资源
		if(uri!=null && uri.endsWith("jsp")){
			//NO3如果是动态资源，设置三个响应头通知浏览器不缓存
			response.setHeader("expires","-1");
			response.setHeader("cache-control","no-cache");
			response.setHeader("pragma","no-cache");
		}else if(uri!=null && uri.endsWith("html")){
			//NO4如果是静态资源，缓存一定的时间
			String strHtml = filterConfig.getInitParameter("html");
			long time = System.currentTimeMillis()+Integer.parseInt(strHtml)*1000;
			//time为毫秒值
			response.setDateHeader("expires",time);
			response.setHeader("cache-control",time/1000+"");
			response.setHeader("pragma",time/1000+"");
		}
		
		//NO5放行资源
		chain.doFilter(request,response);
	}
	public void destroy() {
	}
}
