package org.ocwiki.db.dao.stat;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.ocwiki.persistence.HibernateUtil;

public class ResourceViewDAOInitializer implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		ResourceViewDAO.session.flush();
		ResourceViewDAO.session.clear();
		ResourceViewDAO.session.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		ResourceViewDAO.session = HibernateUtil.getSessionFactory().openSession();
	}
	
}