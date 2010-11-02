package oop.controller.rest.internal;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.AbstractResource;
import oop.controller.rest.util.ListResult;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.ResourceCustomization;
import oop.data.User;
import oop.data.AnswerAttempt;
import oop.db.dao.BaseQuestionDAO;
import oop.db.dao.ResourceCustomizationDAO;
import oop.db.dao.AnswerAttemptDAO;
import oop.util.Utils;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.oreilly.servlet.ParameterNotFoundException;

@Path(AnswerAttemptService.PATH)
public class AnswerAttemptService extends AbstractResource {

	public static final String PATH = "/answer_attempts";

	@GET
	@Path("/{resourceId: \\d+}")
	public ListResult<AnswerAttempt> retrieve(
			@PathParam("resourceId") long resourceId) {
		User user = getUserNullSafe();
		List<AnswerAttempt> userAnswers = AnswerAttemptDAO.fetchByQuestionAndUser(
				resourceId, user.getId());
		return new ListResult<AnswerAttempt>(userAnswers);
	}

	@POST
	@Path("/{resourceId: \\d+}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public JSONObject answer(@PathParam("resourceId") long resourceId)
			throws NumberFormatException, ParameterNotFoundException,
			JSONException {
		Resource<BaseQuestion> resource = BaseQuestionDAO.fetchById(resourceId);
		List<Answer> answers = resource.getArticle().getAnswers();
		int choiceCount = getParams().count("answers");
		boolean choosed[] = new boolean[answers.size()];
		boolean correct = true;
		for (int i = 0; i < choiceCount; i++) {
			int answerId = getParams().getIndexedInt("answers", i);
			int answerIndex = Utils.indexOf(answers, answerId);
			if (answerIndex < 0) {
				correct = false;
				break;
			}
			choosed[answerIndex] = true;
		}
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).isCorrect() != choosed[i]) {
				correct = false;
				break;
			}
		}
		// save to db
		User user = getUserNullSafe();
		AnswerAttempt userAnswer = new AnswerAttempt(resource, user, correct);
		AnswerAttemptDAO.persist(userAnswer);
		if (correct) {
			ResourceCustomization customization = ResourceCustomizationDAO
					.fetchByResourceAndUser(resource.getId(), user.getId());
			if (customization == null) {
				customization = new ResourceCustomization(resource, user);
			}
			customization.setDone(true);
			ResourceCustomizationDAO.persist(customization);
		}
		long attemptCount = AnswerAttemptDAO.countByQuestionAndUser(resourceId,
				user.getId());
		return new JSONObject().put("result", new JSONObject().put("correct",
				correct).put("count", attemptCount));
	}

}
