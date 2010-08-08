package oop.controller.action.question;

import oop.controller.action.AbstractAction;
import oop.db.dao.AnswerDAO;

import org.apache.commons.lang.StringUtils;

public class AddAnswerAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		title("Thêm câu trả lời");
		String submit = getParams().get("qa_submit");
		if ("add".equals(submit)) {
			try {
				long questionId = getParams().getLong("qa_question");
				String content = getParams().get("answer");
				boolean correct = getParams().getBoolean("correct", false);
				AnswerDAO.create(questionId, content, correct);
				
				if (getParams().hasParameter("qa_more")) {
					request.setAttribute("qa_clear", true);
				} else {
					setNextAction("question.view&qv_id=" + questionId);
				}
			} catch (NumberFormatException ex) {
				request.setAttribute("idErr", "Định dạng không hợp lệ");
			}
		}
	}
}
