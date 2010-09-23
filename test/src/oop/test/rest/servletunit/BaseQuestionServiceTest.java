package oop.test.rest.servletunit;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import oop.conf.Config;
import oop.data.Namespace;

import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

public class BaseQuestionServiceTest extends ResourceTest {

	private static final String PATH = Config.get().getRestPath()
			+ "/questions";

	@Test
	public void testRetrieve() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		WebResponse response = client.getResponse(PATH + "/88");
		
		System.out.println(response.getText());
		JsonNode root = parseJSON(response);
		JsonNode question = root.get("result");
		Assert.assertEquals("88", question.get("id").getValueAsText());
		Assert.assertEquals("1044", question.get("content").get("id").getValueAsText());
		Assert.assertEquals(String.valueOf(Namespace.QUESTION), 
				question.get("namespace").get("id").getValueAsText());
		Assert.assertEquals("5", question.get("level").getValueAsText());
		Assert.assertEquals(4, question.get("answers").size());
		Assert.assertEquals("321", question.get("answers").get(0).get("id").getValueAsText());
		Assert.assertEquals("322", question.get("answers").get(1).get("id").getValueAsText());
		Assert.assertEquals("323", question.get("answers").get(2).get("id").getValueAsText());
		Assert.assertEquals("324", question.get("answers").get(3).get("id").getValueAsText());
	}
	
	@Test
	public void testUpdate() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		login(client, "teacher", "1234");
		String json = "{" +
				"\"article\": {" +
					"\"type\":\"baseQuestion\"," +
					"\"id\":\"88\"," +
					"\"content\":{\"text\":\"content\"}," +
					"\"namespace\":{\"id\":\"0\"}," +
					"\"answers\":[" +
						"{\"content\":{\"id\":\"56\"},\"correct\":true}," +
						"{\"content\":{\"id\":\"57\",\"text\":\"answer2\"},\"correct\":false}," +
						"{\"content\":{\"id\":\"58\",\"text\":\"answer3\"},\"correct\":false}," +
						"{\"content\":{\"text\":\"answer4\"},\"correct\":false}" +
					"]," +
					"\"level\":\"3\"}," +
				"\"summary\": \"sửa bằng unit test\"," +
				"\"minor\": true}";
//		String json = "{}";
//		JsonUtils.fromJson(json, new TypeReference<Revision<BaseQuestion>>() {});
		WebRequest request = new JsonBodyPostWebRequest(PATH + "/88", json);
		request.setHeaderField("Accept", MediaType.APPLICATION_JSON);
		client.setExceptionsThrownOnErrorStatus(false);
		WebResponse response = client.getResponse(request);
		System.out.println(response.getText());
		
		JsonNode root = parseJSON(response);
		JsonNode question = root.get("result");
		Assert.assertTrue(!"88".equals(question.get("id").getValueAsText()));
		Assert.assertTrue("content".equals(question.get("content").get("text").getValueAsText()));
		Assert.assertTrue(!"1044".equals(question.get("content").get("id").getValueAsText()));
		Assert.assertEquals("0", question.get("namespace").get("id").getValueAsText());
		Assert.assertEquals("3", question.get("level").getValueAsText());
		Assert.assertEquals(4, question.get("answers").size());
		
		Assert.assertTrue(!"321".equals(question.get("answers").get(0).get("id").getValueAsText()));
		Assert.assertTrue(!"322".equals(question.get("answers").get(1).get("id").getValueAsText()));
		Assert.assertTrue(!"323".equals(question.get("answers").get(2).get("id").getValueAsText()));
		Assert.assertTrue(!"0".equals(question.get("answers").get(3).get("id").getValueAsText()));

