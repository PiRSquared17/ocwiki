package oop.db.dao;

import java.util.ArrayList;
import java.util.List;

import oop.data.Namespace;
import oop.data.Resource;
import oop.data.Topic;
import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

//XXX
@SuppressWarnings("unchecked")
public final class TopicDAO {

	public static Resource<Topic> fetchByName(String name) {
		return ResourceDAO.fetchByQualifiedName(NamespaceDAO
				.fetch(Namespace.TOPIC), name, Topic.class);
	}

	public static List<Resource<Topic>> fetchByNameLike(String name) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from Topic where name like :name)";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		return query.list();
	}

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static Resource<Topic> fetchById(long id) {
		return ResourceDAO.fetchById(id, Topic.class);
	}

	public static List<Resource<Topic>> fetchTopLevels() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Resource where article in (" +
				"from Topic where parent is null) and status <> 'DELETED'");
		return query.list();
	}

	public static List<Resource<Topic>> getAncestors(long resourceId) {
		Resource<Topic> topic = (Resource<Topic>) HibernateUtil.getSession()
				.load(Resource.class, resourceId);
		ArrayList<Resource<Topic>> ancestorList = new ArrayList<Resource<Topic>>();
		addAncestors(ancestorList, topic);
		return ancestorList;
	}
	
	private static void addAncestors(List<Resource<Topic>> ancestorList,
			Resource<Topic> topic) {
		if (topic == null) {
			return;
		}
		ancestorList.add(topic);
		addAncestors(ancestorList, topic.getArticle().getParent());
	}

	public static List<Resource<Topic>> fetchChildren(long resourceId) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in " +
				"(from Topic where parent.id=:resId)";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		return query.list();
	}
	
	public static long count() {
		String hql = "SELECT COUNT(*) FROM Topic";
		return HibernateUtil.count(hql);
	}

}
