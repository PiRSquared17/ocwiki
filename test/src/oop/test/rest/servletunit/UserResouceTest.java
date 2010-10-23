package oop.test.rest.servletunit;

import java.io.IOException;


import oop.conf.Config;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class UserResouceTest extends AbstractResourceTest {

	@Test
	public void testRetrieve() throws IOException, SAXException {
		ServletUnitClient sc = getServletRunner().newClient();
		WebRequest request = new GetMethodWebRequest(
				Config.get().getRestPath() + "/users/1");
		WebResponse response = sc.getResponse(request);

		JsonNode tree = parseJSON(response);
		JsonNode result = tree.get("result");
		Assert.assertEquals("admin", result.get("name").getTextValue());
		Assert.assertEquals(false, result.get("blocked").getBooleanValue());
		getServletRunner().shutDown();
	}

	@Test
	public void testUpdate() throws IOException, SAXException {
		String blockExpiredDate = "2010-10-25T00:47:13+07:00";

		ServletUnitClient sc = getServletRunner().newClient();
		String url = Config.get().getRestPath() + "/users/1";
		WebResponse response = sc.getResponse(url);
		ObjectNode root = (ObjectNode) parseJSON(response).get("result");
		
		root.put("blocked", true);
		root.put("blockExpiredDate", blockExpiredDate);
		JsonBodyPostWebRequest request = new JsonBodyPostWebRequest(url, root.toString());
		response = sc.getResponse(request);
		
		root = (ObjectNode) parseJSON(response).get("result");
		Assert.assertEquals("true", root.get("blocked").getValueAsText());
		Assert.assertEquals(blockExpiredDate, root.get("blockExpiredDate").getValueAsText());
	}

	@Test
	public void testUpdateOldVersion() throws IOException, SAXException {
		ServletUnitClient sc = getServletRunner().newClient();
		sc.setExceptionsThrownOnErrorStatus(false);
		String url = Config.get().getRestPath() + "/users/1";
		WebResponse response = sc.getResponse(url);
		ObjectNode root = (ObjectNode) parseJSON(response).get("result");

		int version = Integer.parseInt(root.get("version").getValueAsText());
		root.put("version", version-1);
		JsonBodyPostWebRequest request = new JsonBodyPostWebRequest(url, root.toString());
		response = sc.getResponse(request);
		
		root = (ObjectNode) parseJSON(response);
		response.getResponseCode();
		Assert.assertEquals("old version", root.get("code").getTextValue());
	}

}
