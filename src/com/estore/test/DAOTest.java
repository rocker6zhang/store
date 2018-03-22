package com.estore.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.estore.utils.DataSourceUtils;


public class DAOTest {

	@Test
	public void test() {
		//new run().f();
//		String s = this.getClass().getClassLoader().getResource("").getPath(); // 经过测试，这种方法是安全的，最有效的
//
//		//String ss =this.getClass().getResource("/conf").getPath();// 经过测试，这种方法也是安全的
//		System.out.println(s);
//		//System.out.println(ss);
		StringBuilder sb = new StringBuilder("123");
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}

	
	@Test
	public void test2() throws SQLException {
		Connection c = DataSourceUtils.getConnectionByTransaction();
		PreparedStatement ps = c.prepareStatement("update user set username='123' where id = 14");
		int rs = ps.executeUpdate();
//		while(rs.next()) {
//			
//			System.out.println(rs.getString("username"));
//		}
		System.out.println(rs);
	}
}
