package com.estore.web.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Enumeration;
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
 * @ClassName: GetProductServlet 
 * @Description: TODO 获取商品信息
 * @author: zw
 * @date: 2018年3月26日 下午1:24:06
 */
public class GetProductServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String id = request.getParameter("id");
		ProductService service = new ProductServiceimpl();

		Product p = null;

		if(id == null) {
			
			request.setAttribute("jumpURL",request.getContextPath()+"/home");
			request.setAttribute("title","出错了");
			request.setAttribute("message","提交的商品ID有误");
			request.getRequestDispatcher("/showMessage.jsp").forward(request, response);
			return;
		}
		p = service.getProduct(Integer.valueOf(id));
		request.setAttribute("p",p);
		//					System.out.println(p.getName()+"------------");
		request.getRequestDispatcher("/showProduct.jsp").forward(request, response);




	} 

}





