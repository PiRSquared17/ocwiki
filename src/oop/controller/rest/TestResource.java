package oop.controller.rest;

import javax.ws.rs.Path;

import oop.controller.rest.AbstractResource;
import oop.controller.rest.util.ObjectResult;
import oop.data.Test;
import oop.data.Resource;
import oop.data.Revision;
import oop.data.User;
import oop.db.dao.ResourceDAO;
import oop.util.SessionUtils;

@Path("/tests")
public class TestResource extends AbstractResource  {

	public ObjectResult<Test> add(Test test)
			throws Exception {
		User user = SessionUtils.getUser(getSession());
		ResourceDAO.create(user, Test.class, test);
		return new ObjectResult<Test>(test);
	}

	public ObjectResult<Test> get(long resourceId) throws Exception {
		Resource<Test> resource = getResourceSafe(resourceId,
				Test.class);
		Test test = resource.getArticle();
		return new ObjectResult<Test>(test);
	}

	public ObjectResult<Test> update(long resourceId,
			Revision<Test> data) throws Exception {
		Resource<Test> resource = getResourceSafe(resourceId,
				Test.class);
		saveNewRevision(resource, data.getArticle(), data.getSummary(), data
				.isMinor());
		return new ObjectResult<Test>(resource.getArticle());
	}

}
