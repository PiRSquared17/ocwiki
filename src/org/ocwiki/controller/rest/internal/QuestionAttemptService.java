package org.ocwiki.controller.rest.internal;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.ocwiki.controller.rest.AbstractResource;
import org.ocwiki.controller.rest.util.ListResult;
import org.ocwiki.data.Choice;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.data.ResourceCustomization;
import org.ocwiki.data.User;
import org.ocwiki.data.QuestionAttempt;
import org.ocwiki.db.dao.MultichoiceQuestionDAO;
import org.ocwiki.db.dao.ResourceCustomizationDAO;
import org.ocwiki.db.dao.QuestionAttemptDAO;
import org.ocwiki.util.Utils;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.oreilly.servlet.ParameterNotFoundException;

@Path(QuestionAttemptService.PATH)
public class QuestionAttemptService extends AbstractResource {

	public static final String PATH = "/q_attempts";

	@GET
	@Path("/{resourceId: \\d+}")
	public ListResult<QuestionAttempt> retrieve(
			@PathParam("resourceId") long resourceId) {
		User user = getUserNullSafe();
		List<QuestionAttempt> userAnswers = QuestionAttemptDAO.fetchByQuestionAndUser(
				resourceId, user.getId());
		return new ListResult<QuestionAttempt>(userAnswers);
	}

	@POST
	@Path("/{resourceId: \\d+}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public JSONObject answer(@PathParam("resourceId") long resourceId)
			throws NumberFormatException, ParameterNotFoundException,
			JSONException {
		Resource<MultichoiceQuestion> resource = MultichoiceQuestionDAO.fetchById(resourceId);
		List<Choice> answers = resource.getArticle().getChoices();
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
		QuestionAttempt userAnswer = new QuestionAttempt(resource, user, correct);
		QuestionAttemptDAO.persist(userAnswer);
		if (correct) {
			ResourceCustomization customization = ResourceCustomizationDAO
					.fetchByResourceAndUser(resource.getId(), user.getId());
			if (customization == null) {
				customization = new ResourceCustomization(resource, user);
			}
			customization.setDone(true);
			ResourceCustomizationDAO.persist(customization);
		}
		long attemptCount = QuestionAttemptDAO.countByQuestionAndUser(resourceId,
				user.getId());
		return new JSONObject().put("result", new JSONObject().put("correct",
				correct).put("count", attemptCount));
	}

}
