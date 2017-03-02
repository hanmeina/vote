package com.xjtu.vote.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xjtu.vote.domin.Info;
import com.xjtu.vote.util.JdbcUtil;

public class InfoDao {
    
	/**
	 * ��ѯ����ͶƱ�˵���Ϣ
	 * @return
	 * @throws SQLException
	 */
		public List<Info> findAllInfo() throws SQLException{
			List<Info> infoList = new ArrayList<Info>();
			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
			String sql = "select ip,max(votetime) as votetime from info group by ip";
			infoList = (List<Info>) runner.query(sql,new BeanListHandler(Info.class));
			return infoList;
		}
	
	   /**
	    * ����IP��ѯ���ͶƱ��Ϣ
	    * @param ip
	    * @return
	    * @throws SQLException
	    */
		public Info findInfoByIp(String ip) throws SQLException{
			Info info = null;
			QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
			String sql = "select ip,max(votetime) as votetime from info where ip = ? group by ip";
			info = (Info) queryRunner.query(sql,ip,new BeanHandler(Info.class));
		    return info;
		}
	/**
	 * ����һ��info��Ϣ 
	 * @param info
	 * @throws SQLException
	 */
   public void addInfo(Info info) throws SQLException{
	   QueryRunner queryRunner =  new QueryRunner(JdbcUtil.getDataSource());
	   String sql = "insert into info(ip) values(?)";
	   queryRunner.update(sql, info.getIp());
   }
}
