package com.xjtu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//对敏感页面或目录进行认证
public class AutoLoginFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//取得浏览器的Cookie
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		if(cookies!=null){
			for(Cookie c : cookies){
				if(c.getName().equals("usernameAndPassword")){
					userCookie = c;
					break;
				}
			}
			//找到对应的Cookie
			if(userCookie!=null){
				String usernameAndPassword = userCookie.getValue();
				String[] both = usernameAndPassword.split("_");
				String username = both[0];
				String password = both[1];
				if(username.equals("jack") && password.equals("123")){
					request.getSession().setAttribute("username",username);
				}
			}
		}
		//发行资源
		chain.doFilter(request,response);
	}	
		
	public void destroy() {
	}
}
