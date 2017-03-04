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
 * ͶƱ
 */
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    VoteService voteService = new VoteService();
    //�������
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
	 * ��ѯ����ͶƱ�˵���Ϣ
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
			request.setAttribute("message","��ѯ����ͶƱ�˵���Ϣʧ��");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request,response);
		}
	}	
	/**
	 * ͨ��id���º�ѡ����Ϣ
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
			request.setAttribute("message","<font color='red' size='44'>1����֮�ڣ���������ͶƱ</font>");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request,response);
		} 
		catch(OverTicketException e){
			//e.printStackTrace();
			request.setAttribute("message","<font color='red' size='44'>ͶƱ�����ܳ�����100Ʊ</font>");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request,response);
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "ͶƱʧ��");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
		}
	}

   /**
    * �˳�
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
     * ͨ��id��ѯ��ѡ��
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
		request.setAttribute("message", "��ѯ��ѡ����ϸ��Ϣʧ��");
		request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
	}
	}

    /**
     * ��ѯ���к�ѡ����Ϣ
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	private void findAllVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//����Ȼ˳���ѯ��ѡ����Ϣ
			List<Vote> voteList = voteService.findAllVote();
			//�����ŶȲ�ѯ��ѡ����Ϣ
			List<Vote> voteListDesc = voteService.findAllVoteByDesc();
			request.setAttribute("voteList",voteList);
			request.setAttribute("voteListDesc",voteListDesc);
			request.getRequestDispatcher("/WEB-INF/listAllVote.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "��ѯ���к�ѡ����Ϣʧ��");
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
      * ����Ա��½
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
			//����Աָ����ȷ
			if(admin!=null){
				request.getRequestDispatcher("/WEB-INF/backMain.jsp").forward(request,response);
			}else{
				request.setAttribute("message","����Աָ�����");
				request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message","����Աָ�����");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request,response);
		}
	}

	/**
     * ��¼
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
			//�жθ��û��Ƿ�������
	        boolean flag =	voteService.checkOnline(username,usernameList);
			if(!flag){
				//������
				User user = new User();
				user.setUsername(username);  
		     	request.getSession().setAttribute("user", user);
		        response.sendRedirect(request.getContextPath()+"/welcome.jsp");
				
			}else{
				//����
				request.setAttribute("message", "���û�������");
				request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
				
			}
			
		}	
	}
    
}
