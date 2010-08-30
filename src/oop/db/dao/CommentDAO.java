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
	
	@SuppressWarnings("unchecked")
	public static List<Comment> fetchLatest(long resourceId, int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Comment where resource.id=:res order by timestamp desc";
		Query query = session.createQuery(hql);
		query.setLong("res", resourceId);
		query.setFirstResult(start);
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
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

	public static Comment fetch(long id) {
		Session session = HibernateUtil.getSession();
		return (Comment) session.get(Comment.class, id);
	}
	
}
