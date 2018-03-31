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
import com.estore.service.ProductServiceimpl;
import com.estore.service.UserServiceimpl;
import com.estore.utils.PicUtils;
import com.estore.utils.UploadUtils;



/**
 * 
 * @ClassName: ProductListServlet 
 * @Description: TODO 商品管理列表,对应商品管理页面
 * @author: zw
 * @date: 2018年3月26日 下午1:47:55
 */
public class ProductListServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductService service = new ProductServiceimpl();

		List<Product> list;

		//取30条商品
		list = service.getProducts(30);
		request.setAttribute("products",list);


		request.getRequestDispatcher("/productList.jsp").forward(request, response);

	} 

}





