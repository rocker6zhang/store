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


public class CheckRenameServlet extends HttpServlet {


	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = "ok";
		//获得name
		String name = request.getParameter("name");
		
		//浏览器提交的前提是name不为null
		if(name == null ||  "".equals(name)) {
			System.out.println(name == null);
			System.out.println("".equals(name));
			throw new RuntimeException("参数异常");
		}

		UserService us = new UserService();
		boolean hasName = us.hasName(name);


		if(hasName) {
			msg = "no";
		}

		response.getWriter().print(msg);
	}
}

