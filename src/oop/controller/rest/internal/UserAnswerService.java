package oop.controller.rest.internal;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import oop.controller.rest.AbstractResource;
import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.ResourceCustomization;
import oop.data.User;
import oop.data.UserAnswer;
import oop.db.dao.BaseQuestionDAO;
import oop.db.dao.ResourceCustomizationDAO;
import oop.db.dao.UserAnswerDAO;

import com.oreilly.servlet.ParameterNotFoundException;

@Path(UserAnswerService.PATH)
public class UserAnswerService extends AbstractResource {

	public static final String PATH = "/user_answers";

	@GET
	@Path("/{resourceId: \\d+}")
	public ListResult<UserAnswer> retrieve(
			@PathParam("resourceId") long resourceId) {
		User user = getUserNullSafe();
		List<UserAnswer> userAnswers = UserAnswerDAO.fetchByQuestionAndUser(
				resourceId, user.getId());
		return new ListResult<UserAnswer>(userAnswers);
	}

	@POST
	public ObjectResult<Boolean> answer() throws NumberFormatException,
			ParameterNotFoundException {
		Resource<BaseQuestion> resource = BaseQuestionDAO.fetchById(getParams()
				.getLong("q"));
		List<Answer> answers = resource.getArticle().getAnswers();
		int count = getParams().count("a");
		boolean choices[] = new boolean[answers.size()];
		for (int i = 0; i < count; i++) {
			choices[getParams().getIndexedInt("a", i)] = true;
		}
		boolean correct = true;
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).isCorrect() != choices[i]) {
				correct = false;
				break;
			}
		}
		// save to db
		User user = getUserNullSafe();
		UserAnswer userAnswer = new UserAnswer(resource, user, correct);
		UserAnswerDAO.persist(userAnswer);
		if (correct) {
			ResourceCustomization customization = ResourceCustomizationDAO
					.fetchByResourceAndUser(resource.getId(), user.getId());
			customization.setDone(true);
			ResourceCustomizationDAO.persist(customization);
		}
		return new ObjectResult<Boolean>(correct);
	}

}
