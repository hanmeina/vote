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
 * 将数据缓存到内存,单例
 */
public class CacheFilter implements Filter {
	//实例变量[每个线程共享]
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
		//NO1：取得客户端访问的资源路径
		String uri = request.getRequestURI();

		//NO2：根据uri去缓存中查询对应的页面资源有没有
		byte[] data = cache.get(uri);
		//NO3：如果没有
		if(data==null){
			//将请求和响应放行到web资源中	
			MyResponse myResponse = new MyResponse(response);
			chain.doFilter(request, myResponse);
			//NO4:将刚取得的数据放入到缓存中，便于下次重用
			data = myResponse.getBuffer();
			cache.put(uri, data);
			System.out.println("从服务端取数据");
		
		}//NO5：如果有，直接从缓存中了得数据
		//NO6：向浏览器输出数据
		response.getOutputStream().write(data);
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
