package com.estore.service;

import com.estore.domain.User;

public interface UserService {

	/**
	 * 
	* @Title: regist  
	* @Description: TODO 注册
	* @param @param u 用户
	* @return String  为空表示正常.非空,其内容为注册错误报告, 
	* @throws
	 */
	String regist(User u);

	/**
	 * 
	 * 
	 * */
	/**
	 * 
	* @Title: active  
	* @Description: TODO 激活用户 
	* @param @param activeCode :激活码
	* @param @return    设定文件  
	* @return String    为空表示正常.非空,其内容为激活错误报告, 
	* @throws
	 */
	String active(String activeCode);

	/**
	* @Title: sendActiveEmail  
	* @Description: TODO 发送激活邮件 
	* @param @param u 用户 @see User  
	* @return String    为空表示正常.非空,其内容为注册错误报告,  
	* @throws
	 */
	String sendActiveEmail(User u);

	/**
	* @Title: login  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param username
	* @param @param password
	* @param @return    设定文件  
	* @return Object[]   Object[0] 是message 为空表示正常.非空,其内容为登录错误报告,  如果登录成功 Object[1]是user对象, 
	* @throws
	 */
	Object[] login(String username, String password);

	/**
	* @Title: hasName  
	* @Description: TODO 验证用户名  
	* @param @param name
	* @param @return    设定文件  
	* @return boolean    true : name已存在, false name不存在 
	* @throws
	 */
	boolean hasName(String name);

}