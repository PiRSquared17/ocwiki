package org.ocwiki.db.dao;

import java.util.List;

import org.ocwiki.data.Comment;
import org.ocwiki.data.CommentReport;
import org.ocwiki.data.User;
import org.ocwiki.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class CommentReportDAO {

	public static List<CommentReport> fetchByResourceAndUser(long resourceId,
			long userId, int start, int size) {
		return fetchByResourceAndUser(resourceId, UserDAO.fetchById(userId),
				start, size);
	}

	public static List<CommentReport> fetchByResourceAndUser(long resourceId,
			User user, int start, int size) {
		Session session = HibernateUtil.getSession();
		Query query;
		if (user == null) {
			String hql = "from CommentReportWithoutUser "
					+ "where comment.resource.id = :resId "
					+ "order by comment.id asc";
			query = session.createQuery(hql);
		} else {
			String hql = "from CommentReportWithUser "
					+ "where comment.resource.id = :resId and user = :user "
					+ "order by comment.id asc";
			query = session.createQuery(hql);
			query.setEntity("user", user);
		}
		query.setLong("resId", resourceId);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}

	public static List<CommentReport> fetchByResourceAndUserLatest(
			long resourceId, long userId, int start, int size) {
		return fetchByResourceAndUserLatest(resourceId, UserDAO
				.fetchById(userId), start, size);
	}

	public static List<CommentReport> fetchByResourceAndUserLatest(
			long resourceId, User user, int start, int size) {
		Session session = HibernateUtil.getSession();
		Query query;
		if (user == null) {
			String hql = "from CommentReportWithoutUser "
					+ "where comment.resource.id = :resId "
					+ "order by comment.id asc";
			query = session.createQuery(hql);
		} else {
			String hql = "from CommentReportWithUser "
					+ "where comment.resource.id = :resId and user = :user "
					+ "order by comment.id asc";
			query = session.createQuery(hql);
			query.setEntity("user", user);
		}
		long count = CommentDAO.countByResource(resourceId);
		query.setLong("resId", resourceId);
		query.setFirstResult((int) (count - start - size));
		query.setMaxResults(size);
		return query.list();
	}
	
	//Hacon tu viet
	public static CommentReport fetch(long commentId, User user) {
		Session session = HibernateUtil.getSession();
		Query query;
		String hql = "from CommentReportWithUser "
			+ "where comment.id = :crid and user = :user";
		query = session.createQuery(hql);
		query.setEntity("user", user);
		query.setLong("crid", commentId);		
		return (CommentReport) query.uniqueResult();
	}

}
