package oop.db.dao;

import java.util.ArrayList;
import java.util.List;

import oop.data.Namespace;
import oop.data.Resource;
import oop.data.Topic;
import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

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

	/**
	 * Lấy các chủ đề chưa được phân loại
	 * @return
	 */
	public static List<Resource<Topic>> fetchUncategorized() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Resource where article in (" +
				"from Topic where parent is null) and id <> " + Topic.ROOT_ID + 
				" and status <> 'DELETED'");
		return query.list();
	}
	
	/**
	 * Lấy các chủ đề chưa dùng đến
	 * @return
	 */
	public static List<Resource<Topic>> fetchUnused() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Resource r where r not in (" +
				"select elements(topics) from CategorizableArticle a " +
					"where a in (select article from Resource where status <> 'DELETED') ) " +
				"and r not in (select parent from Topic t " +
					"where t in (select article from Resource where status <> 'DELETED') ) " +
				"and status <> 'DELETED'");
		return query.list();
	}

	public static List<Resource<Topic>> getAncestors(long resourceId) {
		return fetchAncestorsNestedSetImpl(resourceId);
	}

	private static List<Resource<Topic>> fetchAncestorsNestedSetImpl(long resourceId) {
		Session session = HibernateUtil.getSession();
		String hql = "select s.resource from TopicSet s where " +
				"s.leftIndex <= (select leftIndex from TopicSet where resource.id=:resId) and " +
				"s.rightIndex >= (select rightIndex from TopicSet where resource.id=:resId) " +
				"order by s.rightIndex asc";
		Query query = session.createQuery(hql);
		query.setLong("resId", resourceId);
		return query.list();
	}
	
	private static List<Resource<Topic>> fetchAncestorsRecursiveImpl(
			long resourceId) {
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