//		Assert.assertEquals("answer1", question.get("answers").get(0).get("content").get("text").getTextValue());
		Assert.assertEquals("answer2", question.get("answers").get(1).get("content").get("text").getTextValue());
		Assert.assertEquals("answer3", question.get("answers").get(2).get("content").get("text").getTextValue());
		Assert.assertEquals("answer4", question.get("answers").get(3).get("content").get("text").getTextValue());
	}

	/**
	 * <p>
	 * Kiểm thử trường hợp không có lựa chọn nào. Khi đặt
	 * <code>answers:[]</code> (mảng rỗng) sẽ gây ra lỗi NullPointerException
	 * trên server, đây là một bug của Jersey (xem <a
	 * href="https://jersey.dev.java.net/issues/show_bug.cgi?id=300">issue
	 * 300</a>)
	 * </p>
	 * 
	 * @throws IOException
	 * @throws SAXException
	 */
	@Test
	public void testUpdateNoAnswer() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		login(client, "teacher", "1234");
		String json = "{" +
				"\"article\": {" +
					"\"type\":\"baseQuestion\"," +
					"\"id\":\"88\"," +
					"\"content\":{\"text\":\"content\"}," +
					"\"namespace\":{\"id\":\"0\"}," +
					"\"answers\":null," + // null thay cho mảng rỗng
					"\"level\":\"3\"}," +
				"\"summary\": \"sửa bằng unit test\"," +
				"\"minor\": true}";
		WebRequest request = new JsonBodyPostWebRequest(PATH + "/88", json);
		client.setExceptionsThrownOnErrorStatus(false);
		WebResponse response = client.getResponse(request);
		
		JsonNode root = parseJSON(response);
		Assert.assertEquals("too little answers", root.get("code")
				.getTextValue());
	}

	@Test
	public void testUpdateOneAnswer() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		login(client, "teacher", "1234");
		String json = "{" +
				"\"article\": {" +
					"\"type\":\"baseQuestion\"," +
					"\"id\":\"88\"," +
					"\"content\":{\"text\":\"content\"}," +
					"\"namespace\":{\"id\":\"0\"}," +
					"\"answers\": [" +
						"{\"content\":{\"id\":\"56\",\"text\":\"answer1\"},\"correct\":true} ]," +
					"\"level\":\"3\"}," +
				"\"summary\": \"sửa bằng unit test\"," +
				"\"minor\": true}";
		WebRequest request = new JsonBodyPostWebRequest(PATH + "/88", json);
		client.setExceptionsThrownOnErrorStatus(false);
		WebResponse response = client.getResponse(request);
		
		JsonNode root = parseJSON(response);
		Assert.assertEquals("too little answers", root.get("code")
				.getTextValue());
	}
	
	@Test
	public void testUpdateBlankAnswerContent() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		login(client, "teacher", "1234");
		String json = "{" +
				"\"article\": {" +
					"\"type\":\"baseQuestion\"," +
					"\"id\":\"88\"," +
					"\"content\":{\"text\":\"content\"}," + 
					"\"namespace\":{\"id\":\"0\"}," +
					"\"answers\": [" +
						"{\"content\":{\"text\":\"\"},\"correct\":true}," + // blank 
						"{\"content\":{\"id\":\"57\",\"text\":\"answer2\"},\"correct\":false}]," +
					"\"level\":\"3\"}," +
				"\"summary\": \"sửa bằng unit test\"," +
				"\"minor\": true}";
		WebRequest request = new JsonBodyPostWebRequest(PATH + "/88", json);
		client.setExceptionsThrownOnErrorStatus(false);
		WebResponse response = client.getResponse(request);
		
		JsonNode root = parseJSON(response);
		Assert.assertEquals("answer content is blank", root.get("code")
				.getTextValue());
	}

	@Test
	public void testUpdateBlankQuestionContent() throws IOException, SAXException {
		ServletUnitClient client = getServletRunner().newClient();
		login(client, "teacher", "1234");
		String json = "{" +
				"\"article\": {" +
					"\"type\":\"baseQuestion\"," +
					"\"id\":\"88\"," +
					"\"content\":{\"text\":\"\"}," + // blank
					"\"namespace\":{\"id\":\"0\"}," +
					"\"answers\": [" +
						"{\"content\":{\"id\":\"56\",\"text\":\"answer1\"},\"correct\":true}," +
						"{\"content\":{\"id\":\"57\",\"text\":\"answer2\"},\"correct\":false}]," +
					"\"level\":\"3\"}," +
				"\"summary\": \"sửa bằng unit test\"," +
				"\"minor\": true}";
		WebRequest request = new JsonBodyPostWebRequest(PATH + "/88", json);
		client.setExceptionsThrownOnErrorStatus(false);
		WebResponse response = client.getResponse(request);
		
		JsonNode root = parseJSON(response);
		Assert.assertEquals("question content is blank", root.get("code")
				.getTextValue());
	}
	
}
