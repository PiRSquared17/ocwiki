package oop.test.rest;

import javax.ws.rs.core.MediaType;

import oop.conf.Config;
import oop.conf.ConfigIO;
import oop.data.User;
import oop.db.dao.UserDAO;
import oop.persistence.HibernateUtil;

import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class UserResourceTest {

	public UserResourceTest() {
		Config config = new Config();
		ConfigIO.loadDirectory(config, "WebContent/WEB-INF/conf");
		HibernateUtil.init(config);
		Config.setDefaultInstance(config);
	}
	
//	@Test
	public void testLocal() {
		User user = UserDAO.fetchById(1);
		user.setBlocked(true);
		Transaction tx = HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().update(user);
		tx.commit();
		HibernateUtil.getSession().close();
	}
	
	@Test
	public void testUpdate() {
		Client client = Client.create();
		WebResource resource = client.resource(Config.get().getRestPath()
				+ "/users/1");
		String userJson = "{\"type\":\"user\",\"version\":-1,\"id\":1,\"blocked\":true,\"email\":\"admin@ocwiki.org\",\"fullname\":\"admin\",\"group\":\"admin\",\"name\":\"admin\"}";
		String json = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).post(
						String.class, userJson);
		Assert.assertTrue(json.contains("\"blocked\":true"));
		System.out.println(json);
	}
	
}
