package org.ocwiki.controller.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.ocwiki.controller.rest.util.ListResult;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.ResourceSearchReport;
import org.ocwiki.db.dao.ArticleDAO;


@Path(QuestionResource.PATH)
public class QuestionResource extends AbstractResource {

	public static final String PATH = "/MultichoiceQuestion";

	@GET
	@Path("/related/{resourceID: \\d+}")
	public ListResult<ResourceSearchReport<MultichoiceQuestion>> listByRelatedResource(
			@PathParam("resourceID") long resourceID) {
		List<ResourceSearchReport<MultichoiceQuestion>> listRelatedTest = ArticleDAO
				.fetchRelated(MultichoiceQuestion.class, resourceID, 0, 5);
		return new ListResult<ResourceSearchReport<MultichoiceQuestion>>(listRelatedTest);
	}
}