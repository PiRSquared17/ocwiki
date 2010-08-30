package oop.test.rest;

import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ObjectResult;
import oop.data.User;

import org.junit.Test;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class UserServiceTest extends AbstractServiceTest {

	public UserServiceTest() {
	}
	
	@Test
	public void testUpdate() {
		WebResource resource = createResource("/users/1");
		String userJson = "{\"version\":1,\"id\":1,\"blocked\":true,\"email\":\"admin@ocwiki.org\",\"fullname\":\"admin\",\"group\":\"admin\",\"name\":\"admin\"}";
		ObjectResult<User> obj = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).post(
						new GenericType<ObjectResult<User>>() {
						}, userJson);
		System.out.println(obj.getResult().isBlocked());
	}
	
}
