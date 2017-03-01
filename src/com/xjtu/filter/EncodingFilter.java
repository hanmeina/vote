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

import com.xjtu.decoration.MyHttpServletRequestDecorator;

/**
 * Servlet Filter implementation class EncodingFilter
 */
public class EncodingFilter implements Filter {

    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest request = (HttpServletRequest)req;
	    HttpServletResponse response = (HttpServletResponse)resp;
	    MyHttpServletRequestDecorator myHttpServletRequest = new MyHttpServletRequestDecorator(request);
		System.out.println("doFilter()");
		response.setContentType("text/html;charset=UTF-8");
	    chain.doFilter(myHttpServletRequest, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
