package oop.db.dao;

import java.util.Date;
import java.util.List;

import oop.data.TestStructure;
import oop.data.Text;
import oop.data.User;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class TestStructureDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static TestStructure fetchById(long id) {
		Session session = HibernateUtil.getSession();
		return (TestStructure) session.get(TestStructure.class, id);
	}
	
	public static List<TestStructure> fetch(int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from TestStructure order by id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	public static long count() {
		String hql = "SELECT COUNT(*) FROM TestStructure";
		return HibernateUtil.count(hql);
	}
	
	public static List<TestStructure> fetchByTopic(long topicId, int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from TestStructure where topic.id=:topicId";
		Query query = session.createQuery(hql);
		query.setLong("topicId", topicId);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

	public static long countByTopic(long topicId) {
		String sql = "SELECT COUNT(*) FROM TestStructure WHERE topic.id=" + topicId;
		return HibernateUtil.count(sql);
	}
	
	public static List<TestStructure> fetchByAuthor(long authorId, int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from TestStructure where author.id=:authorId";
		Query query = session.createQuery(hql);
		query.setLong("authorId", authorId);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

	public static long countByAuthor(long authorId) {
		String sql = "SELECT COUNT(*) FROM TestStructure " +
				"WHERE author.id=" + authorId;
		return HibernateUtil.count(sql);
	}

	@Deprecated
	public static void persist(TestStructure structure) {
	}
	
	public static void setDeleted(long id, boolean deleted) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			TestStructure testStructure = (TestStructure) session.load(
					TestStructure.class, id);
			tx = session.beginTransaction();
			testStructure.setDeleted(deleted);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

	public static TestStructure create(String name, String contentStr,
			long authorId) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			Text content = new Text(contentStr);
			User author = (User) session.load(User.class, authorId);
			TestStructure testStructure = new TestStructure(name, content,
					author, new Date());
			tx = session.beginTransaction();
			session.save(content);
			session.save(testStructure);
			tx.commit();
			return testStructure;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

	public static List<TestStructure> fetchByName(String name, int limit) {
		Session session = HibernateUtil.getSession();
		String hql = "from TestStructure where name like :name";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		query.setMaxResults(limit);
		return query.list();
	}

}
