package org.ocwiki.controller.rest.internal;

import javax.ws.rs.Path;

import org.ocwiki.controller.rest.AbstractResource;

@Path(TestSolvingService.PATH)
public class TestSolvingService extends AbstractResource {

	public static final String PATH = "test_solve";
	
	public void solve() {
		
	}
	
}
