package com.xjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	    //字符方式
		PrintWriter pw = response.getWriter();
		pw.write("美国美国美国美国美国美国美国美国美国美国美国美国美国美国美国<br/>");
		pw.write("中国中国中国中国中国中国中国中国中国中国中国中国中国中国中国<br/>");
		pw.write("法国法国法国法国法国法国法国法国法国法国法国法国法国法国法国<br/>");
		//字节方式
		//ServletOutputStream sout = response.getOutputStream();
		//sout.write(data.getBytes("UTF-8"));
		//MyServletOutputStream mySout = myResponse.getOutputStream();
		//sout.write("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA".getBytes());
		//mySout.write("AAAAAA".getBytes[]);
	}
}





