package org.ocwiki.db.dao;

import java.util.List;

import org.ocwiki.data.CommentCustomization;
import org.ocwiki.data.CommentStatus;
import org.ocwiki.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public final class CommentCustomizationDAO {
	
	public static List<CommentCustomization> fetchByUser(long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "from CommentCustomization where user.id=:userId";
		Query query = session.createQuery(hql);
		query.setLong("userId", userId);
		return query.list();
	}

	public static List<CommentCustomization> fetchByResourceAndUser(
			long resourceId, long userId, int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from CommentCustomization " +
				"where comment.resource.id = :resId " +
				"and user.id = :userId " +
				"order by comment.id asc";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setLong("userId", userId);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}
	
	public static long countByResourceAndUser(long resourceId, long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from CommentCustomization " +
				"where comment.resource.id = :resId " +
				"and user.id = :userId";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setLong("userId", userId);
		return (Long)query.uniqueResult();
	}

	/**
	 * Đếm số lần thảo luận của một người dùng được thích (status=LIKE) hoặc
	 * bị ẩn (status=HIDDEN).
	 * @param authorId
	 * @param status
	 * @return
	 */
	public static long countByCommentAuthorAndStatus(long authorId, CommentStatus status) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from CommentCustomization "
				+ "where comment.user.id=:authorId and status=:status";
		Query query = session.createQuery(hql);
		query.setLong("authorId", authorId);
		query.setString("status", status.name());
		return (Long) query.uniqueResult();
	}
	
	public static long countByCommentAuthor(long authorId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from CommentCustomization "
				+ "where comment.user.id=:authorId";
		Query query = session.createQuery(hql);
		query.setLong("authorId", authorId);
		return (Long) query.uniqueResult();
	}
	
	public static CommentCustomization persist(
			CommentCustomization customization) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate("CommentCustomization", customization);
			tx.commit();
			return customization;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}
	
}
