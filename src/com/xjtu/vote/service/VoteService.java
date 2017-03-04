package com.xjtu.vote.service;

import java.sql.SQLException;

import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper;
import com.xjtu.vote.dao.AddressDao;
import com.xjtu.vote.dao.AdminDao;
import com.xjtu.vote.dao.ContentDao;
import com.xjtu.vote.dao.ImageDao;
import com.xjtu.vote.dao.InfoDao;
import com.xjtu.vote.dao.VoteDao;
import com.xjtu.vote.domin.Address;
import com.xjtu.vote.domin.Admin;
import com.xjtu.vote.domin.Content;
import com.xjtu.vote.domin.Image;
import com.xjtu.vote.domin.Info;
import com.xjtu.vote.domin.Vote;
import com.xjtu.vote.exception.NoVoteException;
import com.xjtu.vote.exception.OverTicketException;

public class VoteService {
	
	private VoteDao voteDao = new  VoteDao();
	private ContentDao contentDao = new ContentDao();
	private InfoDao  infoDao = new InfoDao();
    private ImageDao imageDao = new ImageDao();
	private AddressDao addressDao = new AddressDao();
    private AdminDao adminDao = new AdminDao();
    /**
     * 根据用户名和密码验证管理员信息
     * @param username
     * @param password
     * @return
     * @throws Exception 
     * @throws SQLException
     */
	public Admin findAdminByUsernameAndPassword(String username,String password) throws Exception{
		try {
			return adminDao.findAdminByUsernameAndPassword(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}
    
    
    
    
	/**
	 * 查询所有投票人的信息
	 * @return
	 * @throws Exception 
	 * @throws SQLException
	 */
		public List<Info> findAllInfo() throws Exception{
			
		try {
			List<Info> infoList = 	infoDao.findAllInfo();
			for(Info info:infoList){
				Address address  = addressDao.findAddressByIp(info.getIp());
			    info.setAddress(address);	
			}			
			return 	infoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
		}
	/**
	 * 通过id更新票数[投票功能]
	 * @param id
	 * @param ip
	 * @throws Exception 
	 * @throws SQLException
	 */
	public void updateVoteById(int id,String ip) throws Exception{
		try {
			Info info = infoDao.findInfoByIp(ip);
			Vote vote = voteDao.findVoteById(id);
			if(vote.getTicket() < 100){
			//第一次投票
			if(info == null){
				  //投票
				   voteDao.updateVoteById(id);
				    info = new Info();
				   info.setIp(ip);		   
				   infoDao.addInfo(info);
				
			}else{
				//第N次投票
				// 取得最后投票时间（单位毫秒）
	            
			    Long  endvotetime  = info.getVotetime().getTime();
			   Long  middle = (System.currentTimeMillis()-endvotetime)/1000;
			   // 如果大于XX秒钟
			  if(middle>60){
				voteDao.updateVoteById(id);			 
			   info.setIp(ip);		   
			   infoDao.addInfo(info);
				
			}else{
				throw new NoVoteException();
				
			}
				
				
			}
			}else{
				throw new  OverTicketException();
			}
			
		  
		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}
	/**
	 * 查询所有候选人的记录
	 * @return
	 * @throws Exception
	 */
  public List<Vote> findAllVote() throws Exception{
	  try {
		  
		return voteDao.findAllVote();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new Exception();
	}
	  
  }
  /**
 	 * 查询所有候选人的记录[热门候选人]
 	 * @return
     * @throws Exception 
 	 * @throws SQLException
 	 */
   public List<Vote> findAllVoteByDesc() throws Exception{
	   try {
		return voteDao.findAllVoteByDesc();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new Exception();
	}
   }
  
  /**
   * 查询候选人的具体信息
   * @param id
   * @return
   * @throws Exception
   */
  public Content findContentById(int id) throws Exception{
	  try {
		Content  content =contentDao.findContentById(id);
		Vote vote = voteDao.findVoteById(id);
		Image image = imageDao.findImageById(id);
		
		content.setVote(vote);
		content.setImage(image);
		return content;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new Exception();
	}
	  
	  
  }
   /**
    * 判断用户是否二次登陆
    * @param username
    * @param usernameList
    * @return true 在线
    * false  不在线
    */
   public boolean checkOnline(String username,List<String> usernameList){
	  boolean flag = true;
	  if(usernameList.size()==0){
		//该用户未在线 
		  flag = false; 
		  usernameList.add(username);
	  }else{
		   //迭代查询用户名
			for(String un : usernameList){
				if(un.equals(username)){
					//该用户在线
					flag = true;
					return flag;
				}				
			}
			//该用户不在线
			flag = false; 		  
			//将该用户名加入到集合中
			usernameList.add(username);
	  }
	  
	  
	  return flag;
	   
   }
}
