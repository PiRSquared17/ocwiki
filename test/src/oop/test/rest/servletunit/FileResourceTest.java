package oop.test.rest.servletunit;

import java.io.File;
import java.io.IOException;

import oop.conf.Config;
import oop.controller.rest.FileResource;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.servletunit.ServletUnitClient;

public class FileResourceTest extends ResourceTest {

	private final String path = Config.get().getRestPath() + FileResource.PATH;
	
	@Test
	public void testUpload() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		PostMethodWebRequest request = new PostMethodWebRequest(path + "/123/file", true);
		request.selectFile("file", new File("test/files/flag_of_Vietnam.gif"));
		client.getResponse(request);
	}
	
}
