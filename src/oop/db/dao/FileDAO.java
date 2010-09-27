package oop.db.dao;

import oop.data.File;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FileDAO {

	public static File fetchById(long id) throws Exception {
		Session session = HibernateUtil.getSession();
		return (File) session.get(File.class, id);
	}
	
	public static void persist(File uploadedFile) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(uploadedFile);
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
