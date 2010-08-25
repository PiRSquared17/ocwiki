package oop.db.dao;

import java.util.Date;
import java.util.List;

import oop.data.Article;
import oop.data.Revision;
import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class RevisionDAO {

	public static <T extends Article> Revision<T> fetch(int id) {
		Session session = HibernateUtil.getSession();
		return (Revision<T>) session.get(Revision.class, id);
	}

	public static List<Revision<Article>> fetchByResource(long resourceId,
			int start, int size) {
		Session session = HibernateUtil.getSession();
		Query query = session
				.createQuery("from Revision where resource.id=:resId");
		query.setLong("resId", resourceId);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}

	public static List<Revision<Article>> fetchByResourceBefore(
			long resourceId, Date timestamp, int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Revision where resource.id=:resId and timestamp<=:ts";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setDate("ts", timestamp);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}

}
