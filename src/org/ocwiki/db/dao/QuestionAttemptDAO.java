package org.ocwiki.db.dao;

import java.util.List;

import org.ocwiki.data.QuestionAttempt;
import org.ocwiki.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class QuestionAttemptDAO {

	public static List<QuestionAttempt> fetchByQuestionAndUser(long resourceId,
			long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "from QuestionAttempt where question.id=:resId and user.id=:userId";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setLong("userId", userId);
		return query.list();
	}

	public static long countByQuestionAndUser(long resourceId,
			long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from QuestionAttempt " +
				"where question.id=:resId and user.id=:userId";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setLong("userId", userId);
		return (Long)query.uniqueResult();
	}

	public static void persist(QuestionAttempt userAnswer) {
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
