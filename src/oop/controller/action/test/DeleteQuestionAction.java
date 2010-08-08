package oop.controller.action.test;

import oop.controller.action.AbstractAction;
import oop.data.Question;
import oop.db.dao.QuestionDAO;

public class DeleteQuestionAction extends AbstractAction {

	public DeleteQuestionAction() {
	}

	@Override
	public void performImpl() throws Exception {
		title("Gỡ bỏ câu hỏi khỏi đề thi");
		try {
			long questionId = getParams().getLong("id");
			Question question = QuestionDAO.fetchById(questionId);
			QuestionDAO.setDeleted(questionId, true);
			setNextAction("test.view&tv_id=" + question.getTest().getId());
		} catch (NumberFormatException ex) {
			error("ID không hợp lệ");
		}
	}

}
