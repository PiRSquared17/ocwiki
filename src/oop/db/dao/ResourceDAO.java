package oop.db.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import oop.data.Article;
import oop.data.Resource;
import oop.data.Revision;
import oop.data.Status;
import oop.data.User;
import oop.data.log.ResourceLog;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class ResourceDAO {

	public static <T extends Article> Resource<T> fetchById(long id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Resource where id=:id");
		query.setLong("id", id);
		return (Resource<T>) query.uniqueResult();
	}
	
	public static <T extends Article> Resource<T> fetchById(long id, Class<T> type) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Resource where id=:id");
		query.setLong("id", id);
		Resource<T> resource = (Resource<T>) query.uniqueResult();
		if (resource != null && type.isAssignableFrom(resource.getType())) {
			return null;
		}
		return resource;
	}
	
	public static <T extends Article> List<Resource<T>> fetchByNamespace(
			long namespaceId, int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource " +
				"where article.namespace.id=:ns and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setLong("ns", namespaceId);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}

	public static long countByNamespace(long namespaceId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from Resource " +
				"where article.namespace.id=:ns and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setLong("ns", namespaceId);
		return (Long) query.uniqueResult();
	}

	public static <T extends Article> Resource<T> create(User author,
			Class<T> type, T article) {
		if (!type.isInstance(article)) {
			throw new ClassCastException();
		}

		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			Date date = new Date();
			Resource<T> resource = new Resource<T>(0, date, author,
					Status.NORMAL, 0, type, article,
					new HashSet<Revision<T>>(), new HashSet<ResourceLog>());
			Revision<T> revision = new Revision<T>(0, resource, article,
					author, date, "Khởi tạo đối tượng", false);
			tx = session.beginTransaction();
			session.save(article);
			session.save(resource);
			session.save(revision);
			tx.commit();
			return resource;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}
	
	public static <T extends Article> Revision<T> update(Resource<T> resource,
			T article, User author, String summary, boolean minor) {
		if (!resource.getType().isInstance(article)) {
			throw new ClassCastException();
		}

		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			article = (T) session.merge(article);
			Revision<T> revision = new Revision<T>(0, resource, article,
					author, new Date(), summary, minor);
			session.save(revision);
			resource.setArticle(article);
			session.update(resource);
			tx.commit();
			return revision;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}
	
	public static void persist(Resource<? extends Article> resource) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(resource);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}
	
}
