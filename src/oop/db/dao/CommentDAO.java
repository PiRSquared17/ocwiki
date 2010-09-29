package oop.db.dao;

import java.util.List;

import oop.data.Comment;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public final class CommentDAO {

	private CommentDAO() {
	}

	public static long countByResource(long resourceId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from Comment where resource.id=:resId";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		return (Long) query.uniqueResult();
	}

	public static long countByAuthor(long authorId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from Comment where user.id=:auId";
		Query query = session.createQuery(hql);
		query.setLong("auId", authorId);
		return (Long) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Comment> fetchLatest(long resourceId, int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Comment where resource.id=:res order by timestamp asc";
		Query query = session.createQuery(hql);
		query.setLong("res", resourceId);
		long count = countByResource(resourceId);
		query.setFirstResult((int) (count - start - size));
		query.setMaxResults(size);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Comment> fetch(long resourceId, int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Comment where resource.id=:res order by timestamp asc";
		Query query = session.createQuery(hql);
		query.setLong("res", resourceId);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}

	public static void persist(Comment comment) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(comment);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}

	public static Comment fetch(long id) {
		Session session = HibernateUtil.getSession();
		return (Comment) session.get(Comment.class, id);
	}

}
