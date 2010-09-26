package oop.controller.rest;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.data.File;
import oop.data.Resource;

@Path(FileResource.PATH)
public class FileResource {
	
	public static final String PATH = "/files";

	@Path("{id: \\d+}")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Resource<File> upload(@PathParam("id") long id,
			@FormParam("file") InputStream file) {
		return null;
	}
	
}
