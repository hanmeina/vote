package com.xjtu.vote.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xjtu.vote.domin.Info;
import com.xjtu.vote.domin.Vote;
import com.xjtu.vote.util.JdbcUtil;

public class VoteDao {
	
	
	
	/**
	 * 通过id更新票数
	 * @param id
	 * @throws SQLException
	 */
	public void updateVoteById(int id) throws SQLException{
		  QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
		  String sql = "update vote set ticket = ticket+1 where id = ?";
		  queryRunner.update(sql, id);
		
	}
	/**
	 * 查询所有候选人的记录
	 * @return
	 * @throws SQLException
	 */
   public List<Vote> findAllVote() throws SQLException{
	   List<Vote> voteList = new ArrayList<>();
	    QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
	    String sql = "select * from vote";
	   voteList = (List<Vote>) queryRunner.query(sql, new BeanListHandler(Vote.class));
	   
	   return voteList;
   }
   /**
    * 通过id查询Vote
    * @param id
    * @return
    * @throws SQLException
    */
   public Vote findVoteById(int id) throws SQLException{
	   Vote vote = new Vote();
	   QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
	    String sql = "select * from vote where id=?";
	  vote = (Vote) queryRunner.query(sql, id, new BeanHandler(Vote.class));
	   return vote;
	   
	   
   }
}
