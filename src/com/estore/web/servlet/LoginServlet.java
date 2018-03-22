package com.estore.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estore.dao.UserDAO;
import com.estore.domain.Product;
import com.estore.domain.User;
import com.estore.service.OrderService;
import com.estore.service.UserService;
import com.estore.utils.MD5Utils;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpServletRequest request; 
	HttpServletResponse response;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		//User uu = (User) request.getSession().getAttribute("user");
		//System.out.println(uu);

		//做参数校验
		//封装数据
		//调用service
		//返回视图

		String msg = null;
		Object[] obj = null;
		User u = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//0. 验证码校验
		String checkCode = request.getParameter("checkCode");
		if (checkCode == null || ! checkCode.toUpperCase().equals((String) request.getSession().getAttribute("checkCode"))) {
			msg = "check code is  wrong";
			request.getSession().removeAttribute("checkcode_session");//从session中删除。

			jump(msg);
			return;
		}

		//非空校验
		if(username == null || password == null || "".equals(username) || "".equals(password)){
			msg = "用户名和密码不能为空";
			jump(msg);
			return;
		}

		UserService us = new UserService();

		System.out.println("用户名"+username+"密码"+password);

		//这里返回两个参数,  1是方法产生的消息, 2是user对象
		obj = us.login(username, password);

		//强转,  如果数组是null, 强转后也null
		msg = (String) obj[0];
		u = (User) obj[1];
		//判断是否登录成功,  有无错误消息返回.
		//System.out.println("msg:"+msg+"  u:"+u);
		if(msg != null || u == null){
			jump(msg);
			return;
		}

		//登录成功
		//可能出现一个浏览器登录2个以上账号的问题,  这里相当于规定同一时刻只有一个账号可以登录
		//request.getSession().invalidate();//先销毁session。不能销毁 session中有其他信息


		//还可能出现 同一账号在两个浏览器上登录的问题, 
		//暂未解决

		//将用户保存到 session
		request.getSession().setAttribute("user",u);

		//还可能出现 同一账号在两个浏览器上登录的问题

		//判断用户是否勾选自动登录
		String autologin = request.getParameter("autologin");
		if("on".equals(autologin)) {
			//如果用户勾选,  将用户名和密码存储到cookie
			//没有对信息加密
			Cookie cookie = new Cookie("autologin", URLEncoder.encode(
					username, "utf-8")+"::"+password);

			cookie.setMaxAge(60*60*24*10);
			cookie.setPath("/");
			response.addCookie(cookie);


		}else {
			Cookie cookie = new Cookie("autologin", URLEncoder.encode(
					u.getUsername(), "utf-8")+"::"+password);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}

		/*
		 * 同步购物车
		 * */
		setCart( request,  response);

		request.setAttribute("title", "登录成功");
		request.setAttribute("message", "登录成功,3秒后跳转到首页");
		request.setAttribute("jumpURL", request.getContextPath()+"/home");
		request.getRequestDispatcher("/showMessage.jsp").forward(request, response);

		return;


	}

	/*
	 * user购物车, 会添加到数据库, 那么没有登录的时候的cart 会和 数据库里的cart 冲突,  需要同步
	 * */
	private void setCart(HttpServletRequest request2, HttpServletResponse response2) {
		// TODO Auto-generated method stub
		Map<Product,Integer> cart = (Map<Product,Integer>) request.getSession().getAttribute("cart");
		User u = (User) request.getSession().getAttribute("user");

		if(cart == null) {
			//购物车为空,也需要同步,  把数据库同步到session
			cart = new HashMap<Product,Integer>();
		}

		OrderService os = 	new OrderService();
		//db cart
		Map<Product,Integer> dbcart = os.getCart(u);

		if(dbcart == null || dbcart.isEmpty()) {
			// 数据库里没有cart item,  session cart 全部写入 db  
			for(Product p : cart.keySet()) {
				//add
				os.addCartItem(p, cart.get(p), u.getId());

			}
			//不用再同步了
			return;
		}

		//遍历 session cart
		for(Product p : cart.keySet()) {
			if(dbcart.containsKey(p)) {
				//DB 已有, UPDATE
				os.updateCartItem(p, cart.get(p), u.getId());
			}else {
				//DB 没有,  add
				os.addCartItem(p, cart.get(p), u.getId());
			}
		}

		if(dbcart == null || dbcart.isEmpty()) {
			return;
		}

		//遍历 DB cart
		for(Product p : dbcart.keySet()) {
			Integer count = cart.get(p);
			if(count != null) {
				//DB 已有, UPDATE
				cart.put(p, count + dbcart.get(p));
			}else {
				//DB 没有,  add
				cart.put(p, dbcart.get(p));

			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void jump(String wrongMessage) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println(wrongMessage);
		request.setAttribute("LMessage", wrongMessage);

		request.getRequestDispatcher("/login.jsp").forward(request, response);


	}
}
