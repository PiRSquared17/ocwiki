package org.ocwiki.controller.action.answer;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Answer;
import org.ocwiki.data.BaseQuestion;
import org.ocwiki.data.Text;
import org.ocwiki.db.dao.BaseQuestionDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractResourceAction<BaseQuestion> {

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			resource = BaseQuestionDAO.fetchById(getParams()
					.getLong("question"));
			question = resource.getArticle().copy();
		} catch (NumberFormatException e) {
			throw new ActionException("Mã câu hỏi không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			throw new ActionException("Bạn cần chọn câu hỏi.");
		}

		title("Tạo câu trả lời mới" );

		if (getParams().hasParameter("submit")) {
			Answer answer = new Answer();

			try {
				String content = getParams().getString("content");
				answer.setContent(new Text(content));
			} catch (ParameterNotFoundException e) {
				addError("content", "Bạn cần nhập nội dung phương án trả lời.");
			}

			boolean correct = getParams().getBoolean("correct", false);
			answer.setCorrect(correct);

			if (hasNoErrors()) {
				question.getAnswers().add(answer);
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

	private BaseQuestion question;

	public BaseQuestion getQuestion() {
		return question;
	}

}
