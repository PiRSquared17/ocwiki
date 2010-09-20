package oop.db.dao;

import java.util.List;

import oop.data.CommentCustomization;
import oop.data.CommentStatus;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public final class CommentCustomizationDAO {

	public static long countAllCustomizationForUser(long userId,
			CommentStatus status) {
		String hql = "select count(*) from UserDefinedCommentCustomization " +
				"where comment.user.id = :userId and status = :status";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		query.setLong("userId", userId);
		query.setString("status", status.name());
		return (Long)query.uniqueResult();
	}

	public static List<CommentCustomization> fetchByUser(long userId) {
		Session session = HibernateUtil.getSession();
		String hql = "from UserDefinedCommentCustomization where user.id=:userId";
		Query query = session.createQuery(hql);
		query.setLong("userId", userId);
		return query.list();
	}

	public static List<CommentCustomization> fetchByResourceAndUser(
			long resourceId, long userId, int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from UserDefinedCommentCustomization " +
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
		String hql = "select count(*) from UserDefinedCommentCustomization " +
				"where comment.resource.id = :resId " +
				"and user.id = :userId";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setLong("userId", userId);
		return (Long)query.uniqueResult();
	}

	/**
	 * Lấy về các tuỳ biến thảo luận của người dùng trên một bài viết, sử dụng
	 * giá trị mặc định nếu người dùng chưa lựa chọn gì cho thảo luận.
	 * @param resourceId Mã số bài viết
	 * @param userId Mã số người dùng
	 * @return Danh sách các tuỳ biến thảo luận
	 */
	public static List<CommentCustomization> fetchByResourceAndUserWithDefault(
			long resourceId, long userId, int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from CommentCustomizationWithDefault " +
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

	/**
	 * Đếm các tuỳ biến thảo luận kể cả các giá trị mặc định, chính bằng số
	 * thảo luận về bài viết cho trước.
	 * @param resourceId
	 * @param userId
	 * @return
	 * @see CommentDAO#countByResource(long)
	 */
	public static long countByResourceAndUserWithDefault(long resourceId,
			long userId) {
		return CommentDAO.countByResource(resourceId);
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
		String hql = "select count(*) from UserDefinedCommentCustomization "
				+ "where comment.user.id=:authorId and status=:status";
		Query query = session.createQuery(hql);
		query.setLong("authorId", authorId);
		query.setString("status", status.name());
		return (Long) query.uniqueResult();
	}
	
	public static CommentCustomization persist(
			CommentCustomization customization) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate("UserDefinedCommentCustomization", customization);
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
