package com.xjtu.vote.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xjtu.vote.domin.Admin;
import com.xjtu.vote.util.JdbcUtil;

public class AdminDao {

	      /**
	       * 根据用户名和密码验证管理员信息
	       * @param username
	       * @param password
	       * @return
	       * @throws SQLException
	       */
		public Admin findAdminByUsernameAndPassword(String username,String password) throws SQLException{
			Admin admin = null;
			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
			String sql = "select * from admin where username = ? and password = ?";
			admin = (Admin) runner.query(sql,new Object[]{username,password},new BeanHandler(Admin.class));
			return admin;
		}
}
