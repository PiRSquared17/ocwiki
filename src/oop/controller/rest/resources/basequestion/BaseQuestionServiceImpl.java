package oop.controller.rest.resources.basequestion;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;

import oop.controller.rest.AbstractResource;
import oop.controller.rest.util.ObjectResult;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.Revision;
import oop.data.Topic;
import oop.data.User;
import oop.db.dao.ArticleDAO;
import oop.db.dao.ResourceDAO;
import oop.db.dao.TopicDAO;
import oop.util.SessionUtils;

@Path("/questions")
public class BaseQuestionServiceImpl extends AbstractResource implements
		BaseQuestionService {

	@Override
	public ObjectResult<BaseQuestion> add(BaseQuestion question)
			throws Exception {
		validate(question);
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
		validate(data.getArticle());
		assertValid(resource.getArticle().getId() == data.getArticle().getId(),
				"old version");

		BaseQuestion question = data.getArticle().copy();
		question.getTopics().clear();
		for (Resource<Topic> topic : data.getArticle().getTopics()) {
			assertValid(topic != null, "topic is empty");
			topic = TopicDAO.fetchById(topic.getId());
			assertValid(topic != null, "topic not found");
			question.getTopics().add(topic);
		}
		ArticleDAO.persist(question);

		saveNewRevision(resource, question, data.getSummary(), data.isMinor());
		return new ObjectResult<BaseQuestion>(resource.getArticle());
	}

	private void validate(BaseQuestion question) {
		assertValid(question != null, "question is empty");
		assertValid(question.getContent().getId() <= 0
				|| StringUtils.isNotBlank(question.getContent().getText()),
				"question content is empty");
		assertValid(question.getAnswers().size() >= 2, "too litte answers");
		assertValid(question.getAnswers().size() < 10, "too many answers");
		boolean hasCorrect = false;
		for (Answer answer : question.getAnswers()) {
			assertValid(answer.getContent().getId() <= 0
					|| StringUtils.isNotBlank(answer.getContent().getText()),
					"answer content is empty");
			if (answer.isCorrect()) {
				hasCorrect = true;
			}
		}
		assertValid(hasCorrect, "no correct answer");
	}

}
