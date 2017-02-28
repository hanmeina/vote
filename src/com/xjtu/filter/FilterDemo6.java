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

//对敏感目录进行认证
public class FilterDemo6 implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//取得用户请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//判段
		if(username!=null && password!=null){
			if(username.equals("jack") && password.equals("123")){
				//允许进入敏感资源
				chain.doFilter(request,response);
				
			}else{
				//转发到message.jsp页面
				request.setAttribute("message","用户名或密码不正确");
				request.getRequestDispatcher("/message.jsp").forward(request,response);
			}
		}else{
			//转发到message.jsp页面
			request.setAttribute("message","必须填入用户名和密码");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
		}
	}
	public void destroy() {
	}
}
