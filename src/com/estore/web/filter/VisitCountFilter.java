package com.estore.web.filter;



import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;




public class VisitCountFilter implements Filter {
	private static Logger logger = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		logger = Logger.getLogger(VisitCountFilter.class);
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		
		String requestURL = httpServletRequest.getRemoteAddr();
		//httpServletRequest.getRequestURI()
		String uri = httpServletRequest.getRequestURI();
		String contextPath = httpServletRequest.getContextPath();
		String path = uri.substring(contextPath.length());
		//过滤静态资源
		if(!path.contains("css") && !path.contains("upload")) {
			
			logger.info(requestURL+" visit "+path);
		}
		
		
		// 放行
		chain.doFilter(httpServletRequest, response);
	}

	public void destroy() {
		logger = null;

	}

}
