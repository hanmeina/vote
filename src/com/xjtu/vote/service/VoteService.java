package com.xjtu.vote.service;

import java.sql.SQLException;
import java.util.List;

import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper;
import com.xjtu.vote.dao.ContentDao;
import com.xjtu.vote.dao.InfoDao;
import com.xjtu.vote.dao.VoteDao;
import com.xjtu.vote.domin.Content;
import com.xjtu.vote.domin.Info;
import com.xjtu.vote.domin.Vote;
import com.xjtu.vote.exception.NoVoteException;
import com.xjtu.vote.exception.OverTicketException;

public class VoteService {
	
	VoteDao voteDao = new  VoteDao();
	ContentDao contentDao = new ContentDao();
	InfoDao  infoDao = new InfoDao();

	
	/**
	 * 查询所有投票人的信息
	 * @return
	 * @throws Exception 
	 * @throws SQLException
	 */
		public List<Info> findAllInfo() throws Exception{
			
		try {
			return 	infoDao.findAllInfo();
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
   * 查询候选人的具体信息
   * @param id
   * @return
   * @throws Exception
   */
  public Content findContentById(int id) throws Exception{
	  try {
		Content  content =contentDao.findContentById(id);
		Vote vote = voteDao.findVoteById(id);
		content.setVote(vote);
		return content;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new Exception();
	}
	  
	  
  }
}
