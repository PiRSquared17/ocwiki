package oop.controller.action.question;

import oop.controller.action.AbstractAction;
import oop.data.BaseQuestion;
import oop.db.dao.BaseQuestionDAO;

public class EnhancedViewAction extends AbstractAction {
	
	private BaseQuestion question;

	@Override
	public void performImpl() throws Exception {
		try {
			question = BaseQuestionDAO.fetchById(getParams().getLong("id"));

			if (question == null) {
				error("Không tìm thấy câu hỏi có mã số " + getParams().get("id"));
			} else {
				title("Câu hỏi "+question.getId());
			}
		} catch (NumberFormatException ex) {
			error("Id không hợp lệ: " + getParams().get("id"));
		}
	}
	
	public BaseQuestion getQuestion() {
		return question;
	}
	
}
