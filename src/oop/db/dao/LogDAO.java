package oop.db.dao;

import java.util.List;

import oop.data.log.ResourceLog;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class LogDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static ResourceLog fetchById(long id) throws Exception {
		Session session = HibernateUtil.getSession();
		return (ResourceLog) session.get(ResourceLog.class, id);
	}
	
	public static List<ResourceLog> fetchByResource(long id, int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Log where resource.id=:id";
		Query query = session.createQuery(hql);
		query.setLong("id", id);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}

	public static ResourceLog save(ResourceLog change) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(change);
			tx.commit();
			return change;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}
	
}
