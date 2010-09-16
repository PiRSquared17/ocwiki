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
		Assert.assertEquals(1, root.size());
	}
	
}
