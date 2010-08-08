package oop.controller.action.answer;

import oop.controller.action.AbstractAction;
import oop.data.BaseQuestion;
import oop.db.dao.AnswerDAO;
import oop.db.dao.BaseQuestionDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() {
		try {
			BaseQuestion question = BaseQuestionDAO.fetchById(getParams()
					.getLong("question"));

			title("Tạo câu trả lời mới");
			request.setAttribute("question", question);

			if (getParams().hasParameter("submit")) {
				// int priority = parser.getIntParameter("priority");

				String content = null;
				try {
					content = getParams().getString("content");
				} catch (ParameterNotFoundException e) {
					contentError = "Bạn cần nhập nội dung phương án trả lời.";
				}

				boolean correct = getParams().getBoolean("correct", false);

				if (contentError == null) {
					AnswerDAO.create(question.getId(), content, correct);
					if ("more".equals(getParams().get("submit"))) {
						setNextAction("answer.create&question="
								+ question.getId()
								+ "&content=&correct=&submit=");
					} else {
						setNextAction("question.view&qv_id=" + question.getId());
					}
				}
			}
		} catch (NumberFormatException e) {
			error("Mã câu hỏi không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			error("Bạn cần chọn câu hỏi.");
		}
	}

	private String contentError;

	public String getContentError() {
		return contentError;
	}

}
