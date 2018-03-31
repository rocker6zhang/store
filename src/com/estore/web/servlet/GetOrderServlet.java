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
import com.estore.service.OrderServiceimpl;

/**
 * 
 * @ClassName: GetOrderServlet 
 * @Description: TODO 查看订单
 * @author: zw
 * @date: 2018年3月26日 下午1:28:55
 */
public class GetOrderServlet extends HttpServlet {

	
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
		
		
		//调用查询服务
		OrderService os = new OrderServiceimpl();
		List<Order> order = os.getOrder(user);
		  for(Order o : order) {
		   o.setOis(os.getOrderItems(o));
		  }
		request.getSession().setAttribute("orders",order);
		request.getRequestDispatcher("/showOrder.jsp").forward(request,response);
	}
}

