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
 * @ClassName: SearchProductServlet 
 * @Description: TODO 商品搜素
 * @author: zw
 * @date: 2018年3月26日 下午1:42:07
 */
public class SearchProductServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//获取数据
		String key = request.getParameter("key");
		String message = null;
		if(key == null ) {
			message = "关键词有误,请重新输入";
			request.getSession().setAttribute("searchMSG", message);
			request.getRequestDispatcher("/home").forward(request, response);
		}
		
		//调用服务
		ProductService service = new ProductService();

		List<Product> list = null;
		list = service.getProductByKey(key);
		//System.out.println(list);
		if(list == null) {
			request.setAttribute("searchMSG", "没有找到你搜索的商品");
		}else {
			request.setAttribute("products", list);
		}
		request.getRequestDispatcher("/home.jsp").forward(request, response);

	} 

}





