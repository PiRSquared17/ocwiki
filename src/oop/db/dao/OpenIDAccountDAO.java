package oop.db.dao;

import java.util.List;

import oop.data.OpenIDAccount;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OpenIDAccountDAO {

	public static OpenIDAccount fetchByUrl(String url) {	
		Session session = HibernateUtil.getSession();
		String hql = "from OpenIDAccount where url=:url";
		Query query = session.createQuery(hql);
		query.setString("url", url);
		return (OpenIDAccount) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public static List<OpenIDAccount> fetchByUser(long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "from OpenIDAccount where user.id=:userId";
		Query query = session.createQuery(hql);
		query.setLong("userId", userId);
		return query.list();
	}
	
	public static void persist(OpenIDAccount openIDAccount) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(openIDAccount);
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
