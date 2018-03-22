package com.estore.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estore.dao.ProductDAO;
import com.estore.domain.OrderItem;
import com.estore.domain.Product;
import com.estore.domain.User;
import com.estore.service.OrderService;

/*
 * 购物车添加商品
 * 将用户的订单项保存在session中.!!(考虑优先保存在cookie中)
 * 以OrderItem对象封装订单项,保存于List中,然后存入session
 * */
public class AddCartItemServlet extends HttpServlet {
	HttpServletRequest request;
	HttpServletResponse response;
	String message;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.response = response;


		//获取数据,  数据校验,

		if(request.getParameter("id") == null || request.getParameter("num") == null 
				|| Integer.parseInt(request.getParameter("num")) == 0) {
			door("添加失败");
			return;
		}
		
		//获取数据
		Integer id =  Integer.parseInt(request.getParameter("id"));
		Integer num = Integer.parseInt(request.getParameter("num"));
		User u = (User) request.getSession().getAttribute("user");

		Product p = null;
		try {
			p = new ProductDAO().getProduct(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(p == null || p.check() != null) {
			door("添加失败");

			return ;
		}


		//封装数据
		Map<Product,Integer> cart = (Map<Product,Integer>) request.getSession().getAttribute("cart");
		if(cart == null) {
			//第一次使用购物车
			cart = new HashMap<Product,Integer>();
		}


		Integer count = cart.get(p);

		//写到数据库
		OrderService os = new OrderService();
		

		if(count == null) {
			//商品第一次出现
			count = 0;
			
			// 如果用户已登录,添加到cart 数据库, 
			if(u != null)
			os.addCartItem(p,num,u.getId());
		}

		//如果用户已登录,更新数据
		if(u != null)
		os.updateCartItem(p,num,u.getId());
		
		
		//??? 没有考虑多用户商品库存问题
		num = count + num;
		cart.put(p, num);



		request.getSession().setAttribute("cart", cart);

		//		System.out.println(cart);

		door("添加成功");


	}

	void door(String message) throws IOException {
		response.getWriter().println(message);
		response.getWriter().flush();
	}

}
