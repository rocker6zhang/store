package com.estore.service;

import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.estore.dao.UserDAO;
import com.estore.domain.User;
import com.estore.utils.DataSourceUtils;
import com.estore.utils.MD5Utils;
import com.estore.utils.MailUtils;


/**
 * 用户业务逻辑处理,
 * 处理用户的注册/登录/信息加密(密码MD5)
 * 
 * */
public class UserService {

	public String regist(User u) { 
		String wrongMsg = null;
		UserDAO userDAO = new UserDAO();

		try {
			//判断用户名是否已经存在
			if(userDAO.hasUsername(u.getUsername())){
				wrongMsg = "username already exists ";
				return wrongMsg;
			}

			//判断 email address 是否已注册
			User user = userDAO.getUserByEmail(u.getEmail());

			// 是否存在 email 并且 已激活,
			if(user != null && user.getState() == 0){
				wrongMsg = "email already use!!!";
				return wrongMsg;
			}

			//将用户密码用md5加密,  是否放在这一层合适?
			u.setPassword( MD5Utils.md5( u.getPassword() ) );

			userDAO.addUser(u);


		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {

			closeConnection();
		}


		//send 激活 email...
		try {
			sendEmail(u);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			wrongMsg = "激活邮件发送失败";
			e.printStackTrace();
		}


		return wrongMsg;
	}

	private void closeConnection() {
		// TODO Auto-generated method stub
		try {

			DataSourceUtils.closeConnection(DataSourceUtils.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sendEmail(User u) throws AddressException, MessagingException {

		String message = "你的注册激活码是:"+"<a href='http://47.104.191.132:8080/store/activeUser?"
				+ "activeCode="+u.getActivecode()+"'>"+u.getActivecode()+"</a>, 请点击激活"+"  此邮件由系统发出,请勿回复.如果你没有注册, 请忽略这封邮件,谢谢!";
		MailUtils.sendMail(u.getEmail(), "用户激活", message);
	}



	/**
	 * 
	 * 激活用户
	 * */
	public String active(String activeCode) {
		// TODO Auto-generated method stub
		String wrongMsg = null;
		UserDAO userDAO = new UserDAO();

		User u = null;
		try {
			u = userDAO.getUserByActive(activeCode);
			
			//判断激活码是否正确
			if(u == null) {
				wrongMsg = "激活码错误"; 
				return wrongMsg;
			}


			//这里假设的场景是 同一个邮箱,  多次注册,但是都没有激活,  只允许一个激活
			// 是否存在 email 并且 已激活,
			User user = userDAO.getActiveEmail(u.getEmail());
			if(user != null){
				wrongMsg = "email already use, please write new Email address from regist page!!!";
				return wrongMsg;
			}

			
			//判断激活码是否过期
			long time = System.currentTimeMillis() - u.getUpdatetime().getTime();
			if(time > 1000*60*60*2) {
				wrongMsg = "激活码过期";
			}else {
				//激活用户
				userDAO.activeUser(u);
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			closeConnection();
		}




		return wrongMsg;
	}

	public String sendActiveEmail(User u) {
		String wrongMsg = null;
		//检查用户是否存在
		try {

			if(null == new UserDAO().getUser(u.getUsername(), MD5Utils.md5(u.getPassword()))) {
				wrongMsg = "用户名或密码错误";
				return wrongMsg;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {

			closeConnection();
		}

		try {
			sendEmail(u);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			wrongMsg = "发送失败";
			e.printStackTrace();
		}
		return wrongMsg;
	}

	public Object[] login(String username, String password){
		System.out.println(username+password);
		String msg = null;
		User u = null;
		try {
			u = new UserDAO().getUser(username, MD5Utils.md5(password));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			closeConnection();
		}

		//这里想返回两个参数,  1是方法产生的消息, 2是user对象
		Object[] o = {msg,u};
		//这里有两种情况, 1 .用户是否存在. 2. 用户是否激活, 
		if(u == null) {
			msg = "用户名或密码错误";
			o[0] = msg;
			return o;
		}

		//地址写死了,,这里没有request 不好动态获取
		if(u.getState() == 0) {
			msg = "用户未激活,点击<a href=/store/active.jsp>激活</a>";
			o[0] = msg;
			return o;
		}


		return o;

	}


	public boolean hasName(String name) {
		boolean f = true;
		try {
			if(new UserDAO().getUserByName(name) == null)
				f = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			closeConnection();
		}

		return f;
	}


































}
