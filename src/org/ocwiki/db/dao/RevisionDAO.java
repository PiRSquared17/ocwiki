package org.ocwiki.db.dao;

import java.util.Date;
import java.util.List;

import org.ocwiki.data.Article;
import org.ocwiki.data.Revision;
import org.ocwiki.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class RevisionDAO {

	public static <T extends Article> Revision<T> fetch(long id) {
		Session session = HibernateUtil.getSession();
		return (Revision<T>) session.get(Revision.class, id);
	}

	public static List<Revision<Article>> fetchByResource(long resourceId,
			int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Revision where resource.id=:resId order by id desc";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}
	
	public static Revision<? extends Article> fetchEarliestByResource(long resourceId) {
		Session session = HibernateUtil.getSession();
		String hql = "from Revision where id = " +
					"(select min(id) from Revision " +
					"where resource.id=:resId)";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		return (Revision<? extends Article>) query.uniqueResult();
	}
	
	public static Revision<? extends Article> fetchLatestByResource(long resourceId) {
		Session session = HibernateUtil.getSession();
		String hql = "from Revision where id = " +
					"(select max(id) from Revision " +
					"where resource.id=:resId)";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		return (Revision<? extends Article>) query.uniqueResult();
	}
	
	public static long countByResource(long resourceId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from Revision where resource.id = :resId";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		return (Long) query.uniqueResult();
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
	
	public static <T extends Article> Revision<T> fetchPreviousRevision(long id) {
		Session session = HibernateUtil.getSession();
		String hql = "from Revision where id = (" +
				"select max(id) from Revision " +
				"where resource=(select resource from Revision where id=:revId) " +
					"and id < :revId)";
		Query query = session.createQuery(hql);
		query.setLong("revId", id);
		return (Revision<T>) query.uniqueResult();
	}

	public static <T extends Article> Revision<T> fetchNextRevision(long id) {
		Session session = HibernateUtil.getSession();
		String hql = "from Revision where id = (" +
				"select min(id) from Revision " +
				"where resource=(select resource from Revision where id=:revId) " +
					"and id>:revId)";
		Query query = session.createQuery(hql);
		query.setLong("revId", id);
		return (Revision<T>) query.uniqueResult();
	}

	public static <T extends Article> Revision<T> fetch(long id,
			Class<T> articleType) {
		Revision<T> revision = fetch(id);
		if (revision == null
				|| !articleType.isAssignableFrom(revision.getResource()
						.getType())) {
			return null;
		}
		return revision;
	}

	public static long count() {
		String hql = "select count(*) from Revision";
		return HibernateUtil.count(hql);
	}

}
