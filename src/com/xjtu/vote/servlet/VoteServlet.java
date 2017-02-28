package com.xjtu.vote.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjtu.vote.domin.Content;
import com.xjtu.vote.domin.User;
import com.xjtu.vote.domin.Vote;
import com.xjtu.vote.service.VoteService;

/**
 * 投票
 */
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    VoteService voteService = new VoteService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("text/html;charset=UTF-8"); 
		String method = request.getParameter("method");
	  if(method!=null){
		  if("findAllVote".equals(method)){
			  this.findAllVote(request,response);
			  
		  }else if("findVoteById".equals(method)){
			  this.findVoteById(request,response);
			  
		  }else if("toLoginJsp".equals(method)){
			  request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			  
		  }else if("exit".equals(method)){
			  this.exit(request,response);
			  
		  }
	  }
	}

	
	private void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/welcome.jsp");
	}


	private void findVoteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
	  try {
		Content content = voteService.findContentById(Integer.parseInt(id));
		request.setAttribute("content",content);
		request.getRequestDispatcher("/WEB-INF/listContent.jsp").forward(request, response);
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		request.setAttribute("message", "查询候选人详细信息失败");
		request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
	}
	}


	private void findAllVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<Vote> voteList = voteService.findAllVote();
			request.setAttribute("voteList",voteList);
			request.getRequestDispatcher("/WEB-INF/listAllVote.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "查询所有候选人信息失败");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String method = request.getParameter("method");
		  if(method!=null){
			  if("login".equals(method)){
				  this.login(request,response);
			  }
			
		  }
	}


	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		
		String username = request.getParameter("username");
		if(username!=null && username.trim().length()>0){
			User user = new User();
			user.setUsername(username);  
		request.getSession().setAttribute("user", user);
	    response.sendRedirect(request.getContextPath()+"/welcome.jsp");
		}	
	}
    
}
