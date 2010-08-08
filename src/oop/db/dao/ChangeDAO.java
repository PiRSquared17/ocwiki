package oop.db.dao;

import java.util.List;

import oop.change.Change;
import oop.data.Article;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class ChangeDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static Change fetchById(long id) throws Exception {
		Session session = HibernateUtil.getSession();
		return (Change) session.get(Change.class, id);
	}
	
	public static List<Change> fetchByArticle(Article article) {
		Session session = HibernateUtil.getSession();
		String hql = "from Change where namespaceId=:ns and articleId=:ar";
		Query query = session.createQuery(hql);
		return query.list();
	}

	public static Change save(Change change) {
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
