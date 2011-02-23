package org.ocwiki.db.dao;

import org.ocwiki.data.ResourceReport;
import org.ocwiki.data.User;
import org.ocwiki.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

public class ResourceReportDAO {

	public static ResourceReport fetchByResourceAndUser(long resourceId,
			long userId) {
		return fetchByResourceAndUser(resourceId, UserDAO.fetchById(userId));
	}

	public static ResourceReport fetchByResourceAndUser(long resourceId,
			User user) {
		Session session = HibernateUtil.getSession();
		Query query;
		if (user == null) {
			String hql = "from ResourceReportWithoutUser where resource.id = :resId";
			query = session.createQuery(hql);
		} else {
			String hql = "from ResourceReportWithUser " +
					"where resource.id = :resId and user.id = :userId";
			query = session.createQuery(hql);
			query.setLong("userId", user.getId());
		}
		query.setLong("resId", resourceId);
		return (ResourceReport) query.uniqueResult();
	}
	
}
