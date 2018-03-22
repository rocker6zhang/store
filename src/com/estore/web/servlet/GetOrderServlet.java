package com.estore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estore.domain.Order;
import com.estore.domain.OrderItem;
import com.estore.domain.Product;
import com.estore.domain.User;
import com.estore.service.OrderService;


public class GetOrderServlet extends HttpServlet {

	/*
	 * 业务逻辑获得要删除订单项的商品id 遍历session中的list 删除订单项
	 * 这里通过商品id确定一条订单项, 
	 * 最后跳转回购物车页面
	 * */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = null;
		User user = (User)request.getSession().getAttribute("user");
		
		if(user == null){
			//用户未登录,
			request.setAttribute("jumpURL",request.getContextPath()+"/login.jsp");
			request.setAttribute("title","请登录");
			request.setAttribute("message","用户未登录, 请登录后再查看订单");
			request.getRequestDispatcher("/showMessage.jsp").forward(request, response);
			return;
		}
		
		OrderService os = new OrderService();
		List<Order> order = os.getOrder(user);
		  for(Order o : order) {
		   o.setOis(os.getOrderItems(o));
		  }
		request.getSession().setAttribute("orders",order);
		
//		System.out.println(orders);
//		System.out.println(orders);
		request.getRequestDispatcher("/showOrder.jsp").forward(request,response);
	}
}

