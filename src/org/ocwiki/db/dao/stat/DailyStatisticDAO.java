package org.ocwiki.db.dao.stat;

import java.util.Date;

import org.ocwiki.data.stat.DailyStatistic;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.db.dao.RevisionDAO;
import org.ocwiki.db.dao.UserDAO;
import org.ocwiki.persistence.HibernateUtil;
import org.ocwiki.util.SiteViewCountUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DailyStatisticDAO {

	/**
	 * Đảm bảo luôn trả về một đối tượng khác null.
	 * @param date
	 * @return
	 */
	public static DailyStatistic fetch(Date date) {
		try {
			Session session = HibernateUtil.getSession();
			String hql = "from DailyStatistic where date=:date";
			Query query = session.createQuery(hql);
			query.setDate("date", date);
			DailyStatistic value = (DailyStatistic) query.uniqueResult();
			if (value == null) {
				return new DailyStatistic();
			}
			return value;
		} catch (HibernateException ex) {
			return new DailyStatistic();
		}
	}
	
	/**
	 * Đảm bảo luôn trả về một đối tượng khác null.
	 * @return
	 */
	public static DailyStatistic fetchLastStatistic() {
		try {
			Session session = HibernateUtil.getSession();
			String hql = "from DailyStatistic order by date desc";
			Query query = session.createQuery(hql);
			query.setMaxResults(1);
			DailyStatistic value = (DailyStatistic) query.uniqueResult();
			if (value == null) {
				return new DailyStatistic();
			}
			return value;
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
