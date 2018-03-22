package com.estore.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements javax.servlet.Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	} 

	@Override
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = (HttpServletRequest) req;
		request.setCharacterEncoding("utf-8");
		rep.setCharacterEncoding("utf-8");
		rep.setContentType("text/html;charset=utf-8");		
		
		//System.out.println("encoding");
		chain.doFilter(req, rep);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
