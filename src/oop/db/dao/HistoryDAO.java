package oop.db.dao;

import java.util.List;

import oop.data.History;
import oop.persistence.HibernateUtil;

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
	public static History fetchById(long id) {
		Session session = HibernateUtil.getSession();
		return (History) session.get(History.class, id);
	}

	public static List<History> fetchByUser(long userId, int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from History where user.id=:userId order by id desc";
		Query query = session.createQuery(hql);
		query.setLong("userId", userId);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	public static long countByUser(long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from History where user.id=:userId";
		Query query = session.createQuery(hql);
		query.setLong("userId", userId);
		return (Long)query.uniqueResult();
	}

	public static History persist(History history) {
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
