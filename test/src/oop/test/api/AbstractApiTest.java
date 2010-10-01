package oop.test.api;

import java.io.File;
import java.io.IOException;

import oop.conf.Config;
import oop.persistence.HibernateUtil;
import oop.test.hibernate.HibernateTestUtil;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public abstract class AbstractApiTest {

	private static ServletRunner servletRunner;

	@BeforeClass
	public static void setupClass() throws IOException, SAXException {
		String contextPath = "/ocwiki";
		servletRunner = new ServletRunner(new File("test/web.xml"), contextPath);
		// do lỗi của ServletRunner nên khi oop.controller.Initializer được gọi
		// thì contextPath=null
		Config.get().setHomeDir("http://localhost/" + contextPath);
	}

	@Before
	public void setup() throws Exception {
		HibernateTestUtil.cleanInsertDataset("test/dataset/full.xml");
	}

	@After
	public void tearDown() {
		HibernateUtil.closeSession();
	}

	protected JsonNode parseJSON(WebResponse response) throws IOException,
			JsonParseException, JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		JsonParser parser = objMapper.getJsonFactory().createJsonParser(
				response.getText());
		JsonNode tree = parser.readValueAsTree();
		return tree;
	}

	/**
	 * Lấy về đối tượng ServletRunner. Đây là đối tượng có nhiệm vụ tạo môi 
	 * trường hoạt động cho các Servlet thay cho Tomcat.
	 * @return
	 */
	protected static ServletRunner getServletRunner() {
		return servletRunner;
	}

	protected void login(ServletUnitClient client, String username,
			String password) throws IOException, SAXException {
		PostMethodWebRequest request = new PostMethodWebRequest(Config.get()
				.getRestPath()
				+ "/login");
		request.setParameter("name", username);
		request.setParameter("password", password);
		WebResponse response = client.getResponse(request);

		JsonNode root = parseJSON(response);
		Assert.assertEquals(username, root.get("result").get("name")
				.getTextValue());
	}
	
}
