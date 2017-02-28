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

//������Ŀ¼������֤
public class FilterDemo6 implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//ȡ���û��������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//�ж�
		if(username!=null && password!=null){
			if(username.equals("jack") && password.equals("123")){
				//�������������Դ
				chain.doFilter(request,response);
				
			}else{
				//ת����message.jspҳ��
				request.setAttribute("message","�û��������벻��ȷ");
				request.getRequestDispatcher("/message.jsp").forward(request,response);
			}
		}else{
			//ת����message.jspҳ��
			request.setAttribute("message","���������û���������");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
		}
	}
	public void destroy() {
	}
}
