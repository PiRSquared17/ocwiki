package oop.db.dao;

import oop.data.Article;
import oop.data.Resource;
import oop.data.Revision;
import oop.persistence.HibernateUtil;

import org.hibernate.Session;

public class RevisionDAO {

	@SuppressWarnings("unchecked")
	public static <T extends Article> Revision<T> fetch(Resource<T> resource,
			int id) {
		Session session = HibernateUtil.getSession();
		return (Revision<T>) session.get(Revision.class, id);
	}

}
