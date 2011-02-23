package org.ocwiki.test.rest;

import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBException;

import org.ocwiki.controller.rest.util.ObjectResult;
import org.ocwiki.data.BaseQuestion;
import org.ocwiki.data.Comment;
import org.ocwiki.data.CommentStatus;
import org.ocwiki.util.JsonUtils;

import org.junit.Assert;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class CommentServiceTest extends AbstractServiceTest {

//	@Test
	public void testCreate() throws Exception {
		WebResource resource = createResource("/comments");
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.putSingle("resourceId", "1");
		formData.putSingle("revisionId", "1");
		formData.putSingle("message", "xyz");
		ObjectResult<Comment> obj = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_FORM_URLENCODED).post(
						new GenericType<ObjectResult<Comment>>() {
						}, formData);
		Assert.assertEquals("xyz", obj.getResult().getMessage());
	}

//	@Test
	public void testUpdate() {
		WebResource resource = createResource("/comments/1");
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.putSingle("message", "xyz");
		formData.putSingle("status", CommentStatus.HIDDEN.name());
		ObjectResult<Comment> obj = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).put(
						new GenericType<ObjectResult<Comment>>() {
						}, formData);
		Assert.assertEquals("xyz2", obj.getResult().getMessage());
	}
	
//	@Test
	public void testBaseQuestionUpdate() throws IOException, JAXBException {
		WebResource resource = createResource("/basequestion/88");
		String json = "{" +
				"\"id\":\"88\"," +
				"\"content\":{\"id\":\"1044\",\"text\":\"content\"}," +
				"\"namespace\":{\"id\":\"0\"}," +
				"\"answers\":[" +
					"{\"id\":\"321\",\"content\":{\"id\":\"56\",\"text\":\"answer1\"},\"correct\":\"false\"}," +
					"{\"id\":\"322\",\"content\":{\"id\":\"57\",\"text\":\"answer2\"},\"correct\":\"false\"}," +
					"{\"id\":\"323\",\"content\":{\"id\":\"58\",\"text\":\"answer3\"},\"correct\":\"false\"}" +
				"]," +
				"\"level\":\"3\"}";
		BaseQuestion newQuestion = JsonUtils.fromJson(json, BaseQuestion.class);
		BaseQuestion question = resource
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.post(BaseQuestion.class, newQuestion);
		Assert.assertNotNull(question);
	}

}
