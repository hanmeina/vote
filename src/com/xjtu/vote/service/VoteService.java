package com.xjtu.vote.service;

import java.sql.SQLException;
import java.util.List;

import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper;
import com.xjtu.vote.dao.ContentDao;
import com.xjtu.vote.dao.VoteDao;
import com.xjtu.vote.domin.Content;
import com.xjtu.vote.domin.Vote;

public class VoteService {
	
	VoteDao voteDao = new  VoteDao();
	ContentDao contentDao = new ContentDao();
	
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
