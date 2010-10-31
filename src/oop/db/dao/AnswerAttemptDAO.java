package oop.db.dao;

import java.util.List;

import oop.data.AnswerAttempt;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class AnswerAttemptDAO {

	public static List<AnswerAttempt> fetchByQuestionAndUser(long resourceId,
			long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "from AnswerAttempt where question.id=:resId and user.id=:userId";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setLong("userId", userId);
		return query.list();
	}

	public static long countByQuestionAndUser(long resourceId,
			long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from AnswerAttempt " +
				"where question.id=:resId and user.id=:userId";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setLong("userId", userId);
		return (Long)query.uniqueResult();
	}

	public static void persist(AnswerAttempt userAnswer) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(userAnswer);
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
