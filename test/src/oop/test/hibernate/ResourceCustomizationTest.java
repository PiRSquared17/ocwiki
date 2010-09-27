package oop.test.hibernate;

import oop.data.ResourceCustomization;
import oop.data.ResourceLike;
import oop.data.ResourceTodo;
import oop.db.dao.ResourceCustomizationDAO;
import oop.db.dao.ResourceDAO;
import oop.db.dao.UserDAO;

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
