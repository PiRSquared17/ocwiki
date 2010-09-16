package oop.controller.rest;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import oop.controller.rest.util.ListResult;
import oop.data.Article;
import oop.data.Resource;
import oop.data.ResourceSearchReport;
import oop.data.Test;
import oop.data.User;
import oop.db.dao.ArticleDAO;
import oop.db.dao.UserDAO;

@Path("/tests")
public class TestResource extends AbstractResource {

	@GET
	@Path("/related/{resourceID: \\d+}")
	public ListResult<Test> listByRelatedResource(
			@PathParam("resourceID") long resourceID) {
		this.assertParamFound("resourceID");
		List<ResourceSearchReport<Test>> listRelatedTest = 
			ArticleDAO.fetchRelated(Test.class, resourceID, 1, 5);
		return new ListResult<Test>(listRelatedTest);
	}

}