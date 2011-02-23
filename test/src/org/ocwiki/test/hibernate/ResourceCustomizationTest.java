package org.ocwiki.test.hibernate;

import org.ocwiki.data.ResourceCustomization;
import org.ocwiki.data.ResourceLike;
import org.ocwiki.data.ResourceTodo;
import org.ocwiki.db.dao.ResourceCustomizationDAO;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.db.dao.UserDAO;

import org.junit.Test;

public class ResourceCustomizationTest extends HibernateTest {

	@Test
	public void testFetch() {
		ResourceCustomizationDAO.fetchByResourceAndUser(1, 88);
	}
	
	@Test
	public void testPersist() {
		ResourceCustomization customization = new ResourceCustomization(
				ResourceDAO.fetchById(88), UserDAO.fetchById(2), 1,
				ResourceLike.LIKE, ResourceTodo.NORMAL);
		ResourceCustomizationDAO.persist(customization);
	}
	
}
