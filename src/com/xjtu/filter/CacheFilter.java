package com.xjtu.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjtu.decoration.MyResponse;



/**
 * �����ݻ��浽�ڴ�,����
 */
public class CacheFilter implements Filter {
	//ʵ������[ÿ���̹߳���]
	Map<String,byte[]>  cache = new HashMap<>();
    /**
     * Default constructor. 
     */
    public CacheFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//NO1��ȡ�ÿͻ��˷��ʵ���Դ·��
		String uri = request.getRequestURI();

		//NO2������uriȥ�����в�ѯ��Ӧ��ҳ����Դ��û��
		byte[] data = cache.get(uri);
		//NO3�����û��
		if(data==null){
			//���������Ӧ���е�web��Դ��	
			MyResponse myResponse = new MyResponse(response);
			chain.doFilter(request, myResponse);
			//NO4:����ȡ�õ����ݷ��뵽�����У������´�����
			data = myResponse.getBuffer();
			cache.put(uri, data);
			System.out.println("�ӷ����ȡ����");
		
		}//NO5������У�ֱ�Ӵӻ������˵�����
		//NO6����������������
		response.getOutputStream().write(data);
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
