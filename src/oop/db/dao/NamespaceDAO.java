package oop.db.dao;

import org.hibernate.Session;

import oop.data.Namespace;
import oop.persistence.HibernateUtil;

public class NamespaceDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static Namespace fetchById(long id) {
		Session session = HibernateUtil.getSession();
		return (Namespace) session.get(Namespace.class, id);
	}
	
}
