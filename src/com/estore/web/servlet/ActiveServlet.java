package com.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estore.domain.User;
import com.estore.service.UserService;
import com.estore.service.UserServiceimpl;

/**
 * 用户激活
 * 
 * @author Administrator
 *
 */

public class ActiveServlet extends HttpServlet {

	//	HttpServletRequest request; 
	//	HttpServletResponse response;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = new UserServiceimpl();
		String message = null;
		String activeCode = request.getParameter("activeCode");
		//用户携带验证码,需要验证服务,  
		if(activeCode == null) {
			//如果激活码为null,  说明客户端异常, 跳转到主页.
			request.setAttribute("message", "");
			request.setAttribute("jumpURL", request.getContextPath()+"/home");
			request.getRequestDispatcher("/showMessage.jsp").forward(request, response);
		}


		message = service.active(activeCode);

		//message=null 表示激活成功, 不为空 message就是错误消息
		if(message == null) {
			request.setAttribute("message", "regist success 激活成功!!");
			request.setAttribute("jumpURL", request.getContextPath()+"/home");
			request.getRequestDispatcher("/showMessage.jsp").forward(request, response);
		}else {

			request.setAttribute("message", "<font color='pink'>激活失败,原因是: "+message+"</font>");
			request.setAttribute("jumpURL", request.getContextPath()+"/active.jsp");
			request.getRequestDispatcher("/showMessage.jsp").forward(request, response);

		}

	

	}






}
