package oop.test.hibernate;

import java.util.List;

import oop.data.ResourceSearchReport;
import oop.db.dao.ArticleDAO;

import org.junit.Assert;
import org.junit.Test;

public class ArticleTest extends HibernateTest {

	@Test
	public void testFetchByTopics() {
		List<ResourceSearchReport<oop.data.Test>> result = ArticleDAO
				.fetchRelated(oop.data.Test.class, 62, 0, 3);
		Assert.assertEquals(3, result.size());
	}
	
}
