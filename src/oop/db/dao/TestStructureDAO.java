package oop.db.dao;

import java.util.List;

import oop.data.Resource;
import oop.data.TestStructure;
import oop.data.Topic;
import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class TestStructureDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static Resource<TestStructure> fetchById(long id) {
		return ResourceDAO.fetchById(id, TestStructure.class);
	}
	
	public static List<Resource<TestStructure>> fetch(int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where " +
				"article in (from TestStructure) order by id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	public static long count() {
		String hql = "SELECT COUNT(*) from Resource where " +
				"article in (FROM TestStructure)";
		return HibernateUtil.count(hql);
	}
	
	public static List<Resource<TestStructure>> fetchByTopic(long topicId, int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from TestStructure where :topic in elements (topics))";
		Query query = session.createQuery(hql);
		query.setEntity("topic", session.load(Topic.class, topicId));
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

	public static long countByTopic(long topicId) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from TestStructure where :topic in elements(topics))";
		Query query = session.createQuery(hql);
		query.setEntity("topic", session.load(Topic.class, topicId));
		return (Long) query.uniqueResult();
	}
	
	public static List<Resource<TestStructure>> fetchByAuthor(long authorId, int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from TestStructure) and author.id=:authorId";
		Query query = session.createQuery(hql);
		query.setLong("authorId", authorId);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

	public static long countByAuthor(long authorId) {
		String sql = "SELECT COUNT(*) from Resource where article in " +
				"(FROM TestStructure) WHERE author.id=" + authorId;
		return HibernateUtil.count(sql);
	}

	public static List<TestStructure> fetchByName(String name, int limit) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from TestStructure where name like :name)";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		query.setMaxResults(limit);
		return query.list();
	}

}
