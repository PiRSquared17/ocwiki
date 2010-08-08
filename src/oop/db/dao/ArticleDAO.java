package oop.db.dao;

import oop.data.Article;
import oop.data.Namespace;
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

	public static Article fetchByName(String qualifiedName) {
		int colonIndex = qualifiedName.indexOf(':');
		if (colonIndex < 0) {
			// main namespace
			return null;
		} else {
			String namespace = qualifiedName.substring(0, colonIndex);
			String name = qualifiedName.substring(colonIndex + 1);
			if (Namespace.QUESTION.getName().equalsIgnoreCase(namespace)) {
				try {
					return BaseQuestionDAO.fetchById(Long.parseLong(name));
				} catch (NumberFormatException ex) {
					return null;
				}
			}
			if (Namespace.TEST.getName().equalsIgnoreCase(namespace)) {
				return TestDAO.fetchByName(name);
			}
		}
		return null;
	}

}
