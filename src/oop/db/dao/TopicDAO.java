package oop.db.dao;

import java.util.Date;
import java.util.List;

import oop.data.Resource;
import oop.data.Status;
import oop.data.Text;
import oop.data.Topic;
import oop.data.User;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

//XXX
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
	public static Resource<Topic> fetchById(long id) {
		return ResourceDAO.fetchById(id, Topic.class);
	}

	public static List<Resource<Topic>> fetchTopLevels() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Resource where article in (" +
				"from Topic where parent is null) and status <> 'DELETED'");
		return query.list();
	}

	public static Resource<Topic> create(String name, String contentStr,
			Resource<Topic> parent, User author) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			Topic newTopic = new Topic(name, parent, new Text(contentStr));
			Resource<Topic> resource = new Resource<Topic>(new Date(), author,
					Status.NORMAL, Topic.class, newTopic);
			tx = session.beginTransaction();
			session.save(resource);
			tx.commit();
			return resource;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}

	public static long count() {
		String hql = "SELECT COUNT(*) FROM Topic";
		return HibernateUtil.count(hql);
	}

}
