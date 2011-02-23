package org.ocwiki.test.rest.servletunit;

import java.io.StringReader;

import javax.ws.rs.core.MediaType;

import org.hibernate.engine.jdbc.ReaderInputStream;

import com.meterware.httpunit.PostMethodWebRequest;

public class JsonBodyPostWebRequest extends PostMethodWebRequest {

	public JsonBodyPostWebRequest(String url, String body) {
		super(url, new ReaderInputStream(new StringReader(body)),
				MediaType.APPLICATION_JSON + "; charset=UTF-8");
		setHeaderField("Accept", MediaType.APPLICATION_JSON);
	}
	
}
