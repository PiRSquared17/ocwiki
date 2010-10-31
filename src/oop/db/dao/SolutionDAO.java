package oop.db.dao;

import oop.data.Solution;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SolutionDAO {

	public static Solution fetch(long id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Solution where id=:id");
		query.setLong("id", id);
		return (Solution) query.uniqueResult();
	}
		
	public static void persist(Solution solution) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(solution);
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
