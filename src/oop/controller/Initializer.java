package oop.controller;


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
    	ServletContext context = evt.getServletContext();
		Config config = new Config();
		ConfigIO.loadDirectory(config, context.getRealPath("/WEB-INF/conf"));
		Config.setDefaultInstance(config);
		HibernateUtil.init(config);
	}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    }
	
}
