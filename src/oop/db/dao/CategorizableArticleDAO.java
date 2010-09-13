package oop.db.dao;

import java.util.Collection;
import java.util.List;

import oop.data.CategorizableArticle;
import oop.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

public class CategorizableArticleDAO {

	public static <T extends CategorizableArticle> List<ResourceSearchReport<T>> 
			fetchByTopics(Class<T> articleType, Collection<Long> resourceIds) {
		Session session = HibernateUtil.getSession();
		String hql = "select new ResourceSearchReport(r, count(t.id)) " +
				"from Resource r join r.article a join a.topics t " +
				"where articleType=:artType and t in :topics " +
				"group by r)";
		Query query = session.createQuery(hql);
		query.setParameterList("topics", resourceIds);
		query.setString("artType", articleType.getName());
		return query.list();
	}
	
}
