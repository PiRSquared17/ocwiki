package oop.controller.rest.internal;

import javax.ws.rs.Path;

import oop.controller.rest.AbstractResource;

@Path(TestSolvingService.PATH)
public class TestSolvingService extends AbstractResource {

	public static final String PATH = "test_solve";
	
	public void solve() {
		
	}
	
}
