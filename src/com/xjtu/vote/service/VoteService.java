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
	 * ��ѯ����ͶƱ�˵���Ϣ
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
	 * ͨ��id����Ʊ��[ͶƱ����]
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
			//��һ��ͶƱ
			if(info == null){
				  //ͶƱ
				   voteDao.updateVoteById(id);
				    info = new Info();
				   info.setIp(ip);		   
				   infoDao.addInfo(info);
				
			}else{
				//��N��ͶƱ
				// ȡ�����ͶƱʱ�䣨��λ���룩
	            
			    Long  endvotetime  = info.getVotetime().getTime();
			   Long  middle = (System.currentTimeMillis()-endvotetime)/1000;
			   // �������XX����
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
	 * ��ѯ���к�ѡ�˵ļ�¼
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
   * ��ѯ��ѡ�˵ľ�����Ϣ
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
