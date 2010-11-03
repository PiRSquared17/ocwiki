package oop.test.rest.servletunit;

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
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

/**
 * Lớp cha của các lớp kiểm thử web service:
 * <ul>
 * <li>Khởi động ServletRunner dựa trên cấu hình trong test/web.xml</li>
 * <li>Tạo lại dữ liệu lấy từ test/dataset/full.xml trước mỗi test case</li>
 * <li>Đóng Hibernate session sau mỗi test case</li>
 * <li>Cung cấp một số hàm dịch vụ</li>
 * </ul> 
 * @author cumeo89
 */
public class ResourceTest {

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
	
	@AfterClass
	public void tearDownClass() {
		servletRunner.shutDown();
	}

	protected JsonNode parseJSON(WebResponse response) throws IOException,
			JsonParseException, JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		JsonParser parser = objMapper.getJsonFactory().createJsonParser(
				response.getText());
		JsonNode tree = parser.readValueAsTree();
		return tree;
	}

	public ResourceTest() {
		super();
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