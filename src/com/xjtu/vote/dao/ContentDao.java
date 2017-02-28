package com.xjtu.vote.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xjtu.vote.domin.Content;

import com.xjtu.vote.util.JdbcUtil;

public class ContentDao {
	/**
	    * 通过id查询候选人的详细信息
	    * @param id
	    * @return
	    * @throws SQLException
	    */
	   public Content findContentById(int id) throws SQLException{
		   Content content = new Content();
		   QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
		    String sql = "select * from content where id=?";
		    content = (Content) queryRunner.query(sql, id, new BeanHandler(Content.class));
		   return content;
		   
		   
	   }
}
