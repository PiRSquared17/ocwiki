package oop.db.dao;

import oop.data.Namespace;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NamespaceDAO {

	public static Namespace fetch(long id) {
		Session session = HibernateUtil.getSession();
		return (Namespace) session.get(Namespace.class, id);
	}
	
	public static Namespace create(long id, String name) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			Namespace namespace = new Namespace(id, name);
			tx = session.beginTransaction();
			session.save(namespace);
			tx.commit();
			return namespace;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}
	
}
