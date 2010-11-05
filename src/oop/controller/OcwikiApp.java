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
public class OcwikiApp implements ServletContextListener {

	public static final String VERSION = "1.0";
	public static final String NAME = "OCWiki";
	
    private ServletContext context;
	private Config config;
	private ConfigIOException configException = null;

	/**
     * Default constructor. 
     */
    public OcwikiApp() {
    	if (INSTANCE == null) {
    		INSTANCE = this;
    	}
    }
    
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
	public void contextInitialized(ServletContextEvent evt) {
		try {
			context = evt.getServletContext();
			config = new Config();
			String configPath = context.getRealPath(context.getInitParameter("configDir"));
			init(configPath);
		} catch (ConfigIOException e) {
			configException = e;
		}
	}

	private void init(String configPath) {
		ConfigIO.loadDirectory(config, configPath);
		config.setHomeDir(config.getDomain() + context.getContextPath());
		
		context.setAttribute("app", this);
		context.setAttribute("config", config);
		context.setAttribute("homeDir", config.getHomeDir());
		context.setAttribute("scriptPath", getScriptPath());

		HibernateUtil.setConfig(config);
	}
	
	public static void initialize(String configPath) {
		if (INSTANCE == null) {
			OcwikiApp ocwikiApp = new OcwikiApp();
			ocwikiApp.init(configPath);
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
    	if (configException != null) {
			throw configException;
		}
		return config;
	}

    public String getVersion() {
    	return VERSION;
    }
    
    public String getName() {
    	return NAME;
    }
    
    private static OcwikiApp INSTANCE = null;
    
    public static OcwikiApp get() {
    	return INSTANCE;
    }
	
	public String getScriptPath() {
		return config.getHomeDir() + config.getMainEntry();
	}

}
