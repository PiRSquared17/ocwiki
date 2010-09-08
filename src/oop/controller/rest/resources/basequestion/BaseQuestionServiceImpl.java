package oop.controller.rest.resources.basequestion;

import javax.ws.rs.Path;

import oop.controller.rest.AbstractResource;
import oop.controller.rest.util.ObjectResult;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.Revision;
import oop.data.User;
import oop.db.dao.ResourceDAO;
import oop.util.SessionUtils;

@Path("/questions")
public class BaseQuestionServiceImpl extends AbstractResource implements
		BaseQuestionService {

	@Override
	public ObjectResult<BaseQuestion> add(BaseQuestion question)
			throws Exception {
		User user = SessionUtils.getUser(getSession());
		ResourceDAO.create(user, BaseQuestion.class, question);
		return new ObjectResult<BaseQuestion>(question);
	}

	@Override
	public ObjectResult<BaseQuestion> get(long resourceId) throws Exception {
		Resource<BaseQuestion> resource = getResourceSafe(resourceId,
				BaseQuestion.class);
		BaseQuestion question = resource.getArticle();
		return new ObjectResult<BaseQuestion>(question);
	}

	@Override
	public ObjectResult<BaseQuestion> update(long resourceId,
			Revision<BaseQuestion> data) throws Exception {
		Resource<BaseQuestion> resource = getResourceSafe(resourceId,
				BaseQuestion.class);
		saveNewRevision(resource, data.getArticle(), data.getSummary(), data
				.isMinor());
		return new ObjectResult<BaseQuestion>(resource.getArticle());
	}

}
