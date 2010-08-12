package oop.db.dao;

import java.util.List;

import oop.data.Topic;
import oop.data.User;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public final class TopicDAO {

	public static Topic fetchByName(String name) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Topic where name=:name");
		query.setString("name", name);
		return (Topic) query.uniqueResult();
	}

	public static List<Topic> fetchByNameLike(String name) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Topic where name like :name");
		query.setString("name", name);
		return query.list();
	}

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static Topic fetchById(long topicId) {
		Session session = HibernateUtil.getSession();
		return (Topic) session.get(Topic.class, topicId);
	}

	public static List<Topic> fetchTopLevels() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Topic where parent is null");
		return query.list();
	}

	public static Topic create(String name, Topic parent, User author) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Topic newTopic = new Topic(name, parent, author);
			session.save(newTopic);
			tx.commit();
			return newTopic;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

	@Deprecated
	public static void persist(Topic topic) {
		// DO NOTHING
	}

	public static int drop(Topic topic) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(topic);
			tx.commit();
			return 1;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

	public static long count() {
		String hql = "SELECT COUNT(*) FROM Topic";
		return HibernateUtil.count(hql);
	}

}
