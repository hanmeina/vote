package com.xjtu.vote.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xjtu.vote.domin.Address;
import com.xjtu.vote.util.JdbcUtil;

public class AddressDao {
	
	/**
	 * ¸ù¾Ýip²éÑ¯Address
	 * @param ip
	 * @return
	 * @throws SQLException
	 */
   public Address findAddressByIp(String ip) throws SQLException{
	   Address  address = null;
	   QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
	   String sql = "select * from address where ip = ?";
	   address = (Address) queryRunner.query(sql, ip,new BeanHandler(Address.class));
	   return address;
	   
	   
   }
}
