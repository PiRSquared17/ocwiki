package oop.db.dao;

import java.util.Date;
import java.util.List;

import oop.data.Article;
import oop.data.Status;
import oop.data.Test;
import oop.data.Text;
import oop.data.Topic;
import oop.data.User;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class TestDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static Test fetchById(long id) {
		Session session = HibernateUtil.getSession();
		return (Test) session.get(Test.class, id);
	}
	
	public static Article fetchByName(String name) {
		Session session = HibernateUtil.getSession();
		String hql = "from Test where name=:name and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		return (Article) query.uniqueResult();
	}

	public static List<Test> fetchByNameLike(String name, int limit) {
		Session session = HibernateUtil.getSession();
		String hql = "from Test where name like :name and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		query.setMaxResults(limit);
		return query.list();
	}

	public static long count() {
		String hql = "SELECT COUNT(*) FROM Test " +
				"where status <> 'DELETED'";
		return HibernateUtil.count(hql);
	}
	
	public static List<Test> fetch(int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Test where status <> 'DELETED' order by id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	public static List<Test> fetchCreateDateOrder(int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Test where status <> 'DELETED' order by id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	public static long countByTopic(long id) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from Test " +
				"where topic=:topic and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setEntity("topic", session.load(Topic.class, id));
		return (Long) query.uniqueResult();
	}

	public static List<Test> fetchByTopic(long id, int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Test where topic=:topic and status <> 'DELETED' " +
				"order by id desc";
		Query query = session.createQuery(hql);
		query.setEntity("topic", session.load(Topic.class, id));
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	public static long countByAuthor(long id) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from Test " +
				"where author=:author and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setEntity("author", session.load(User.class, id));
		return (Long) query.uniqueResult();
	}

	public static List<Test> fetchByAuthor(long id, int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Test where author=:author status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setEntity("author", session.load(User.class, id));
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

	public static Test create(String name, String contentStr, long authorId,
			String type, int time) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			User author = (User) session.load(User.class, authorId);
			Text content = new Text(contentStr);
			Test test = new Test(name, content, new Date(), author, type,
					time);

			tx = session.beginTransaction();
			session.save(content);
			session.save(test);
			tx.commit();
			return test;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}
	
	public void setDeleted(long testId, boolean deleted) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			Article test = (Article) session.load(Test.class, testId);
			tx = session.beginTransaction();
			test.setStatus(Status.DELETED);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}
	
	@Deprecated
	public static void persist(Article test) {
	}

}
