package oop.test.hibernate;

import oop.data.Article;
import oop.data.BaseQuestion;
import oop.data.Topic;
import oop.db.dao.ArticleDAO;

import org.junit.Assert;
import org.junit.Test;

public class ArticleTest extends HibernateTest {

	public ArticleTest() {
		super("test.xml");
	}
	
	@Test
	public void polymorphism() {
		Article question = ArticleDAO.fetchById(1);
		Assert.assertTrue(question instanceof BaseQuestion);
		
		Article test = ArticleDAO.fetchById(6);
		Assert.assertTrue(test instanceof oop.data.Test);
		
		Article topic = ArticleDAO.fetchById(101);
		Assert.assertTrue(topic instanceof Topic);
	}
	
}
