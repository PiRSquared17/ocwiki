package oop.db.dao;

import oop.data.Solution;
import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

public class SolutionDAO {

	public static Solution fetch(long id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Solution where id=:id");
		query.setLong("id", id);
		return (Solution) query.uniqueResult();
	}

}
