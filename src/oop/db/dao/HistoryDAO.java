package oop.db.dao;

import java.util.Date;
import java.util.List;

import oop.data.Article;
import oop.data.History;
import oop.data.Test;
import oop.data.User;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public final class HistoryDAO {

	public static long countByUser(long userId) {
		String hql = "select count(*) from History where user.id=:userId";
		return HibernateUtil.count(hql);
	}

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
		Query query = session.createQuery("from History where user.id=:userId");
		query.setLong("userId", userId);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

	public static History create(long userId, long testId, double mark,
			int time) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = (User) session.load(User.class, userId);
			Article test = (Article) session.load(Test.class, testId);
			History history = new History(user, test, new Date(), mark, time);
			session.save(history);
			tx.commit();
			return history;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

}
