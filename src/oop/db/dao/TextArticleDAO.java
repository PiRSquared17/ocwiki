package oop.db.dao;

import java.util.List;

import oop.data.Resource;
import oop.data.TextArticle;
import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class TextArticleDAO {

	public static List<Resource<TextArticle>> fetchByTopicId(long Topicid, int start,
			int length){
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where artical in (from TextArtical" +
				"where :topic in elements(topics))" + 
				"order by id desc";
		Query query = session.createQuery(hql);
		Object topic = session.load(Resource.class, Topicid);
		query.setEntity("topic", topic);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();			
	}

	public static Resource<TextArticle> fetchById(long id){
		return ResourceDAO.fetchById(id, TextArticle.class);
	}
	
	public static List<Resource<TextArticle>> fetch(int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in (from TextArticle) " +
				"and status <> 'DELETED'";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}
	
	public static long count() {
		String hql = "select count(*) from Resource " + 
				"where article in (from TextArticle) and status <> 'DELETED'";
		return HibernateUtil.count(hql);
	}

}
