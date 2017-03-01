package com.xjtu.filter;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjtu.decoration.MyResponse;



public class GzipFilter implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		MyResponse myResponse = new MyResponse(response);
		
		chain.doFilter(request,myResponse);
		
		//ȡ�û����е�����
		byte[] data = myResponse.getBuffer();
		System.out.println("ѹ��ǰ��" + data.length);
		
		//����ѹ��
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		gout.write(data);
		gout.flush();
		gout.close();
		data = bout.toByteArray();
		System.out.println("ѹ����" + data.length);
		
		//֪ͨ����������ǵ�һ��ѹ�������ݿ�ͳ���
		response.setHeader("content-encoding","gzip");
		response.setHeader("content-length",data.length+"");
		
		//���ֽڷ�ʽ��������������
		response.getOutputStream().write(data);
		
		//����ѹ�����޳��ڣ����ƻ�����ѭ��
		//myResponse.getOutputStream().write(data);
	}
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
