package org.ocwiki.db.dao;

import java.util.List;

import org.ocwiki.data.Resource;
import org.ocwiki.data.TextArticle;
import org.ocwiki.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class TextArticleDAO {

	public static List<Resource<TextArticle>> listOrderByName(int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource where article in (from TextArticle) " +
				"and status='NORMAL' " +
				"order by article.name asc";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return (List<Resource<TextArticle>>) query.list();
	}

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
	
	public static List<Resource<TextArticle>> fetchNewest(int start, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Resource " +
				"where article in (from TextArticle) and status='NORMAL' " +
				"order by id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}
	
	public static long count() {
		String hql = "select count(*) from Resource " + 
				"where article in (from TextArticle) and status='NORMAL'";
		return HibernateUtil.count(hql);
	}

	public static void persist(TextArticle textarticle) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(textarticle);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}
	
}
