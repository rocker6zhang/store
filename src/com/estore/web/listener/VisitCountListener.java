package com.estore.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class VisitCountListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent e) {
		// TODO Auto-generated method stub
		ServletContext application = e.getSession().getServletContext();
		Integer num = (Integer) application.getAttribute("online-num");
		if(num == null) {
			num = 0;
			
		}
		num++;
		application.setAttribute("online-num", num);	
//		
//		System.out.println(num);
//		System.out.println(application.getAttribute("online-num"));
//		Enumeration<String> s = request.getServletContext().getAttributeNames();
//		while(s.hasMoreElements()) {
//			System.out.println(s.nextElement());
//		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		// TODO Auto-generated method stub
		ServletContext application = e.getSession().getServletContext();
		Integer num = (Integer) application.getAttribute("online-num");
		if(num == null) {
			num = 1;
		}
		num--;
		application.setAttribute("online-num", num);
	}

}
