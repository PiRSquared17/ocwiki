package org.ocwiki.db.dao.stat;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ocwiki.data.stat.DailyStatistic;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.db.dao.RevisionDAO;
import org.ocwiki.db.dao.UserDAO;
import org.ocwiki.persistence.HibernateUtil;

public class DailyStatisticDAO {

	/**
	 * Đảm bảo luôn trả về một đối tượng khác null.
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<DailyStatistic> fetch(Date... dates) {
		Session session = HibernateUtil.getSession();
		String hql = "from DailyStatistic where date in (:dates) "
				+ "order by date desc";
		Query query = session.createQuery(hql);
		query.setParameterList("dates", dates);
		return query.list();
	}

	/**
	 * Đảm bảo luôn trả về một đối tượng khác null.
	 * 
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
					SiteViewCounter.get(), RevisionDAO.count(),
					ResourceDAO.count(), UserDAO.count());

			tx = session.beginTransaction();
			session.merge(stat);
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
