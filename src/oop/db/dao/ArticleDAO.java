package oop.db.dao;

import oop.data.Article;
import oop.persistence.HibernateUtil;

import org.hibernate.Session;


public class ArticleDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static Article fetchById(long id) {
		Session session = HibernateUtil.getSession();
		return (Article) session.get(Article.class, id);
	}

	public static Article fetchByName(String name) {
		return null; //XXX
	}

}
