package oop.controller;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import oop.conf.Config;
import oop.conf.ConfigIO;
import oop.persistence.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

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
			config.setHomeDir(config.getDomain() + context.getContextPath());
			Config.setDefaultInstance(config);
			HibernateUtil.init(config);
			
			System.out.print("Creating index... ");
			try {
				Session session = HibernateUtil.getSession();
				FullTextSession fullTextSession = Search
						.getFullTextSession(session);
				fullTextSession.createIndexer().startAndWait();
				System.out.println("Done.");
			} catch (InterruptedException ex) {
				System.out.println("Failed.");
			} finally {
				HibernateUtil.closeSession();
			}
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
