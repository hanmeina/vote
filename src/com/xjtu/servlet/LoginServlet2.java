package com.xjtu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet2 extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.getWriter().write("”√ªß√˚£∫" + username);
		response.getWriter().write("√‹¬Î£∫" + password);
	}
}
