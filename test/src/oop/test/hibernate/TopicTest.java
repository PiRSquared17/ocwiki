package oop.test.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import oop.data.Topic;
import oop.data.User;
import oop.db.dao.TopicDAO;
import oop.db.dao.UserDAO;

import org.junit.Assert;
import org.junit.Test;

public class TopicTest extends HibernateTest {

	public TopicTest() {
		super("topic.xml");
	}
	
	@Test
	public void self() {
		Topic topic = TopicDAO.fetchById(104);
		Assert.assertEquals(104, topic.getId());
		Assert.assertEquals("Khoa học", topic.getName());
		Assert.assertNull(topic.getParent());
	}
	
	@Test
	public void ancestors() {
		Topic topic = TopicDAO.fetchById(102);
		List<Topic> ancestors = topic.getAncestors();
		Assert.assertEquals("Ngoại ngữ", ancestors.get(0).getName());
		Assert.assertEquals("Xã hội", ancestors.get(1).getName());
		Assert.assertEquals(2, ancestors.size());
	}
	
	@Test
	public void descendants() {
		Topic topic = TopicDAO.fetchByName("Xã hội");
		Set<Topic> descendants = topic.getDescendants();

		Set<Topic> expected = new HashSet<Topic>();
		expected.add(TopicDAO.fetchByName("Ngoại ngữ"));
		expected.add(TopicDAO.fetchByName("Tiếng Anh"));
		expected.add(TopicDAO.fetchByName("Tiếng Pháp"));
		
		Assert.assertEquals(3, descendants.size());
		Assert.assertTrue(expected.containsAll(descendants));
	}
	
	@Test
	public void create() {
		User author = UserDAO.fetchById(1);
		Topic topic = TopicDAO.create("thử 123", null, author);
		Assert.assertTrue(topic.getId() > 0);
	}
	
}
