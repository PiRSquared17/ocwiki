package oop.test.rest.servletunit;

import java.io.IOException;

import oop.conf.Config;

import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class BaseQuestionServiceTest extends ResourceTest {

	@Test
	public void testRetrieve() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		WebResponse response = client.getResponse(Config.get().getRestPath()
				+ "/basequestion/88");
		
		JsonNode root = parseJSON(response);
		Assert.assertEquals("88", root.get("id").getValueAsText());
		Assert.assertEquals(88, root.get("id").getLongValue());
	}

}
