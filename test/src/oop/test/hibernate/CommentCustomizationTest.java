package oop.test.hibernate;

import java.util.List;

import oop.data.Comment;
import oop.data.CommentCustomization;
import oop.data.CommentStatus;
import oop.data.User;
import oop.db.dao.CommentCustomizationDAO;
import oop.db.dao.CommentDAO;
import oop.db.dao.UserDAO;

import org.junit.Assert;
import org.junit.Test;

public class CommentCustomizationTest extends HibernateTest {

	@Test
	public void testFetchByResourceNoUser() {
		List<CommentCustomization> customizations = CommentCustomizationDAO
				.fetchByResourceAndUserWithDefault(88, 1, 0, 10);
		Assert.assertEquals(1, customizations.size());
		Assert.assertEquals("comment 1234", customizations.get(0).getComment()
				.getMessage());
	}

	@Test
	public void testPersist() {
		Comment comment = CommentDAO.fetch(2);
		User user = UserDAO.fetchById(1);
		CommentCustomization customization = new CommentCustomization(comment,
				user, CommentStatus.HIDDEN);
		CommentCustomizationDAO.persist(customization);

		List<CommentCustomization> customizations = CommentCustomizationDAO
				.fetchByResourceAndUser(88, 1, 0, 10);
		Assert.assertEquals(1, customizations.size());
	}

}
