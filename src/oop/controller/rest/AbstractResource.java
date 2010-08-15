package oop.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public abstract class AbstractResource {
	
	@Context
	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public HttpSession getSession() {
		return request.getSession();
	}

}
