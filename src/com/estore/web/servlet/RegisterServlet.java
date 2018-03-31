package com.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.estore.domain.User;
import com.estore.service.UserServiceimpl;
import com.estore.dao.UserDAOimpl;

/**
 * 
 * @ClassName: RegisterServlet 
 * @Description: TODO 用户注册
 * @author: zw
 * @date: 2018年3月26日 下午1:46:29
 */
public class RegisterServlet extends HttpServlet {

	HttpServletRequest request; 
	HttpServletResponse response;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.request = request;
		this.response = response;
		
		
		String wrongMessage = null;
		
		
		//0. 验证码校验
		String checkCode = request.getParameter("checkCode");
		
		if (checkCode == null || ! checkCode.toUpperCase().equals((String) request.getSession().getAttribute("checkCode"))) {
			wrongMessage = "check code is  wrong";
			request.getSession().removeAttribute("checkcode_session");//从session中删除。
			
			wrongJump(wrongMessage);
			return;
		}
		
		
		//1. 封装数据   
		User user = new User();
		user.setActivecode(UUID.randomUUID().toString());
		user.setRole("user");//默认 是user
		user.setState(0);//这里假设使用注册用户都是新注册,  由service判读是否重复,  注意不能覆盖激活状态
		try {
			//如果没有对应的对象字段,  beanUtils 会初始化成null;
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		//2. 校验参数
		wrongMessage = user.check();
		//如果校验返回的结果"wrongMessage"不为null 表示有错误信息. 
		if(wrongMessage != null) {
			wrongJump(wrongMessage);//这里跳转回注册页面
			return;
		}
		
		//3. 调用服务(registService)
		
		wrongMessage = new UserServiceimpl().regist(user);
		//如果校验返回的结果"wrongMessage"不为null 表示有错误信息.
		if(wrongMessage != null) {
			wrongJump(wrongMessage);//这里跳转回注册页面
			return;
		}else {//完成注册
			request.setAttribute("message", "regist success 注册成功!!");
			request.setAttribute("jumpURL", request.getContextPath()+"/home");
			request.getRequestDispatcher("/showMessage.jsp").forward(request, response);
		}
	
	} 

	private void wrongJump(String wrongMessage) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("wrongMessage", "<font color='pink'>"+wrongMessage+"</font>");

		request.getRequestDispatcher("/regist.jsp").forward(request, response);
		
		
	}
	
	
	

}
