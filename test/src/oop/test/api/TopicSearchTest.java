package oop.test.api;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class TopicSearchTest extends AbstractApiTest {

	private static final String PATH = "http://localhost/ocwiki/api/topic.search";

	@Test
	public void testSearch() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		WebResponse response = client.getResponse(PATH + "?query=Anh");
		System.out.println(response.getText());
		JsonNode root = parseJSON(response);
		JsonNode suggestions = root.get("suggestions");
		JsonNode data = root.get("data");
		System.out.println(root);
		Assert.assertEquals(2, suggestions.size());
		Assert.assertEquals(2, data.size());
	}
	
}
