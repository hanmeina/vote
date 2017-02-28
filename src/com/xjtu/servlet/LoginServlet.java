package com.xjtu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//用户自动登录
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username!=null && password!=null && username.trim().length()>0 && password.trim().length()>0){
			if(username.equals("jack") && password.equals("123")){
				//登录成功，将信息绑定到HttpSession域对象中
				request.getSession().setAttribute("username",username);
				request.getSession().setAttribute("password",password);
				//向浏览器写入Cookie
				Cookie cookie = new Cookie("usernameAndPassword",username+"_"+password);
				int time = Integer.parseInt(request.getParameter("time"));
				cookie.setMaxAge(time);
				response.addCookie(cookie);
				//重定向到welcome.jsp页面
				response.sendRedirect(request.getContextPath()+"/welcome.jsp");
			}
		}
	}
}



