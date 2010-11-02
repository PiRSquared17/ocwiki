package oop.controller.rest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

//@Provider
// disabled
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class CustomizedJacksonJsonProvider extends JacksonJsonProvider {
	
	public CustomizedJacksonJsonProvider() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
		// make deserializer use JAXB annotations (only)
	    objectMapper.getDeserializationConfig().setAnnotationIntrospector(introspector);
	    // make serializer use JAXB annotations (only)
	    objectMapper.getSerializationConfig().setAnnotationIntrospector(introspector);
		setMapper(objectMapper);
	}
	
	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return super.isReadable(type, genericType, annotations, mediaType);
	}
	
	@Override
	public Object readFrom(Class<Object> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException {
		return super.readFrom(type, genericType, annotations, mediaType, httpHeaders,
				entityStream);
	}
	
}
