package com.xjtu.vote.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjtu.vote.domin.Admin;
import com.xjtu.vote.domin.Content;
import com.xjtu.vote.domin.Info;
import com.xjtu.vote.domin.User;
import com.xjtu.vote.domin.Vote;
import com.xjtu.vote.exception.NoVoteException;
import com.xjtu.vote.exception.OverTicketException;
import com.xjtu.vote.service.VoteService;

import sun.security.x509.IPAddressName;

/**
 * 投票
 */
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    VoteService voteService = new VoteService();
    //共享的锁
  	private Object lock = new Object();
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
			  
		  }else if("updateVoteById".equals(method)){
			  this.updateVoteById(request,response);
		  }else if("findAllInfo".equals(method)){
			  this.findAllInfo(request, response);
			  
		  }else if ("tobackLogin".equals(method)) {
			  
			this.tobackLogin(request,response);
		}
	  }
	}
	
	private void tobackLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/backLogin.jsp").forward(request, response);
	}

	/**
	 * 查询所有投票人的信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void findAllInfo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		VoteService voteService = new VoteService();
		try {
			List<Info> infoList = voteService.findAllInfo();
			request.setAttribute("infoList",infoList);
			request.getRequestDispatcher("/WEB-INF/listAllInfo.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message","查询所有投票人的信息失败");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request,response);
		}
	}	
	/**
	 * 通过id更新候选人信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateVoteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id  = request.getParameter("id");
		String  ip = request.getRemoteAddr() ;
		//System.out.println(ip);
		try {
			synchronized (lock) {
				voteService.updateVoteById(Integer.parseInt(id), ip);
			}
			response.sendRedirect(request.getContextPath()+"/welcome.jsp");

		}catch(NoVoteException e){
			
			//e.printStackTrace();
			request.setAttribute("message","<font color='red' size='44'>1分钟之内，不允许再投票</font>");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request,response);
		} 
		catch(OverTicketException e){
			//e.printStackTrace();
			request.setAttribute("message","<font color='red' size='44'>投票数不能超过程100票</font>");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request,response);
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "投票失败");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
		}
	}

   /**
    * 退出
    * @param request
    * @param response
    * @throws IOException
    */
	private void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/welcome.jsp");
	}

    /**
     * 通过id查询候选人
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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

    /**
     * 查询所有候选人信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	private void findAllVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//按自然顺序查询候选人信息
			List<Vote> voteList = voteService.findAllVote();
			//按热门度查询候选人信息
			List<Vote> voteListDesc = voteService.findAllVoteByDesc();
			request.setAttribute("voteList",voteList);
			request.setAttribute("voteListDesc",voteListDesc);
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
			  }else if("backLogin".equals(method)){
				  this.backLogin(request,response);
				  
			  }
			
		  }
	}
     /**
      * 管理员登陆
      * @param request
      * @param response
     * @throws IOException 
     * @throws ServletException 
      */
    private void backLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			VoteService voteService = new VoteService();
			Admin admin = voteService.findAdminByUsernameAndPassword(username,password);
			//管理员指令正确
			if(admin!=null){
				request.getRequestDispatcher("/WEB-INF/backMain.jsp").forward(request,response);
			}else{
				request.setAttribute("message","管理员指令错误");
				request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message","管理员指令错误");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request,response);
		}
	}

	/**
     * 登录
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
			
		String username = request.getParameter("username");
		if(username!=null && username.trim().length()>0){		
          ServletContext  context = this.getServletContext();
		    List<String> usernameList = (List<String>)context.getAttribute("usernameList");
			if(usernameList == null){
				usernameList = new ArrayList<>();
				context.setAttribute("usernameList",usernameList );
				
			}
			//判段该用户是否已在线
	        boolean flag =	voteService.checkOnline(username,usernameList);
			if(!flag){
				//不在线
				User user = new User();
				user.setUsername(username);  
		     	request.getSession().setAttribute("user", user);
		        response.sendRedirect(request.getContextPath()+"/welcome.jsp");
				
			}else{
				//在线
				request.setAttribute("message", "该用户已在线");
				request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
				
			}
			
		}	
	}
    
}
