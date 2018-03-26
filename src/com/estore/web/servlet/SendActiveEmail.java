package com.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estore.domain.User;
import com.estore.service.UserService;

/**
 * 
 * @ClassName: SendActiveEmail 
 * @Description: TODO 发送激活邮件,对应激活页面
 * @author: zw
 * @date: 2018年3月26日 下午1:38:51
 */
public class SendActiveEmail extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = new UserService();
		String message = null;

		//获取数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 2 用户携带用户名和密码 需要发送激活邮件服务


		//service.hasUser(username,password);
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setEmail("@");//U.check 会校验email
		//校验数据
		String str = u.check();
		
		if(str != null) {
			request.setAttribute("message", str);
			request.getRequestDispatcher("/active.jsp").forward(request, response);
			return;
		}
		
		//调用service 发送邮件
		message = service.sendActiveEmail(u);

		if(message == null) {
			request.setAttribute("message", "激活邮件已发送,请注意查收");
			request.setAttribute("jumpURL", request.getContextPath()+"/home");
			request.getRequestDispatcher("/showMessage.jsp").forward(request, response);
		}else {

			request.setAttribute("message", "<font color='pink'>发送失败,原因是: "+message+"</font>");
			request.setAttribute("jumpURL", request.getContextPath()+"/active.jsp");
			request.getRequestDispatcher("/showMessage.jsp").forward(request, response);

		}

	}


}
