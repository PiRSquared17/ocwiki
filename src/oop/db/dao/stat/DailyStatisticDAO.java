package oop.db.dao.stat;

import java.util.Date;

import oop.data.stat.DailyStatistic;
import oop.db.dao.ResourceDAO;
import oop.db.dao.RevisionDAO;
import oop.db.dao.UserDAO;
import oop.persistence.HibernateUtil;
import oop.util.SiteViewCountUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DailyStatisticDAO {

	public static DailyStatistic fetch(Date date) {
		try {
			Session session = HibernateUtil.getSession();
			String hql = "from DailyStatistic where date=:date";
			Query query = session.createQuery(hql);
			query.setDate(":date", date);
			return (DailyStatistic) query.uniqueResult();
		} catch (HibernateException ex) {
			return new DailyStatistic();
		}
	}
	
	public static DailyStatistic fetchLastStatistic() {
		try {
			Session session = HibernateUtil.getSession();
			String hql = "from DailyStatistic order by date desc";
			Query query = session.createQuery(hql);
			query.setMaxResults(1);
			return (DailyStatistic) query.uniqueResult();
		} catch (HibernateException ex) {
			return new DailyStatistic();
		}
	}
	
	public static DailyStatistic saveCurrentStatistic() {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			DailyStatistic stat = new DailyStatistic(new Date(),
					SiteViewCountUtil.get(), RevisionDAO.count(),
					ResourceDAO.count(), UserDAO.count());
		
			tx = session.beginTransaction();
			session.saveOrUpdate(stat);
			tx.commit();
			
			return stat;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}
	
}
