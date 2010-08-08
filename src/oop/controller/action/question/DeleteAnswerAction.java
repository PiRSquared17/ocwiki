package oop.controller.action.question;

import oop.controller.action.AbstractAction;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.db.dao.AnswerDAO;

public class DeleteAnswerAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		try {
			long id = getParams().getLong("id");
			Answer answer = AnswerDAO.fetch(id);
			answer.setDeleted(true);
			BaseQuestion question = answer.getQuestion();
			setNextAction("question.view&qv_id=" + question.getId());
		} catch (NumberFormatException ex) {
			error("ID không hợp lệ");
		}
	}

}
