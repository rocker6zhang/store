package com.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.estore.domain.Product;
import com.estore.service.ProductService;
import com.estore.service.ProductServiceimpl;


/**
 * 
 * @ClassName: UpdateProductServlet 
 * @Description: TODO 更新商品信息
 * @author: zw
 * @date: 2018年3月26日 下午1:37:57
 */
public class UpdateProductServlet extends HttpServlet {


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "更新成功";
		Product p = new Product();

		//封装数据
		try {
			BeanUtils.populate(p, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//校验数据
		String msg = p.updateCheck();
		if(msg != null) {
			message = msg;
			response.getWriter().print(message);
			return;
		}

		//调用服务
		ProductService ps = new ProductServiceimpl();

		msg = ps.updateProduct(p);
		if(msg != null) {
			message = msg;
		}

		response.getWriter().print(message);
		return;

	}


}
