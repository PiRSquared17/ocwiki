package oop.controller.rest.resources.basequestion;

import java.util.List;

import javax.ws.rs.Path;

import oop.controller.rest.AbstractResource;
import oop.controller.rest.WebServiceUtils;
import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.ResourceSearchReport;
import oop.data.Revision;
import oop.data.Text;
import oop.data.Topic;
import oop.data.User;
import oop.db.dao.ArticleDAO;
import oop.db.dao.ResourceDAO;
import oop.db.dao.TopicDAO;
import oop.util.SessionUtils;

import org.apache.commons.collections.CollectionUtils;

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
		WebServiceUtils.assertValid(resource.getArticle().getId() == data
				.getArticle().getId(), "old version");

		BaseQuestion question = data.getArticle().copy();
		question.getTopics().clear();
		for (Resource<Topic> topic : data.getArticle().getTopics()) {
			WebServiceUtils.assertValid(topic != null, "topic is empty");
			topic = TopicDAO.fetchById(topic.getId());
			WebServiceUtils.assertValid(topic != null, "topic not found");
			question.getTopics().add(topic);
		}
		ArticleDAO.persist(question);

		saveNewRevision(resource, question, data.getSummary(), data.isMinor());
		return new ObjectResult<BaseQuestion>(resource.getArticle());
	}

	private void validate(BaseQuestion question) {
		WebServiceUtils.assertValid(question != null, "question is empty");
		WebServiceUtils.assertValid(Text.isNotBlank(question.getContent()),
				"question content is blank");
		WebServiceUtils.assertValid(
				CollectionUtils.size(question.getAnswers()) >= 2,
				"too little answers");
		WebServiceUtils.assertValid(
				CollectionUtils.size(question.getAnswers()) < 10,
				"too many answers");
		boolean hasCorrect = false;
		for (Answer answer : question.getAnswers()) {
			WebServiceUtils.assertValid(answer != null, "answer is empty");
			WebServiceUtils.assertValid(Text.isNotBlank(answer.getContent()),
					"answer content is blank");
			if (answer.isCorrect()) {
				hasCorrect = true;
			}
		}
		WebServiceUtils.assertValid(hasCorrect, "no correct answer");
	}

	@Override
	public ListResult<ResourceSearchReport<BaseQuestion>> listByRelatedResource(
			long resourceID) {
		List<ResourceSearchReport<BaseQuestion>> listRelatedTest = ArticleDAO
				.fetchRelated(BaseQuestion.class, resourceID, 0, 5);
		return new ListResult<ResourceSearchReport<BaseQuestion>>(listRelatedTest);
	}

}
