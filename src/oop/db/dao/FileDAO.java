package oop.db.dao;

import java.util.List;

import oop.data.File;
import oop.data.Resource;
import oop.data.Topic;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FileDAO {

	public static Resource<File> fetchById(long id){
		return ResourceDAO.fetchById(id, File.class);
	}
	
	public static void persist(File uploadedFile) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(uploadedFile);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}
	
	/**
	 * Lấy các file chưa dùng đến
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static List<Resource<File>> fetchUnused(int start, int size) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from File f where f not in (" +
				"select elements(attachments) from BaseArticle a " +
					"where a in (select article from Resource where status <> 'DELETED'))" +
				"and f not in (select elements(embeds) from BaseArticle b " +
					"where b in (select article from Resource where status <> 'DELETED'))");
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}
	
	public static long countUnused(){
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("Select count (*) from File f where f not in (" +
				"select elements(attachments) from BaseArticle a " +
					"where a in (select article from Resource where status <> 'DELETED'))" +
				"and f not in (select elements(embeds) from BaseArticle b " +
					"where b in (select article from Resource where status <> 'DELETED'))");
		return (Long)query.uniqueResult();
	}
}
