package oop.db.dao;

import java.util.List;

import oop.data.TopicReport;
import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class TopicReportDAO {

	public static TopicReport fetchById(long id) {
		Session session = HibernateUtil.getSession();
		String hql = "from TopicReport where resource=:id";
		Query query = session.createQuery(hql);
		query.setLong("id", id);
		return (TopicReport) query.uniqueResult();
	}
	
	public static List<TopicReport> fetchChildren(long resourceId) {
		Session session = HibernateUtil.getSession();
		String hql = "from TopicReport where " +
				"resource in (from Resource where article in " +
					"(from Topic where parent.id=:resId))";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		return query.list();
	}
	
	public static List<TopicReport> fetchTopLevels() {
		Session session = HibernateUtil.getSession();
		String hql = "from TopicReport " +
				"where resource in (from Resource where article in (" +
				"from Topic where parent is null))";
		Query query = session.createQuery(hql);
		return query.list();
	}


}
