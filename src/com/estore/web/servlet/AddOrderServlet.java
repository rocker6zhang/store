package com.estore.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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
 * @ClassName: AddOrderServlet 
 * @Description: TODO 添加订单
 * @author: zw
 * @date: 2018年3月26日 下午12:11:31
 */
public class AddOrderServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取数据
		String message = null;
		User user = (User)request.getSession().getAttribute("user");
		String address = request.getParameter("address");
		
		//校验数据
		if(user == null){
			//用户未登录,
			request.setAttribute("jumpURL",request.getContextPath()+"/login.jsp");
			request.setAttribute("title","请登录");
			request.setAttribute("message","用户未登录, 请登录后再提交订单");
			request.getRequestDispatcher("/showMessage.jsp").forward(request, response);
			return;
		}
		
		//调用OrderService

		//购物车
		Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		//订单项
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		//封装订单信息,用户,地址
		Order o = new Order();
		o.setUser_id(user.getId());
		o.setAddress(address);
		
		double total_price = 0;
		
		if(cart == null || cart.isEmpty()){
			//按前端逻辑,  可以提交订单,那么购物车一定不为空
			request.getRequestDispatcher(response.encodeURL("/home")).forward(request, response);
			
			return;
		}
		
		//默认用户购买购物车所有商品
		int num = 0;
		for(Product p : cart.keySet()) {
			num = cart.get(p);
			total_price += (p.getPrice() * num);
			orderItems.add(new OrderItem(o, p, num ) );
		}
		
		o.setPrice(total_price);
		
		OrderService os = new OrderServiceimpl();
		message = os.addOrder(orderItems);
		
		if(message == null) {
			//提交订单成功
			//清空购物车
			//remove product of the user cart 
			for(Product p : cart.keySet()) {
				//待修改----要多次访问数据库, 要修改成只访问一次,传递给 OrderService 一个商品 id集合和用户id,, 将批量删除写在一条 sql语句中
				os.removeCartItem(o.getUser_id(), p);
			}
			cart.clear();
			request.setAttribute("jumpURL",request.getContextPath()+"/pay");
			request.setAttribute("title","支付页面");
			request.setAttribute("message","订单提交成功,请确认支付xxx元(默认已确认)");
			request.getRequestDispatcher("/showMessage.jsp").forward(request, response);
		}else {
			request.setAttribute("jumpURL",request.getContextPath()+"/order.jsp");
			request.setAttribute("title","提交失败");
			request.setAttribute("message","订单提交失败 ");
			request.getRequestDispatcher("/showMessage.jsp").forward(request, response);
		}
		
	}

}










