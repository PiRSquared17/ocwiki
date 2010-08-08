package oop.controller.action.answer;

import java.util.Iterator;

import oop.controller.action.AbstractAction;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.db.dao.BaseQuestionDAO;

public class DeleteAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		long questionId = getParams().getLong("question");
		long answerId = getParams().getLong("answer");

		BaseQuestion question = BaseQuestionDAO.fetchById(questionId);
		for (Iterator<Answer> iter = question.getAnswers().iterator(); iter
				.hasNext();) {
			Answer answer = iter.next();
			if (answer.getId() == answerId) {
				iter.remove();
			}
		}
		
		setNextAction("question.view&qv_id=" + questionId);
	}

}
