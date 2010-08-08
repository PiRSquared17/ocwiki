package oop.db.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import oop.data.Article;
import oop.data.BaseQuestion;
import oop.data.Text;
import oop.data.Topic;
import oop.data.User;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class BaseQuestionDAO {

	public static List<BaseQuestion> fetchByTopic(long topicId, int start,
			int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from BaseQuestion " +
				"where topic=:topic and deleted=0 " +
				"order by id desc";
		Query query = session.createQuery(hql);
		Object topic = session.load(Topic.class, topicId);
		query.setEntity("topic", topic);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

	public static List<BaseQuestion> fetchByAuthor(long authorId, int start,
			int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from BaseQuestion " +
				"where author=:author and deleted=0" +
				"order by id desc";
		Query query = session.createQuery(hql);
		Object author = session.load(Topic.class, authorId);
		query.setEntity("author", author);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

	public static List<BaseQuestion> fetch(int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from BaseQuestion where deleted=0";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static BaseQuestion fetchById(long id) {
		Session session = HibernateUtil.getSession();
		return (BaseQuestion) session.get(BaseQuestion.class, id);
	}

	public static long countByAuthor(long authorId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from BaseQuestion " +
				"where author=:author and deleted=0";
		Query query = session.createQuery(hql);
		Object author = session.load(Topic.class, authorId);
		query.setEntity("author", author);
		return (Long) query.uniqueResult();
	}

	public static long countByTopic(long topicId) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from BaseQuestion " +
				"where topic=:topic and deleted=0";
		Query query = session.createQuery(hql);
		Object topic = session.load(Topic.class, topicId);
		query.setEntity("topic", topic);
		return (Long) query.uniqueResult();
	}

	public static BaseQuestion create(String contentStr, int level, long authorId) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			User author = (User) session.load(User.class, authorId);
			Text content = new Text(contentStr);
			BaseQuestion baseQuestion = new BaseQuestion(content, level, author,
					new Date());

			tx = session.beginTransaction();
			session.save(content);
			session.save(baseQuestion);
			tx.commit();
			return baseQuestion;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}
	
	public static List<BaseQuestion> fetchByContent(String content, int limit) {
		Session session = HibernateUtil.getSession();
		String hql = "from BaseQuestion " + 
		"where content.text like :content and deleted=0 ";
		Query query = session.createQuery(hql);
		query.setString("content", content);
		query.setMaxResults(limit);
		return query.list();
	}

	public static void setDeleted(long questionId, boolean deleted) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			Article question = (Article) session.load(
					BaseQuestion.class, questionId); 
			tx = session.beginTransaction();
			question.setDeleted(deleted);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}
	
	@Deprecated
	public static void persist(Article question) {
	}

	/**
	 * Lấy về câu hỏi thuộc chủ đề <code>topicId</code> chưa có trong đề thi
	 * <code>testId</code>.
	 * XXX kiểm tra đề thi
	 * @param testId
	 * @param topicId
	 * @param quantity
	 * @return
	 */
	public static List<BaseQuestion> fetchRandomly(long testId,
			long topicId, int quantity) {
		Session session = HibernateUtil.getSession();
		String hql = "from BaseQuestion " +
				"where topic=:topic and deleted=0 " +
				"order by rand()";
		Query query = session.createQuery(hql);
		Topic topic = (Topic) session.load(Topic.class, topicId);
		query.setEntity("topic", topic);
		query.setMaxResults(quantity);
		return query.list();
	}

	/**
	 * Dùng cho chức năng sinh đề tự động, tạm thời bị bỏ. TODO implement again?
	 */
	public static List<BaseQuestion> fetchByInfo(long testId, int level,
			int mark, long topicId, int count) {
		return Collections.emptyList();
	}

	public static long count() {
		String sql = "SELECT COUNT(*) FROM  BaseQuestion " +
				"where deleted=0";
		return HibernateUtil.count(sql);
	}

	public static int[][][] fetchCounts(long testId, long[] topicIds,
			int[] levels) {
		/*
		 * Connection conn = null; Statement stmt = null; ResultSet rs = null;
		 * try { StringBuilder where = new StringBuilder(); StringBuilder select
		 * = new StringBuilder("SELECT "); StringBuilder groupBy = new
		 * StringBuilder(); StringBuilder orderBy = new StringBuilder(); if
		 * (topicIds[0] >= 0) { select.append("ques_topic, ");
		 * where.append("ques_topic IN ("
		 * ).append(join(topicIds)).append(") AND ");
		 * groupBy.append("ques_topic, "); orderBy.append("ques_topic ASC, "); }
		 * else { select.append(topicIds[0]).append(", "); } if (levels[0] >= 0)
		 * { select.append("ques_level, ");
		 * where.append("ques_level IN (").append
		 * (join(levels)).append(") AND "); groupBy.append("ques_level, ");
		 * orderBy.append("ques_level ASC, "); } else {
		 * select.append(levels[0]).append(", "); } if (marks[0] >= 0) {
		 * select.append("ques_mark, ");
		 * where.append("ques_mark IN (").append(join(marks)).append(") AND ");
		 * groupBy.append("ques_mark, "); orderBy.append("ques_mark ASC, "); }
		 * else { select.append(marks[0]).append(", "); } if (where.length() >
		 * 0) { where.delete(where.length()-4, where.length()).insert(0,
		 * "WHERE ").append("AND "); groupBy.delete(groupBy.length()-2,
		 * groupBy.length()).insert(0, "GROUP BY ");
		 * orderBy.delete(orderBy.length()-2, orderBy.length()).insert(0,
		 * " ORDER BY "); } where.append("ques_id NOT IN ")
		 * .append("(SELECT ques_id FROM vwTestQuestion WHERE test_id = ")
		 * .append(testId).append(")");
		 * 
		 * String sql = select + "COUNT(ques_id) " + "FROM tblQuestion " + where
		 * + groupBy + orderBy; conn = Database.get().getConnection(); stmt =
		 * conn.createStatement(); rs = stmt.executeQuery(sql);
		 * 
		 * int[][][] c = new int[topicIds.length][levels.length][marks.length];
		 * while (rs.next()) { long topic = rs.getLong(1); int level =
		 * rs.getInt(2); int mark = rs.getInt(3); int count = rs.getInt(4);
		 * 
		 * int i = Arrays.binarySearch(topicIds, topic); int j =
		 * Arrays.binarySearch(levels, level); int k =
		 * Arrays.binarySearch(marks, mark); c[i][j][k] = count; } return c; }
		 * finally { if (rs != null) rs.close(); if (stmt != null) stmt.close();
		 * if (conn != null) conn.close(); }
		 */
		return new int[][][] {};
	}

}
