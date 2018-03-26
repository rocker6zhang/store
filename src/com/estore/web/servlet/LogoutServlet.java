package com.estore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estore.utils.CookieUtils;



/**
 * 
 * @ClassName: LogoutServlet 
 * @Description: TODO 注销已登录的用户<br/>
 * 					     删除掉session和cookie中的用户信息
 * @author: zw
 * @date: 2018年3月26日 下午1:31:47
 */

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//不能直接删除session,  session里可能有其他的信息
		request.getSession().setAttribute("user", null);;
		
		//删除cookie
		Cookie cookie = CookieUtils.findCookieByName(
				request.getCookies(), "autologin");
		if (cookie != null) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
//		System.out.println(cookie);
		// 重定向可以跳转到任意路径,请求转发只能在本站内跳转.
		response.sendRedirect(request.getContextPath()+"/home"); 
		
		
		return;
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
