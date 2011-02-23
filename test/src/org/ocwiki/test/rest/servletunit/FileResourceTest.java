package org.ocwiki.test.rest.servletunit;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.ocwiki.conf.Config;
import org.ocwiki.controller.rest.FileResource;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class FileResourceTest extends AbstractResourceTest {

	private final String PATH = Config.get().getRestPath() + FileResource.PATH;
	
	@Test
	public void testUpdate() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		PostMethodWebRequest request = new PostMethodWebRequest(PATH + "/123", true);
		request.setHeaderField("Accept", MediaType.APPLICATION_JSON);
		request.selectFile("file", new File("test/files/flag_of_Vietnam.gif"));
		WebResponse response = client.getResponse(request);
	}
	
}
