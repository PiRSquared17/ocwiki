package oop.test.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import oop.conf.Config;
import oop.controller.rest.util.ObjectResult;
import oop.data.Answer;
import oop.test.TestConfig;
import oop.util.GsonFactory;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class AnswerResourceTest {

	static {
		TestConfig.getConfig();
	}

//	@Test
	public void update() {
		Client client = Client.create();
		WebResource resource = client.resource(Config.get().getRestPath()
				+ "/answers/1");
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.putSingle("content", "xyz");
		String json = resource.type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(String.class, formData);
		ObjectResult<Answer> obj = GsonFactory.get().fromJson(json,
				new TypeToken<ObjectResult<Answer>>() {
				}.getType());

		Assert.assertEquals("xyz", obj.getResult().getContent().getText());
		Assert.assertEquals(false, obj.getResult().isCorrect());
		System.out.println(obj.getResult());
	}

}
