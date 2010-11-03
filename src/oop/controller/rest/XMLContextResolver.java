package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

@Provider
@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class XMLContextResolver implements ContextResolver<JAXBContext> {

	private JAXBContext context;

	public XMLContextResolver() throws Exception {
		context = JAXBContext.newInstance(JSONContextResolver.TYPES);
	}
	
	@Override
	public JAXBContext getContext(Class<?> objectType) {
		if (JSONContextResolver.TYPE_SET.contains(objectType)) {
			return context;
		}
		return null;
	}

}
