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
	    //�ַ���ʽ
		PrintWriter pw = response.getWriter();
		pw.write("������������������������������������������������������������<br/>");
		pw.write("�й��й��й��й��й��й��й��й��й��й��й��й��й��й��й�<br/>");
		pw.write("������������������������������������������������������������<br/>");
		//�ֽڷ�ʽ
		//ServletOutputStream sout = response.getOutputStream();
		//sout.write(data.getBytes("UTF-8"));
		//MyServletOutputStream mySout = myResponse.getOutputStream();
		//sout.write("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA".getBytes());
		//mySout.write("AAAAAA".getBytes[]);
	}
}





