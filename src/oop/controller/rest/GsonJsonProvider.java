package oop.controller.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import com.google.gson.Gson;

//@Provider
//@Consumes(MediaType.APPLICATION_JSON)
public class GsonJsonProvider implements MessageBodyReader {

	@Override
	public boolean isReadable(Class arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		return true;
	}

	@Override
	public Object readFrom(Class arg0, Type type, Annotation[] arg2,
			MediaType arg3, MultivaluedMap arg4, InputStream inp)
			throws IOException, WebApplicationException {
		return new Gson().fromJson(new InputStreamReader(inp), type);
	}

}
