package oop.test.rest.servletunit;

import java.io.StringReader;

import javax.ws.rs.core.MediaType;

import org.hibernate.engine.jdbc.ReaderInputStream;

import com.meterware.httpunit.PostMethodWebRequest;

public class JsonBodyPostWebRequest extends PostMethodWebRequest {

	public JsonBodyPostWebRequest(String url, String body) {
		super(url, new ReaderInputStream(new StringReader(body)),
				MediaType.APPLICATION_JSON);
	}
	
}
