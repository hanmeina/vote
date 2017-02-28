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

//������Ŀ¼������֤[������ϰ1]
public class FilterDemo7 implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//������������뷽ʽ
		request.setCharacterEncoding("UTF-8");
		
		//ȡ���û��������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		//�ж�
		if(username!=null && password!=null && role!=null && username.trim().length()>0 && password.trim().length()>0 && role.trim().length()>0){
			if("��ͨ�û�".equals(role)){
				request.setAttribute("message","��ӭ��ͨ�û�<font color='blue'>"+username+"</font>��¼");
				request.setAttribute("flag","user");
			}else if("����Ա".equals(role)){
				request.setAttribute("message","��ӭ����Ա<font color='red'>"+username+"</font>��¼");	
				request.setAttribute("flag","admin");
			}
			chain.doFilter(request,response);
		}
	}
	public void destroy() {
	}
}
