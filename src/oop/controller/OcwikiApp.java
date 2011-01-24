package oop.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import oop.conf.Config;
import oop.conf.ConfigIO;
import oop.conf.ConfigIOException;
import oop.data.stat.DailyStatistic;
import oop.db.dao.stat.DailyStatisticDAO;
import oop.persistence.HibernateUtil;
import oop.util.SiteViewCountUtil;

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
			initializeImpl(configPath);
			DailyStatistic lastStatistic = DailyStatisticDAO.fetchLastStatistic();
			SiteViewCountUtil.initialize(lastStatistic.getViewCount());
		} catch (ConfigIOException e) {
			configException = e;
		}
	}

	private void initializeImpl(String configPath) {
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
			ocwikiApp.initializeImpl(configPath);
		}
	}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	DailyStatisticDAO.saveCurrentStatistic();
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

	public File getTemporaryDirectory() {
		if (context != null) {
			return (File)context.getAttribute("javax.servlet.context.tempdir");
		}
		return new File(System.getProperty("java.io.tmpdir"));
	}

}
