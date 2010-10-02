package oop.db.dao;

import oop.data.ResourceCustomization;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ResourceCustomizationDAO {

	public static ResourceCustomization fetchByResourceAndUser(
			long resourceId, long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "from ResourceCustomization " +
				"where resource.id = :resId " +
				"and user.id = :userId";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setLong("userId", userId);
		return (ResourceCustomization) query.uniqueResult();
	}
	

	public static ResourceCustomization persist(
			ResourceCustomization customization) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(customization);
			tx.commit();
			return customization;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}
	
}
