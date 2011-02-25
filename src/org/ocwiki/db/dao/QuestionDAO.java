package org.ocwiki.db.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.ocwiki.data.Question;
import org.ocwiki.data.Resource;
import org.ocwiki.persistence.HibernateUtil;

public class QuestionDAO {

	public static List<Resource<Question>> fetch(int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in (from Question) " +
				"and status = 'NORMAL' " +
				"order by id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

}
