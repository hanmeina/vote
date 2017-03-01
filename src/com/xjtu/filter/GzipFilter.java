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
		
		//取得缓存中的内容
		byte[] data = myResponse.getBuffer();
		System.out.println("压缩前：" + data.length);
		
		//进行压缩
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		gout.write(data);
		gout.flush();
		gout.close();
		data = bout.toByteArray();
		System.out.println("压缩后：" + data.length);
		
		//通知浏览器接收是的一个压缩型数据库和长度
		response.setHeader("content-encoding","gzip");
		response.setHeader("content-length",data.length+"");
		
		//以字节方式真正输出到浏览器
		response.getOutputStream().write(data);
		
		//无限压缩，无出口，类似环无限循环
		//myResponse.getOutputStream().write(data);
	}
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
