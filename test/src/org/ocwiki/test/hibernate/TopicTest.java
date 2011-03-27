package org.ocwiki.test.hibernate;

import java.util.List;

import org.ocwiki.data.Resource;
import org.ocwiki.data.Topic;
import org.ocwiki.db.dao.TopicDAO;

import org.junit.Assert;
import org.junit.Test;

public class TopicTest extends HibernateTest {

	public TopicTest() {
		super("topic.xml");
	}
	
	@Test
	public void testAncestors() {
		List<Resource<Topic>> ancestors = TopicDAO.getAncestors(461);
		Assert.assertEquals(1, ancestors.size());
		Assert.assertEquals(461, ancestors.get(0).getId());
		Assert.assertEquals("Khoa học tự nhiên", ancestors.get(0).getName());
		
		ancestors = TopicDAO.getAncestors(470);
		Assert.assertEquals(2, ancestors.size());
		Assert.assertEquals(470, ancestors.get(0).getId());
		Assert.assertEquals("Lịch sử", ancestors.get(0).getName());
		Assert.assertEquals(465, ancestors.get(1).getId());
	}

	@Test
	public void testUncategorized() {
		List<Resource<Topic>> topics = TopicDAO.fetchUncategorized(0, 3);
		System.out.println(topics.size());
	}
	
	@Test
	public void testUnused() {
		List<Resource<Topic>> topics = TopicDAO.fetchUnused(0, 3);
		System.out.println(topics.size());
	}
	
	@Test
	public void testListPopularity() {
		TopicDAO.listPopular(0, 10);
	}
	
}
