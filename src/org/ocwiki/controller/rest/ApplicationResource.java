package org.ocwiki.controller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ocwiki.conf.Config;

@Path("/app")
public class ApplicationResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String infomation() {
		return Config.get().getSiteName();
	}
	
}
