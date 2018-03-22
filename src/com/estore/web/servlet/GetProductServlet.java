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
 * 获取商品
 * */
public class GetProductServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		//if(true)	
		//throw new RuntimeException("test");
		ProductService service = new ProductService();

		Product p = null;

		if(id != null) {


			p = service.getProduct(Integer.valueOf(id));
			request.setAttribute("p",p);
			//					System.out.println(p.getName()+"------------");
		}


		request.getRequestDispatcher("/showProduct.jsp").forward(request, response);

	} 

}





