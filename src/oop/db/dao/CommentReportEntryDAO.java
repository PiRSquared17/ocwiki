package oop.db.dao;

import java.util.List;

import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

public class CommentReportEntryDAO {

	public List<CommentReportEntryDAO> fetchByResource(long resourceId,
			int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "select new oop.data.CommentReportEntry(c.comment, c.status, 0) " +
				"from CommentCustomizatiionWithDefault c " +
				"where c.comment.resource.id = :resId " +
				"order by c.comment.id asc";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}

}
