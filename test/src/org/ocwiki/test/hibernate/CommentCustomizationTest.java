package org.ocwiki.test.hibernate;

import java.util.List;

import org.ocwiki.data.Comment;
import org.ocwiki.data.CommentCustomization;
import org.ocwiki.data.CommentStatus;
import org.ocwiki.data.User;
import org.ocwiki.db.dao.CommentCustomizationDAO;
import org.ocwiki.db.dao.CommentDAO;
import org.ocwiki.db.dao.UserDAO;

import org.junit.Assert;
import org.junit.Test;

public class CommentCustomizationTest extends HibernateTest {

	@Test
	public void testCountByCommentAuthorAndStatus() {
		long count = CommentCustomizationDAO.countByCommentAuthorAndStatus(1,
				CommentStatus.LIKE);
		Assert.assertEquals(1, count);
		
		count = CommentCustomizationDAO.countByCommentAuthorAndStatus(1,
				CommentStatus.HIDDEN);
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void testFetch() {
		List<CommentCustomization> customizations = CommentCustomizationDAO
				.fetchByResourceAndUser(88, 2, 0, 10);
		System.out.println(customizations);
		Assert.assertEquals(1, customizations.size());
	}

	@Test
	public void testCountByResourceAndUser() {
		Assert.assertEquals(1, CommentCustomizationDAO.countByResourceAndUser(
				88, 1));
	}
	
	@Test
	public void countByCommentAuthorAndStatus() {
		Assert.assertEquals(1, CommentCustomizationDAO
				.countByCommentAuthorAndStatus(1, CommentStatus.HIDDEN));
	}
	
	@Test
	public void testPersist() {
		Comment comment = CommentDAO.fetch(2);
		User user = UserDAO.fetchById(1);
		CommentCustomization customization = new CommentCustomization(comment,
				user, CommentStatus.HIDDEN);
		CommentCustomizationDAO.persist(customization);

		List<CommentCustomization> customizations = CommentCustomizationDAO
				.fetchByResourceAndUser(comment.getResource().getId(), user
						.getId(), 0, 10);
		Assert.assertEquals(1, customizations.size());
	}

}
