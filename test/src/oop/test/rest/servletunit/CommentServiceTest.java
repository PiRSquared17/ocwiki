package oop.test.rest.servletunit;

import java.io.IOException;


import oop.conf.Config;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class CommentServiceTest extends ServiceTest {

	@Test
	public void testRetrieve() throws IOException, SAXException {
		ServletUnitClient sc = getServletRunner().newClient();
		WebRequest request = new GetMethodWebRequest(
				Config.get().getRestPath() + "/users/1");
		WebResponse response = sc.getResponse(request);

		ObjectMapper objMapper = new ObjectMapper();
		JsonParser parser = objMapper.getJsonFactory().createJsonParser(
				response.getText());
		JsonNode tree = parser.readValueAsTree();
		JsonNode result = tree.get("result");
		Assert.assertEquals("admin", result.get("name").getTextValue());
		Assert.assertEquals(false, result.get("blocked").getBooleanValue());
		getServletRunner().shutDown();
	}

}
