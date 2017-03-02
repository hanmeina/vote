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

import com.xjtu.decoration.MyResponse;

/**
 * Servlet Filter implementation class TransferFilter
 */
public class TransferFilter implements Filter {

    public TransferFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		MyResponse myResponse = new MyResponse(response);
		response.setContentType("text/html;charset=UTF-8");
		chain.doFilter(request, myResponse);
		byte[] data = myResponse.getBuffer();
		String message = new String(data,"UTF-8");
		if(message.contains("中国")){
			message = message.replaceAll("中国","<font size='44'><a href='#' style='text-decoration:none'>中国</a></font>");
		}
		response.getWriter().write(message);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
