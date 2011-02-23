package org.ocwiki.test.hibernate;

import java.util.List;

import org.ocwiki.data.FacebookAccount;
import org.ocwiki.data.User;
import org.ocwiki.db.dao.FacebookAccountDAO;
import org.ocwiki.db.dao.UserDAO;

import org.junit.Assert;
import org.junit.Test;

public class FacebookAccountTest extends HibernateTest {

	public FacebookAccountTest() {
		super("facebook.xml");
	}
	
	@Test
	public void testFetchById() {
		FacebookAccount teacher = FacebookAccountDAO.fetchByUid("100000099729209");
		Assert.assertEquals("teacher", teacher.getUser().getName());
		
		FacebookAccount admin = FacebookAccountDAO.fetchByUid("1260054681");
		Assert.assertEquals("admin", admin.getUser().getName());
	
		FacebookAccount noOne = FacebookAccountDAO.fetchByUid("19842895");
		Assert.assertNull(noOne);
	}
	
	@Test
	public void testFetchByUser() {
		List<FacebookAccount> accounts = FacebookAccountDAO.fetchByUser(1);
		Assert.assertEquals(1, accounts.size());
		Assert.assertEquals("1260054681", accounts.get(0).getUid());
	}
	
	@Test
	public void testCreate() {
		User admin = UserDAO.fetchById(1);
		FacebookAccount newAccount =  new FacebookAccount("123456789", admin);
		FacebookAccountDAO.persist(newAccount);
		Assert.assertNotNull(newAccount);
		Assert.assertEquals(2, FacebookAccountDAO.fetchByUser(1).size());
	}
	
}
