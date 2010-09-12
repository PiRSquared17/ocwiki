package oop.db.dao;

import oop.data.Article;
import oop.data.Resource;
import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class ArticleDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param resourceId
	 * @return
	 */
	public static Resource<? extends Article> fetchById(long resourceId) {
		Session session = HibernateUtil.getSession();
		return (Resource<? extends Article>) session.get(Resource.class, resourceId);
	}

	public static Resource<? extends Article> fetchByName(String name) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Resource where article.name=:name");
		query.setString("name", name);
		return (Resource<? extends Article>) query.uniqueResult();
	}

}
