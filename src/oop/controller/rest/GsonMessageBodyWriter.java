package oop.controller.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import oop.util.GsonFactory;

/**
 * Sử dụng GSON để chuyển đối tượng thành chuỗi JSON. Vấn đề chính với phương
 * pháp này là GSON đọc giá trị từ field chứ không thông qua getter khiến các
 * proxy của Hibernate đều có giá trị rỗng.
 * 
 * @author cumeo89
 * 
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class GsonMessageBodyWriter implements MessageBodyWriter<Object> {

	@Override
	public long getSize(Object obj, Class<?> type, Type genericType,
			Annotation annotations[], MediaType mediaType) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation annotations[], MediaType mediaType) {
		return true;
	}

	@Override
	public void writeTo(Object obj, Class<?> type, Type genericType,
			Annotation annotations[], MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		OutputStreamWriter writer = new OutputStreamWriter(entityStream);
		try {
			GsonFactory.get().toJson(obj, genericType, writer);
		} finally {
			writer.close();
		}
	}

}
