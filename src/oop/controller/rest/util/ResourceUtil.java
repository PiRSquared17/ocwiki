package oop.controller.rest.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;

public final class ResourceUtil {

	private ResourceUtil() {
	}
	
	public static void assertFound(Object obj) {
		if (obj == null) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND)
					.entity(new ErrorResult("not found")).build());
		}
	}
	
	public static void assertParamNotEmpty(String name, String value) {
		if (StringUtils.isEmpty(value)) {
			throw new WebApplicationException(Response.status(
					Status.BAD_REQUEST).entity(
					new InvalidParamResult("empty", name, value)).build());
		}
	}
	
}
