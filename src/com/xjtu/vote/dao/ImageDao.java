package com.xjtu.vote.dao;

import java.sql.SQLException;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xjtu.vote.domin.Image;
import com.xjtu.vote.util.JdbcUtil;

public class ImageDao {
	/**
	 * ¸ù¾Ýcid²éÑ¯Image
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
    public Image  findImageById(int id) throws SQLException{
    	Image image = null;
    	QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
    	String sql = "select * from image where cid=?";
        image = (Image)queryRunner.query(sql, id,new BeanHandler(Image.class));
        return image;
    }
}
