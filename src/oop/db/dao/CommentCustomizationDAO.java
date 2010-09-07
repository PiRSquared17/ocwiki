package oop.db.dao;

import java.util.List;

import oop.data.CommentCustomization;
import oop.persistence.HibernateUtil;

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
				"order by comment.timestamp asc";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setLong("userId", userId);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
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
				"order by comment.timestamp asc";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		query.setLong("userId", userId);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
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
