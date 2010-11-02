package oop.test.rest.servletunit;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import oop.conf.Config;
import oop.controller.rest.ResourceService;

import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class ResourceServiceTest extends AbstractResourceTest {

	private static final String PATH = Config.get().getRestPath()
			+ ResourceService.PATH;
	
	@Test
	public void testRetrieve() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		WebResponse response = client
				.getResponse(createRequest(ResourceService.PATH + "/62"));
		
		JsonNode resource = parseJSON(response).get("result");
		Assert.assertEquals("1", resource.get("author").get("id").getValueAsText());
		Assert.assertEquals("62", resource.get("article").get("id").getValueAsText());
		Assert.assertEquals("NORMAL", resource.get("status").getValueAsText());
		Assert.assertEquals("EVERYONE", resource.get("accessibility").getValueAsText());
		Assert.assertEquals("oop.data.Test", resource.get("articleType").getValueAsText());
		Assert.assertEquals("2010-08-25T01:15:32+07:00", resource.get("createDate").getValueAsText());
	}
	
	@Test
	public void testUpdateWithJunkData() throws IOException, SAXException {
		String json = "{\"type\":\"resourceBean\"," +
				"\"accessibility\":\"AUTHOR_ONLY\"," +
				"\"article\":{\"id\":63,\"name\":\"English\",\"namespace\":{\"id\":4,\"name\":\"xyz\"}}," +
				"\"author\":{\"id\":2}," +
				"\"createDate\":\"2010-08-25T01:15:32+07:00\"," +
				"\"id\":62," +
				"\"status\":\"DELETED\"," +
				"\"articleType\":\"oop.data.TestStructure\"," +
				"\"version\":0}";
		System.out.println(StringEscapeUtils.escapeJava(json));

		ServletUnitClient client = getServletRunner().newClient();
		login(client, "admin", "1234");

		WebRequest request = new JsonBodyPutWebRequest(PATH + "/62", json);
		request.setHeaderField("Accept", MediaType.APPLICATION_XML);
		WebResponse response = client.getResponse(request);
		JsonNode result = parseJSON(response).get("result");
		
		Assert.assertEquals("AUTHOR_ONLY", result.get("accessibility").getValueAsText());
		Assert.assertEquals("DELETED", result.get("status").getValueAsText());
		Assert.assertEquals("oop.data.Test", result.get("articleType").getValueAsText());
		Assert.assertEquals("1", result.get("version").getValueAsText());
		Assert.assertEquals("1", result.get("author").get("id").getValueAsText());
		Assert.assertEquals("62", result.get("article").get("id").getValueAsText());
	}
	
	@Test
	public void testUpdateCompactData() throws IOException, SAXException {
		String json = "{\"accessibility\":\"AUTHOR_ONLY\"," +
				"\"status\":\"DELETED\"," +
				"\"version\":0}";
		System.out.println(StringEscapeUtils.escapeJava(json));

		ServletUnitClient client = getServletRunner().newClient();
		login(client, "admin", "1234");

		JsonBodyPostWebRequest request = new JsonBodyPostWebRequest(PATH + "/62", json);
		request.setHeaderField("Accept", MediaType.APPLICATION_XML);
		WebResponse response = client.getResponse(request);
		JsonNode result = parseJSON(response).get("result");
		
		Assert.assertEquals("AUTHOR_ONLY", result.get("accessibility").getValueAsText());
		Assert.assertEquals("DELETED", result.get("status").getValueAsText());
		Assert.assertEquals("oop.data.Test", result.get("articleType").getValueAsText());
		Assert.assertEquals("1", result.get("version").getValueAsText());
		Assert.assertEquals("1", result.get("author").get("id").getValueAsText());
		Assert.assertEquals("62", result.get("article").get("id").getValueAsText());
	}
	
}
