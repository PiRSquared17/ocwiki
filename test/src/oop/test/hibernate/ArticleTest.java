package oop.test.hibernate;

import oop.db.dao.ArticleDAO;

import org.junit.Test;

public class ArticleTest extends HibernateTest {

	@Test
	public void testFetch() {
		ArticleDAO.fetchById(1);
	}
	
}
