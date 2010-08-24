package oop.controller.action.answer;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.Text;
import oop.db.dao.BaseQuestionDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
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

			if (!hasErrors()) {
				question.getAnswers().add(answer);
				saveNewRevision(resource, question);

				if ("more".equals(getParams().get("submit"))) {
					setNextAction("answer.create&question=" + resource.getId()
							+ "&content=&correct=&submit=");
				} else {
					setNextAction("question.view&id=" + question.getId());
				}
			}
		}
	}

	private Resource<BaseQuestion> resource;
	private BaseQuestion question;

	public Resource<BaseQuestion> getResource() {
		return resource;
	}

	public BaseQuestion getQuestion() {
		return question;
	}

}
