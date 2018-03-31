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

import com.estore.domain.OrderItem;
import com.estore.domain.Product;
import com.estore.service.OrderService;
import com.estore.service.OrderServiceimpl;


/**
 * 
 * @ClassName: ReceiptServlet 
 * @Description: TODO 确认收货
 * @author: zw
 * @date: 2018年3月26日 下午1:47:12
 */
public class ReceiptServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//获得order id
		String order_id = request.getParameter("id");
		if(order_id == null || "".equals(order_id)) {
			response.getWriter().print("空参数");
			return ;
		}
		
		
			
		int id = Integer.parseInt(order_id);
		
		OrderService os = new OrderServiceimpl();
		os.receipt(id);
		response.getWriter().print("ok");
		return;
	}
}

