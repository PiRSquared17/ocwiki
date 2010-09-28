package oop.test.rest;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

import oop.conf.Config;
import oop.test.rest.servletunit.JsonBodyPostWebRequest;
import oop.test.rest.servletunit.ResourceTest;

public class CommentCustomizationResourceTest extends ResourceTest {

	@Test
	public void testCreate() throws IOException, SAXException {
		String json = "{\"comment\":{\"id\": 2}, \"status\": \"LIKE\"}";
		
		ServletUnitClient client = getServletRunner().newClient();
		login(client, "admin", "1234");
		WebRequest request = new JsonBodyPostWebRequest(Config.get()
				.getRestPath()
				+ "/comment_customizations", json);
		WebResponse response = client.getResponse(request);
		
		JsonNode result = parseJSON(response).get("result");
		Assert.assertEquals("LIKE", result.get("status").getTextValue());
	}
	
}
