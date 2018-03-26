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
 * @ClassName: RemoveProductServlet 
 * @Description: TODO 删除商品
 * @author: zw
 * @date: 2018年3月26日 下午1:44:52
 */
public class RemoveProductServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "删除成功";
		String pid = request.getParameter("id"); 
		Integer id = null;
		if(pid != null && !"".equals(pid)) {
			id = new Integer(pid);
		}
		
		
		if(id == null) {
			response.getWriter().print("删除失败");
			return;
		}
		
		ProductService ps = new ProductService();
		
		//不开放删除功能
//		String msg = ps.removeProduct(id,request);
//		if(msg != null) {
//			message = msg;
//		}
		
		response.getWriter().print(message);
		return;
		
	}


	
	
}
