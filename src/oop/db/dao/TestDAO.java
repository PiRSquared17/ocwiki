package oop.db.dao;

import java.util.List;

import oop.data.Resource;
import oop.data.Test;
import oop.data.User;
import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class TestDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static Resource<Test> fetchById(long id) {
		return ResourceDAO.fetchById(id, Test.class);
	}
	
	public static Resource<Test> fetchByName(String name) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from Test where name=:name) and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		return (Resource<Test>) query.uniqueResult();
	}

	public static List<Resource<Test>> fetchByNameLike(String name, int limit) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from Test where name like :name) and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		query.setMaxResults(limit);
		return query.list();
	}

	public static long count() {
		String hql = "select count(*) from Resource where article in " +
				"(from Test) and status <> 'DELETED'";
		return HibernateUtil.count(hql);
	}
	
	public static List<Resource<Test>> fetch(int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from Test) and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	public static long countByTopic(long id) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from Resource where article in " +
				"(from Test where :topic in elements(topics)) " +
				"and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setEntity("topic", session.load(Resource.class, id));
		return (Long) query.uniqueResult();
	}

	public static List<Resource<Test>> fetchByTopic(long id, int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from Test where :topic in elements(topics)) " +
				"and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setEntity("topic", session.load(Resource.class, id));
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	public static long countByAuthor(long id) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(*) from Resource where article in " +
				"(from Test where author=:author) and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setEntity("author", session.load(User.class, id));
		return (Long) query.uniqueResult();
	}

	public static List<Resource<Test>> fetchByAuthor(long id, int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from Test where author=:author) and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setEntity("author", session.load(User.class, id));
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

	public static List<Resource<Test>> fetchByContainedQuestion(
			long baseQuestionResourceId, int start, int length) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from Test as test " +
					"inner join test.sections as section " +
					"inner join section.questions as question " +
				"with question.baseResource.id = :qid)";
		Query query = session.createQuery(hql);
		query.setLong(":qid", baseQuestionResourceId);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

}
