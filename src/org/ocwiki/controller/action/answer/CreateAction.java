package org.ocwiki.controller.action.answer;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Choice;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Text;
import org.ocwiki.db.dao.MultichoiceQuestionDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractResourceAction<MultichoiceQuestion> {

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			resource = MultichoiceQuestionDAO.fetchById(getParams()
					.getLong("question"));
			question = resource.getArticle().copy();
		} catch (NumberFormatException e) {
			throw new ActionException("Mã câu hỏi không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			throw new ActionException("Bạn cần chọn câu hỏi.");
		}

		title("Tạo câu trả lời mới" );

		if (getParams().hasParameter("submit")) {
			Choice choice = new Choice();

			try {
				String content = getParams().getString("content");
				choice.setContent(new Text(content));
			} catch (ParameterNotFoundException e) {
				addError("content", "Bạn cần nhập nội dung phương án trả lời.");
			}

			boolean correct = getParams().getBoolean("correct", false);
			choice.setCorrect(correct);

			if (hasNoErrors()) {
				question.getChoices().add(choice);
				saveNewRevision(resource, question);

				if ("more".equals(getParams().get("submit"))) {
					setNextAction("answer.create&question=" + resource.getId()
							+ "&content=&correct=&submit=");
				} else {
					setNextAction("question.view&id=" + resource.getId());
				}
			}
		}
	}

	private MultichoiceQuestion question;

	public MultichoiceQuestion getQuestion() {
		return question;
	}

}
