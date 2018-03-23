package com.estore.web.listener;



import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class h
 *
 */
@WebListener
public class InitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("init listener run");
    	ResourceBundle r = ResourceBundle.getBundle("my");
    	ServletContext sc = sce.getServletContext();
    	sc.setAttribute("addProduct_password",r.getString("addProduct_password"));
    	sc.setAttribute("email",r.getString("email"));
    	sc.setAttribute("account",r.getString("account"));
    	sc.setAttribute("email_password",r.getString("email_password"));
         // TODO Auto-generated method stub
    }
	
}
