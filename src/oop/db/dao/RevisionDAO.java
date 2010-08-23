package oop.db.dao;

import java.util.List;

import oop.data.Article;
import oop.data.Revision;
import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class RevisionDAO {

	public static <T extends Article> Revision<T> fetch(int id) {
		Session session = HibernateUtil.getSession();
		return (Revision<T>) session.get(Revision.class, id);
	}

	public static List<Revision<Article>> fetchByResource(long resourceId,
			long start, int size) {
		Session session = HibernateUtil.getSession();
		Query query = session
				.createQuery("from Revision where resource.id=:resId");
		query.setLong("resId", resourceId);
		query.setLong("start", start);
		query.setInteger("size", size);
		return query.list();
	}

}
