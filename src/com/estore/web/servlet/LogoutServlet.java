package com.estore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estore.utils.CookieUtils;

/**
 * 注销.
 * 把session和cookie中的用户信息删除掉
 * */

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpServletRequest request; 
	HttpServletResponse response;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
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
