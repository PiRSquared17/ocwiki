package oop.test.rest.servletunit;

import java.io.IOException;

import oop.conf.Config;
import oop.controller.rest.TestResource;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class TestResourceTest extends ResourceTest {

	@Test
	public void testRetrieve() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		WebResponse response = client.getResponse(Config.get().getRestPath()
				+ TestResource.PATH + "/62");
		System.out.println(response.getText());
	}
	
	@Test
	public void testUpdate() {
	}
	
}
