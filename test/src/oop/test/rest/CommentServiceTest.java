package oop.test.rest;

import javax.ws.rs.core.MediaType;

import oop.controller.rest.beans.CommentBean;
import oop.controller.rest.util.ObjectResult;
import oop.data.Comment;
import oop.data.CommentStatus;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class CommentServiceTest extends AbstractServiceTest {

	@Test
	public void testCreate() throws Exception {
		WebResource resource = createResource("/comments");
		CommentBean data = new CommentBean(1, 1, 1, 0, "xyz",
				CommentStatus.NORMAL);
		ObjectResult<Comment> obj = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).post(
						new GenericType<ObjectResult<Comment>>() {
						}, data);
		Assert.assertEquals("xyz", obj.getResult().getMessage());
	}

	@Test
	public void testUpdate() {
		WebResource resource = createResource("/comments/1");
		CommentBean data = new CommentBean(1, 1, 1, 0, "xyz2",
				CommentStatus.NORMAL);
		ObjectResult<Comment> obj = resource.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).put(
						new GenericType<ObjectResult<Comment>>() {
						}, data);
		Assert.assertEquals("xyz2", obj.getResult().getMessage());
	}

}
