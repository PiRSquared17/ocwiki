package oop.controller.rest.resources.basequestion;

import javax.ws.rs.Path;

import oop.controller.rest.AbstractResource;
import oop.controller.rest.util.ObjectResult;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.Revision;
import oop.data.Topic;
import oop.data.User;
import oop.db.dao.ArticleDAO;
import oop.db.dao.ResourceDAO;
import oop.db.dao.TopicDAO;
import oop.util.SessionUtils;

import org.apache.commons.lang.StringUtils;

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
		assertParamValid(data.getArticle() != null, "article", "null");
		assertParamValid(!StringUtils.isEmpty(data.getSummary()), "summary",
				"empty");
		if (resource.getArticle().getId() != data.getArticle().getId()) {
			throw invalidParam("basever", "old version");
		}
		
		BaseQuestion question = data.getArticle().copy();
		question.getTopics().clear();
		for (Resource<Topic> topic : data.getArticle().getTopics()) {
			assertParamValid(topic != null, "topic", "null");
			topic = TopicDAO.fetchById(topic.getId());
			assertParamValid(topic != null, "topic", "not found");
			question.getTopics().add(topic);
		}
		ArticleDAO.persist(question);

		saveNewRevision(resource, question, data.getSummary(), data.isMinor());
		return new ObjectResult<BaseQuestion>(resource.getArticle());
	}

}
