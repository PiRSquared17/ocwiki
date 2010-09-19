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
public class OcwikiApp implements ServletContextListener {

    private ServletContext context;
	private Config config;

	/**
     * Default constructor. 
     */
    public OcwikiApp() {
    	if (INSTANCE != null) {
    		throw new RuntimeException("Already initialized???");
    	}
    	INSTANCE = this;
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
	public void contextInitialized(ServletContextEvent evt) {
		try {
			context = evt.getServletContext();
			config = new Config();
			ConfigIO.loadDirectory(config,
					context.getRealPath(context.getInitParameter("configDir")));
		} catch (IOException e) {
			System.out
					.println("Có lỗi khi đọc tệp cấu hình, hệ thống không thể khởi động.");
			throw new RuntimeException(e);
		}
		config.setHomeDir(config.getDomain() + context.getContextPath());
		Config.setDefaultInstance(config);
		HibernateUtil.setConfig(config);
	}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    public ServletContext getServletContext() {
		return context;
	}

    public Config getConfig() {
		return config;
	}

    private static OcwikiApp INSTANCE = null;
    
    public static OcwikiApp get() {
    	return INSTANCE;
    }

}
