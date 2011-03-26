package org.ocwiki.controller.rest;

import javax.ws.rs.Path;

import org.ocwiki.util.SessionUtils;

@Path(LogoutService.PATH)
public class LogoutService extends AbstractResource {

	public static final String PATH = "/logout";

	@Path("/")
	public String logout() {
		SessionUtils.logout(getSession());
		return "";
	}
	
}
