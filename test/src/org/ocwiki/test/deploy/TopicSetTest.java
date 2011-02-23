package org.ocwiki.test.deploy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.ocwiki.controller.OcwikiApp;
import org.ocwiki.persistence.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TopicSetTest {

	@BeforeClass
	public static void setupClass() throws IOException {
		OcwikiApp.initialize("WebContent/WEB-INF/conf");
	}
	
	@Test
	public void testIntergrity() {
		Session session = HibernateUtil.getSession();
		String hql = "select r.id, t.id, t.parent.id " +
				"from TopicSet s join s.resource r, Topic t, " +
					"TopicSet s2 join s2.resource r2 " +
				"where r.article = t and t.parent = r2 and " +
					"(s.leftIndex <= s2.leftIndex or s.rightIndex >= s2.rightIndex)"; 
		Query query = session.createQuery(hql);
		query.setMaxResults(10);
		List<?> result = query.list();
		if (!result.isEmpty()) {
			System.out.println(Arrays.toString((Object[])result.get(0)));
		}
		Assert.assertTrue(result.isEmpty());
		
		hql = "select resource.id from TopicSet where leftIndex >= rightIndex";
		query = session.createQuery(hql);
		query.setMaxResults(10);
		result = query.list();
		if (!result.isEmpty()) {
			System.out.println(Arrays.toString((Object[])result.get(0)));
		}
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void testDensity_noProblemIfItFails() {
		Session session = HibernateUtil.getSession();
		String hql = "select s.resource.id from TopicSet s " +
				"where not exists (from TopicSet s2 " +
					"where s2.leftIndex = s.leftIndex+1 " +
					"or s2.rightIndex = s.leftIndex+1) " +
				"and not exists (from TopicSet s3 " +
					"where s3.leftIndex = s.rightIndex+1 " +
					"or s3.rightIndex = s.rightIndex+1)";
		Query query = session.createQuery(hql);
		query.setMaxResults(10);
		List<?> result = query.list();
		if (!result.isEmpty()) {
			System.out.println(result);
		}
		Assert.assertTrue(result.isEmpty());
	}
	
}
