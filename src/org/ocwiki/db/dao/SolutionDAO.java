package org.ocwiki.db.dao;

import java.util.List;

import org.ocwiki.data.Resource;
import org.ocwiki.data.Solution;
import org.ocwiki.persistence.HibernateUtil;

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

	public static List<Resource<Solution>> fetchByQuestion(long questionId,
			int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in "
				+ "(from Solution where question.id = :quesId)";
		Query query = session.createQuery(hql);
		query.setLong("quesId", questionId);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}
}
