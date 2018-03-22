package com.estore.web.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.estore.domain.Product;
import com.estore.domain.User;
import com.estore.service.ProductService;
import com.estore.service.UserService;
import com.estore.utils.PicUtils;
import com.estore.utils.UploadUtils;


/**
 * 
 * 
 * */
public class SearchProductServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String key = request.getParameter("key");
		String message = null;
		if(key == null ) {
			message = "关键词有误,请重新输入";
			request.getSession().setAttribute("searchMSG", message);
			request.getRequestDispatcher("/home").forward(request, response);
		}
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





