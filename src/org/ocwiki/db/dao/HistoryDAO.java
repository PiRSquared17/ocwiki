package org.ocwiki.db.dao;

import java.util.List;

import org.ocwiki.data.TestAttempt;
import org.ocwiki.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public final class HistoryDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static TestAttempt fetchById(long id) {
		Session session = HibernateUtil.getSession();
		return (TestAttempt) session.get(TestAttempt.class, id);
	}

	public static List<TestAttempt> fetchByUser(long userId, int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from TestAttempt where user.id=:userId order by id desc";
		Query query = session.createQuery(hql);
		query.setLong("userId", userId);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	public static long countByUser(long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from TestAttempt where user.id=:userId";
		Query query = session.createQuery(hql);
		query.setLong("userId", userId);
		return (Long)query.uniqueResult();
	}

	public static TestAttempt persist(TestAttempt history) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(history);
			tx.commit();
			return history;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}

}
