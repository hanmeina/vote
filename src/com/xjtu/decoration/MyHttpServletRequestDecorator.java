package com.xjtu.decoration;
//对request对象进行包装

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHttpServletRequestDecorator extends HttpServletRequestWrapper {
	private HttpServletRequest  request ;
   public MyHttpServletRequestDecorator(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		this.request = request;
	}
  public String getParameter(String name){
	  String method = request.getMethod();
	  String value  =  null;
	  if(method!=null){
	  if("GET".equalsIgnoreCase(method)){
          	value = request.getParameter(name);
          System.out.println("value:"+value);
         
	  }else if("POST".equalsIgnoreCase(method)){
		  
		  try {
			request.setCharacterEncoding("UTF-8");
			 value = request.getParameter(name);
		
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  }
	value =   filter(value);
	return value;
  };
  
//转义方法
	public String filter(String message) {
        if (message == null)
            return (null);
        char content[] = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
            case '<':
                result.append("&lt;");
                break;
            case '>':
                result.append("&gt;");
                break;
            case '&':
                result.append("&amp;");
                break;
            case '"':
                result.append("&quot;");
                break;
            default:
                result.append(content[i]);
            }
        }
        return (result.toString());
    }
   
}
