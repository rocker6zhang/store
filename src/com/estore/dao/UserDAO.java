package com.estore.dao;

import java.sql.Connection;

/*
 * 操作用户表
 * 
 * 功能:
 * 		提供操作用户表的方法
 * 			1.验证用户名是否存在
 * 			2.添加用户
 * 			3.按name 和 password 查找用户(登录用)
 * 
 * 注意:
 * 		
 * 		
 * */


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.estore.domain.User;
import com.estore.utils.DataSourceUtils;


public class UserDAO extends DAO {


	public User getUser(String name, String password) throws SQLException{
		System.out.println("用户名"+name+"密码"+password);
		User user = null; 
		String sql = "select * from user where username = ? and password = ?";
		Connection conn = DataSourceUtils.getConnection();
		try(
				PreparedStatement stat = conn.prepareStatement(sql);){

			stat.setString(1,name);
			stat.setString(2,password);
			ResultSet resu = stat.executeQuery();

			user = setUser(resu);

		}catch(SQLException e) {
			throw e;
		}
		return user; 
	}

	

	/**
	 * 验证用户名是否占用, 
	 * @throws SQLException 
	 * 
	 * */

	public boolean hasUsername(String username) throws SQLException{
		boolean hasName = false;
		String sql = "select username from user where username=?";
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql);){

			stat.setString(1,username);

			ResultSet resu = stat.executeQuery();

			if(resu.next()) {
				//如果查询无返回结果, 就不会进来
				hasName = true;

			}

		}catch(SQLException e) {
			throw e;
		}
		return hasName;
	}

	/**
	 * 添加用户
	 * 这里确认(假设)user对象里的数据已经经过校验,可以写入数据库.
	 * @throws SQLException 
	 * */
	public int addUser(User u) throws SQLException {

		/*
		private String username;
		private String password;
		private String email;
		private String role; // 角色 默认是user
		private int state; // 是否激活 0 未激活
		private String activecode; // 激活码 UUID获取
		private Timestamp updatetime;
		 * */
		String sql = "insert into user values(null,?,?,?,?,?,?,?);";
		int id = -1;
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

			stat.setString(1,u.getUsername());
			stat.setString(2,u.getPassword());
			stat.setString(3,u.getEmail());
			stat.setString(4,u.getRole());
			stat.setInt(5,u.getState());
			stat.setString(6,u.getActivecode());
			stat.setObject(7, new Timestamp(System.currentTimeMillis()));

			stat.executeUpdate();
			ResultSet resu = stat.getGeneratedKeys();
			if(resu.next()) {
				id = (resu.getInt(1));
			}
			resu.close();

		}catch(SQLException e) {
			throw e;
		}


		return id;

	}
	/**
	 * 根据激活码查找用户
	 * @throws SQLException 
	 * */
	public User getUserByActive(String activeCode) throws SQLException {
		/*
		private String username;
		private String password;
		private String email;
		private String role; // 角色 默认是user
		private int state; // 是否激活 0 未激活
		private String activecode; // 激活码 UUID获取
		private Timestamp updatetime;
		 * */
		String sql = "select * from user where activecode=?;";
		User user = null;
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

			stat.setString(1,activeCode);


			ResultSet resu = stat.executeQuery();
			//将查询结果封装到 user对象
			user = setUser(resu);

			resu.close();//关闭结果集,不然有可能内存泄露
		}catch(SQLException e) {
			throw e;
		}


		return user;
	}

	//激活用户
	public void activeUser(User u) throws SQLException {

		String sql = "update user set state=? where username=?;";

		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

			stat.setInt(1,1);
			stat.setString(2,u.getUsername());


			stat.executeUpdate();
		}catch(SQLException e) {
			throw e;
		}



	}

	/**
	 * 封装user对象,  取ResultSet中的当前元素,  only one
	 * */
	private User setUser(ResultSet resu) throws SQLException {

		/*
		private String username;
		private String password;
		private String email;
		private String role; // 角色 默认是user
		private int state; // 是否激活 0 未激活
		private String activecode; // 激活码 UUID获取
		private Timestamp updatetime;
		 * */
		User user = null;
		if(resu.next()) {
			user = new User();
			user.setId(resu.getInt("id"));
			user.setUsername(resu.getString("username"));
			user.setPassword(resu.getString("password"));
			user.setEmail(resu.getString("email"));
			user.setRole(resu.getString("role"));
			user.setState(resu.getInt("state"));
			user.setActivecode(resu.getString("activecode"));
			user.setUpdatetime((Timestamp)resu.getObject("updatetime")); 
		}

		return user;

	}

	public User getUserByName(String name) throws SQLException {

		String sql = "select * from user where username=?;";
		User user = null;
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

			stat.setString(1,name);


			ResultSet resu = stat.executeQuery();
			//将查询结果封装到 user对象
			user = setUser(resu);
//			System.out.println(user);
//			System.out.println(name);
			resu.close();//关闭结果集,不然有可能内存泄露
		}catch(SQLException e) {
			throw e;
		}


		return user;
		
	}



	public User getUserByEmail(String email) throws SQLException {
		String sql = "select * from user where email=?;";
		User user = null;
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

			stat.setString(1,email);


			ResultSet resu = stat.executeQuery();
			//将查询结果封装到 user对象
			user = setUser(resu);
			resu.close();//关闭结果集,不然有可能内存泄露
		}catch(SQLException e) {
			throw e;
		}


		return user;
		
	}



	public User getActiveEmail(String email) throws SQLException {
		String sql = "select * from user where email=? and state=1;";
		User user = null;
		Connection conn = DataSourceUtils.getConnection();
		try(PreparedStatement stat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

			stat.setString(1,email);


			ResultSet resu = stat.executeQuery();
			//将查询结果封装到 user对象
			user = setUser(resu);
//			System.out.println(user);
//			System.out.println(name);
			resu.close();//关闭结果集,不然有可能内存泄露
		}catch(SQLException e) {
			throw e;
		}


		return user;
		
	}
}

















