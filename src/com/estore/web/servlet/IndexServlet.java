package com.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.estore.domain.Product;
import com.estore.service.ProductService;



/**
 * 
 * @ClassName: IndexServlet 
 * @Description: TODO 获取商品列表,返回给首页显示<br/>
 * 						商品列表是整个应用要经常使用的, 所以存入application 域, 减少读数据库
 * @author: zw
 * @date: 2018年3月26日 下午1:22:14
 */
public class IndexServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		List<Product> products = (List<Product>) request.getServletContext().getAttribute("products");
		
		//如果application 域里面没有, 就读数据库,并存入application域
		if(products == null){
			
			ProductService service = new ProductService();
			products = service.getProducts(25);
			request.getServletContext().setAttribute("products", products);
		}



		request.getRequestDispatcher("/home.jsp").forward(request, response);

	} 

}





