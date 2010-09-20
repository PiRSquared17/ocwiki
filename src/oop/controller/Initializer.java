package oop.controller;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import oop.conf.Config;
import oop.conf.ConfigIO;
import oop.persistence.HibernateUtil;

/**
 * Application Lifecycle Listener implementation class Initializer
 *
 */
public class Initializer implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Initializer() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
	public void contextInitialized(ServletContextEvent evt) {
		try {
			ServletContext context = evt.getServletContext();
			Config config = new Config();
			ConfigIO.loadDirectory(config,
					context.getRealPath(context.getInitParameter("configDir")));
			config.setHomeDir(config.getDomain() + context.getContextPath());
			Config.setDefaultInstance(config);
			HibernateUtil.init(config);
		} catch (IOException e) {
			System.out.println("Có lỗi khi đọc tệp cấu hình, hệ thống không thể khởi động.");
			throw new RuntimeException(e);
		}
	}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    }
	
}
