package org.ocwiki.db.dao.stat;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ocwiki.controller.OcwikiApp;
import org.ocwiki.data.stat.ResourceView;

public final class ResourceViewDAO {
	
	static Session session;
	static Transaction transaction;
	private static int n = 0;

	public static void persist(ResourceView resourceView) {
		synchronized (session) {
			transaction = session.beginTransaction();
			session.persist(resourceView);
			if (++n >= OcwikiApp.get().getConfig().getResourceViewBufferSize()) {
				transaction.commit();
				n = 0;
			}
		}
	}
	
	private ResourceViewDAO() {
	}
	
}
