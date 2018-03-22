package com.estore.web.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estore.utils.CheckCode;
import com.estore.utils.CheckImg;



public class CheckSimpleCodeServlet extends HttpServlet {

	@Override 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//当session有效期到了,被销毁后,再访问次servlet会报以下exceptin

		//Cannot create a session after the response has been committed

		//request.getSession(); 据说加这个可以解决,

		

		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "no-cache"); 
		response.setHeader("CacheControl", "no-cache");
		response.setHeader("Expires", "-1");
		//String code = CheckCode.getCheckImg(response.getOutputStream());
		String code = CheckCode.getCheckImg(response.getOutputStream());

		request.getSession().setAttribute("checkCode",code);
		return;
	}

}
