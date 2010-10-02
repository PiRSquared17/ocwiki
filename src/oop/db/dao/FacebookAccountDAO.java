package oop.db.dao;

import java.util.List;

import oop.data.FacebookAccount;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FacebookAccountDAO {

	public FacebookAccount fetchByUid(long uid) {	
		Session session = HibernateUtil.getSession();
		String hql = "from FacebookAccount where uid=:uid";
		Query query = session.createQuery(hql);
		query.setLong("uid", uid);
		return (FacebookAccount) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<FacebookAccount> fetchByUser(long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "from FacebookAccount where user.id=:userId";
		Query query = session.createQuery(hql);
		query.setLong("userId", userId);
		return query.list();
	}
	
	public void persist(FacebookAccount facebookAccount) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(facebookAccount);
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
