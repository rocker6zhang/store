package com.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName: PayServlet 
 * @Description: TODO 支付
 * @author: zw
 * @date: 2018年3月26日 下午1:30:57
 */
public class PayServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//确认支付
		//nothing
		request.setAttribute("jumpURL",request.getContextPath()+"/home");
		request.setAttribute("title","支付完成");
		request.setAttribute("message","支付成功,<a href='"+request.getContextPath()+"/getOrder'>查看订单</a>, 或继续购物");
		request.getRequestDispatcher("/showMessage.jsp").forward(request, response);

	}
}
