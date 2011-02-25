package org.ocwiki.db.dao.stat;


import org.hibernate.Session;
import org.ocwiki.controller.OcwikiApp;
import org.ocwiki.data.stat.ResourceView;

public final class ResourceViewDAO {

	public static void persist(ResourceView resourceView) {
		session.save(resourceView);
		if (n >= OcwikiApp.get().getConfig().getResourceViewBufferSize()) {
			session.flush();
			session.clear();
		}
	}
	
	private ResourceViewDAO() {
	}
	
	static Session session;
	private static int n = 0;
	
}
