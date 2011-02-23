package org.ocwiki.db.dao.stat;

import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;
import org.ocwiki.data.stat.ResourceViewCounter;
import org.ocwiki.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ResourceViewCounterDAO {

	@SuppressWarnings("unchecked")
	public static ResourceViewCounter fetch(long resourceId) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			String hql = "from ResourceViewCounter where resource.id=:resId";
			Query query = session.createQuery(hql);
			query.setLong("resId", resourceId);
			ResourceViewCounter counter = (ResourceViewCounter) query
					.uniqueResult();

			if (counter == null) {
				counter = new ResourceViewCounter(
						(Resource<? extends Article>) session.load(
								Resource.class, resourceId));
				tx = session.beginTransaction();
				session.save(counter);
				tx.commit();
			}
			return counter;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}

	public static ResourceViewCounter incrementAndFetch(long resourceId) {
		Session session = HibernateUtil.getSession();
		Query query = session.getNamedQuery("incrementResourceViewCounter");
		query.setLong("resource_id", resourceId);
		return (ResourceViewCounter) query.uniqueResult();
	}

}
