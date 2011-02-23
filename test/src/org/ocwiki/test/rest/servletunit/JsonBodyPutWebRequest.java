package org.ocwiki.test.rest.servletunit;

public class JsonBodyPutWebRequest extends JsonBodyPostWebRequest {

	public JsonBodyPutWebRequest(String url, String body) {
		super(url, body);
		setParameter("_method", "PUT");
	}

}
