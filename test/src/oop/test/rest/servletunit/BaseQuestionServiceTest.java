package oop.test.rest.servletunit;

import java.io.IOException;

import oop.conf.Config;

import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class BaseQuestionServiceTest extends ResourceTest {

	private static final String PATH = Config.get().getRestPath()
			+ "/questions";

	@Test
	public void testRetrieve() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		WebResponse response = client.getResponse(PATH + "/88");
		
		JsonNode root = parseJSON(response);
		Assert.assertEquals("88", root.get("id").getValueAsText());
		Assert.assertEquals("1044", root.get("content").get("id").getValueAsText());
		Assert.assertEquals("0", root.get("namespace").get("id").getValueAsText());
		Assert.assertEquals("5", root.get("level").getValueAsText());
		Assert.assertEquals(4, root.get("answers").size());
		Assert.assertEquals("321", root.get("answers").get(0).get("id").getValueAsText());
		Assert.assertEquals("322", root.get("answers").get(1).get("id").getValueAsText());
		Assert.assertEquals("323", root.get("answers").get(2).get("id").getValueAsText());
		Assert.assertEquals("324", root.get("answers").get(3).get("id").getValueAsText());
	}
	
	@Test
	public void testUpdate() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		login(client, "teacher", "1234");
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
		WebRequest request = new JsonBodyPostWebRequest(PATH + "/88", json);
		WebResponse response = client.getResponse(request);
		
		JsonNode root = parseJSON(response);
		Assert.assertTrue(!"88".equals(root.get("id").getValueAsText()));
		Assert.assertTrue(!"1044".equals(root.get("content").get("id").getValueAsText()));
//		Assert.assertEquals("0", root.get("namespace").get("id").getValueAsText());
		Assert.assertEquals("3", root.get("level").getValueAsText());
		Assert.assertEquals(3, root.get("answers").size());
		
		Assert.assertTrue(!"321".equals(root.get("answers").get(0).get("id").getValueAsText()));
		Assert.assertTrue(!"322".equals(root.get("answers").get(1).get("id").getValueAsText()));
		Assert.assertTrue(!"323".equals(root.get("answers").get(2).get("id").getValueAsText()));

		Assert.assertEquals("answer1", root.get("answers").get(0).get("content").getValueAsText());
		Assert.assertEquals("answer2", root.get("answers").get(1).get("content").getValueAsText());
		Assert.assertEquals("answer3", root.get("answers").get(2).get("content").getValueAsText());
	}

}
