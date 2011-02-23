package org.ocwiki.controller.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.ocwiki.controller.rest.util.ListResult;
import org.ocwiki.data.BaseQuestion;
import org.ocwiki.data.ResourceSearchReport;
import org.ocwiki.db.dao.ArticleDAO;


@Path(BaseQuestionResource.PATH)
public class BaseQuestionResource extends AbstractResource {

	public static final String PATH = "/BaseQuestion";

	@GET
	@Path("/related/{resourceID: \\d+}")
	public ListResult<ResourceSearchReport<BaseQuestion>> listByRelatedResource(
			@PathParam("resourceID") long resourceID) {
		List<ResourceSearchReport<BaseQuestion>> listRelatedTest = ArticleDAO
				.fetchRelated(BaseQuestion.class, resourceID, 0, 5);
		return new ListResult<ResourceSearchReport<BaseQuestion>>(listRelatedTest);
	}
}