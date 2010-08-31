package oop.test.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import oop.controller.rest.util.ObjectResult;
import oop.data.Comment;
import oop.data.CommentStatus;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class CommentServiceTest extends AbstractServiceTest {

	@Test
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

	@Test
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

}
