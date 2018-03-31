package com.estore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estore.domain.Product;
import com.estore.domain.User;
import com.estore.service.OrderServiceimpl;

/**
 * 
 * */
/**
 * 
 * @ClassName: RemoveCartItemServlet 
 * @Description: TODO 删除用户的 cart item
 * @author: zw
 * @date: 2018年3月26日 下午1:45:22
 */
public class RemoveCartItemServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//获得商品id和session中的订单项list
		String pid = request.getParameter("id");
		Map<Product,Integer> cart = (Map<Product,Integer>) request.getSession().getAttribute("cart");
		User u = (User) request.getSession().getAttribute("user");
		if(pid == null &&  cart == null) {
			request.getRequestDispatcher("/showCart.jsp").forward(request,response);
			return;
		}

		//在session 中找 product
		int id = Integer.parseInt(pid);
		Product removep = null;

		for(Product p : cart.keySet()) {
			if(p.getId() == id) {
				removep = p;
			}
		}

		//找到后 删除, 
		if(removep != null) {
			cart.remove(removep);

			//如果用户已登录,同步删除数据库数据
			if(u != null)
				new OrderServiceimpl().removeCartItem(u.getId(),removep);
		}


		request.getRequestDispatcher("/showCart.jsp").forward(request,response);
	}
}

