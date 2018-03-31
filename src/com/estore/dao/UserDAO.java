package com.estore.dao;

import java.sql.SQLException;

import com.estore.domain.User;

public interface UserDAO {

	User getUser(String name, String password) throws SQLException;

	/**
	 * 验证用户名是否占用, 
	 * @throws SQLException 
	 * 
	 * */

	boolean hasUsername(String username) throws SQLException;

	/**
	 * 添加用户
	 * 这里确认(假设)user对象里的数据已经经过校验,可以写入数据库.
	 * @throws SQLException 
	 * */
	int addUser(User u) throws SQLException;

	/**
	 * 根据激活码查找用户
	 * @throws SQLException 
	 * */
	User getUserByActive(String activeCode) throws SQLException;

	//激活用户
	void activeUser(User u) throws SQLException;

	User getUserByName(String name) throws SQLException;

	User getUserByEmail(String email) throws SQLException;

	User getActiveEmail(String email) throws SQLException;

}