package org.ocwiki.test.rest.servletunit;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.ocwiki.conf.Config;

import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class LoginServiceTest extends ResourceTest {

	@Test
	public void testLogin() throws IOException, SAXException {
		String username = "admin";
		String password = "1234";

		ServletUnitClient client = getServletRunner().newClient();
		PostMethodWebRequest request = new PostMethodWebRequest(
				Config.get().getRestPath() + "/login");
		request.setHeaderField("Accept", MediaType.APPLICATION_XML);
		request.setParameter("name", username);
		request.setParameter("password", password);
		WebResponse response = client.getResponse(request);

		JsonNode root = parseJSON(response);
		Assert.assertEquals(username, root.get("result").get("name")
				.getTextValue());
	}

}
