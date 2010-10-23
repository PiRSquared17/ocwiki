package oop.controller.rest;

import javax.ws.rs.Path;

@Path(FileResource.PATH)
public class FileResource extends AbstractResource {
	
	public static final String PATH = "/files";

}
