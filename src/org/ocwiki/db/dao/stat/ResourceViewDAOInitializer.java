package org.ocwiki.db.dao.stat;

import java.util.EventObject;

import org.ocwiki.controller.OcwikiAppListener;
import org.ocwiki.persistence.HibernateUtil;

public class ResourceViewDAOInitializer implements OcwikiAppListener {

	@Override
	public void appInitialized(EventObject evt) {
		ResourceViewDAO.session = HibernateUtil.getSessionFactory()
				.openSession();
	}

	@Override
	public void appDestroying(EventObject evt) {
		ResourceViewDAO.session.flush();
		ResourceViewDAO.transaction.commit();
		ResourceViewDAO.session.close();
	}

}