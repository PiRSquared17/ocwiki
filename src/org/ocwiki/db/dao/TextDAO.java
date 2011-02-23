package org.ocwiki.db.dao;

import org.ocwiki.data.Text;
import org.ocwiki.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TextDAO {

	public static Text fetch(long id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Text where id=:id");
		query.setLong("id", id);
		return (Text) query.uniqueResult();
	}

	public static Text create(String content) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Text text = new Text(content);
			session.save(text);
			tx.commit();
			return text;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}

}
