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
import com.estore.service.UserService;
import com.estore.service.UserServiceimpl;


/**
 * 
 * @ClassName: CheckRenameServlet 
 * @Description: TODO 检查用户名重名,对应注册页面
 * @author: zw
 * @date: 2018年3月26日 下午12:18:12
 */
public class CheckRenameServlet extends HttpServlet {


	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ajax响应信息
		String msg = "ok";
		//获得name
		String name = request.getParameter("name");
		
		//校验数据
		if(name == null ||  "".equals(name)) {
			System.out.println(name == null);
			System.out.println("".equals(name));
			throw new RuntimeException("参数异常");
		}

		//调用服务
		UserService us = new UserServiceimpl();
		boolean hasName = us.hasName(name);


		if(hasName) {
			msg = "no";
		}

		//返回响应信息
		response.getWriter().print(msg);
	}
}

