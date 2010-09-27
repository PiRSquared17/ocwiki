package oop.test.rest.servletunit;

import java.io.File;
import java.io.IOException;

import oop.conf.Config;
import oop.controller.rest.FileResource;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class FileResourceTest extends ResourceTest {

	private final String path = Config.get().getRestPath() + FileResource.PATH;
	
	@Test
	public void testUpload() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		PostMethodWebRequest request = new PostMethodWebRequest(path + "/123/file", true);
		File flag = new File("test/files/flag_of_Vietnam.gif");
		Assert.assertTrue("file not exists", flag.exists());
		request.selectFile("file", flag);
		WebResponse response = client.getResponse(request);
	}
	
}
