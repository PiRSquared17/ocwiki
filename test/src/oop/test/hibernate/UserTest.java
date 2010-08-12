package oop.test.hibernate;

import junit.framework.Assert;
import oop.data.User;
import oop.db.dao.UserDAO;

import org.junit.Test;

public class UserTest extends HibernateTest {

	public UserTest() {
		super("user.xml");
	}
	
	@Test
	public void getById() {
		User users = UserDAO.fetchById(1);
		Assert.assertNotNull(users);
		Assert.assertEquals(users.getName(), "cumeo89");
	}
	
	@Test
	public void create() {
		User newUser = UserDAO.create("test2", "abc", "def", "cumeo89");
		Assert.assertNotNull(newUser);
		User user = UserDAO.fetchByUsername("test2");
		Assert.assertEquals(newUser.getName(), user.getName());
		Assert.assertEquals(newUser.getFullname(), user.getFullname());
		Assert.assertEquals(newUser.getPassword(), user.getPassword());
		Assert.assertEquals(newUser.getEmail(), user.getEmail());
	}
	
}
