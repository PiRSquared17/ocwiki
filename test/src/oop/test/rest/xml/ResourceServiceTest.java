package oop.test.rest.xml;

import javax.ws.rs.core.MediaType;

import oop.controller.rest.ResourceService;
import oop.test.rest.servletunit.AbstractResourceTest;

import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class ResourceServiceTest extends AbstractResourceTest {

	@Test
	public void retrieve() throws Exception {
		ServletUnitClient client = getServletRunner().newClient();
		GetMethodWebRequest request = new GetMethodWebRequest(
				getRestPath(ResourceService.PATH + "/62"));
		request.setHeaderField("Accept", MediaType.APPLICATION_XML);
		WebResponse response = client.getResponse(request);
		System.out.println(response.getText());
	}
	
}
