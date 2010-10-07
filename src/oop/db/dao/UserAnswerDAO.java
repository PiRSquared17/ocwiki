package oop.db.dao;

import java.util.List;

import oop.data.UserAnswer;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class UserAnswerDAO {

	public static List<UserAnswer> fetchByQuestionAndUser(long resourceId,
			long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "from UserAnswer where question.id=:resId and user.id=:userId";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setLong("userId", userId);
		return query.list();
	}

	public static void persist(UserAnswer userAnswer) {
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
