package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import oop.util.JsonUtils;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JSONContextResolver implements ContextResolver<JAXBContext> {

	public JSONContextResolver() {
	}

	public JAXBContext getContext(Class<?> objectType) {
		if (JsonUtils.TYPE_SET.contains(objectType)) {
			return JsonUtils.JAXB_CONTEXT;
		}
		return null;
	}
}
