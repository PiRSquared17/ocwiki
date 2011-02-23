package org.ocwiki.db.dao;

import org.ocwiki.data.ChoiceAnswer;
import org.ocwiki.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ChoiceAnswerDAO {

	public static void persist(ChoiceAnswer answer) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(answer);
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
