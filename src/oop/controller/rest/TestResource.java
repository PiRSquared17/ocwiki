package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import oop.controller.rest.util.ObjectResult;
import oop.data.Resource;
import oop.data.Revision;
import oop.data.Section;
import oop.data.Test;
import oop.data.User;
import oop.db.dao.ArticleDAO;
import oop.db.dao.ResourceDAO;
import oop.util.SessionUtils;

@Path("/tests")
public class TestResource extends AbstractResource  {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<Test> add(Test test)
			throws Exception {
		validate(test);
		User user = SessionUtils.getUser(getSession());
		ResourceDAO.create(user, Test.class, test);
		return new ObjectResult<Test>(test);
	}

	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<Test> get(long resourceId) throws Exception {
		Resource<Test> resource = getResourceSafe(resourceId,
				Test.class);
		Test test = resource.getArticle();
		return new ObjectResult<Test>(test);
	}

	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<Test> update(long resourceId,
			Revision<Test> data) throws Exception {
		Resource<Test> resource = getResourceSafe(resourceId, Test.class);
		validate(data.getArticle());
		WebServiceUtils.assertValid(resource.getArticle().getId() == data
				.getArticle().getId(), "old version");

		Test test = data.getArticle().copy();
        WebServiceUtils.copyTopics(test, data.getArticle());
	    ArticleDAO.persist(test);

		saveNewRevision(resource, data.getArticle(), data.getSummary(), data
				.isMinor());
		return new ObjectResult<Test>(resource.getArticle());
	}

	private void validate(Test test) {
		WebServiceUtils.assertValid(test != null, "test is empty");
		WebServiceUtils.assertValid(test.getContent().getId() <= 0
				|| StringUtils.isNotBlank(test.getContent().getText()),
				"test content is blank");
		WebServiceUtils.assertValid(test.getSections().size() > 0,
				"too little sections");
		for (Section section : test.getSections()) {
			WebServiceUtils.assertValid(section.getQuestions().size() > 0,
					"too little questions");
			WebServiceUtils.assertValid(section.getContent().getId() <= 0
					|| StringUtils.isNotBlank(section.getContent().getText()),
					"section content is blank");
		}
	}

}
