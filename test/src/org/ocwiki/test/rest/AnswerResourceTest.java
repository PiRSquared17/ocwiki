package org.ocwiki.test.rest;

import org.ocwiki.test.TestConfig;

public class AnswerResourceTest {

	static {
		TestConfig.getConfig();
	}

	/*
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
	*/
	
}
