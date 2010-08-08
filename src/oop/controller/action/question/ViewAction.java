package oop.controller.action.question;

import oop.controller.action.AbstractAction;
import oop.data.BaseQuestion;
import oop.db.dao.BaseQuestionDAO;

public class ViewAction extends AbstractAction {
	
	private BaseQuestion question;

	@Override
	public void performImpl() throws Exception {
		try {
			long id = getParams().getLong("qv_id");
			question = BaseQuestionDAO.fetchById(id);

			if (question == null) {
				error("Không tìm thấy câu hỏi có mã số " + id);
			} else {
				title("Câu hỏi "+question.getId());
			}
		} catch (NumberFormatException ex) {
			error("Id không hợp lệ: " + getParams().getString("id"));
		}
	}
	
	public BaseQuestion getQuestion() {
		return question;
	}
	
}
