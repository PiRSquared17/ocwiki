package oop.controller.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import oop.controller.rest.util.ListResult;
import oop.data.ResourceSearchReport;
import oop.data.Test;
import oop.db.dao.ArticleDAO;

@Path("/tests")
public class TestResource extends AbstractResource {

	@GET
	@Path("/related/{resourceID: \\d+}")
	public ListResult<ResourceSearchReport<Test>> listByRelatedResource(
			@PathParam("resourceID") long resourceID) {
		List<ResourceSearchReport<Test>> listRelatedTest = ArticleDAO
				.fetchRelated(Test.class, resourceID, 1, 5);
		return new ListResult<ResourceSearchReport<Test>>(listRelatedTest);
	}

}