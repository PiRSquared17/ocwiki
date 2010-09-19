package oop.controller.action.question;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.db.dao.BaseQuestionDAO;

public class EnhancedViewAction extends AbstractAction<BaseQuestion> {
	
	private BaseQuestion question;

	@Override
	public void performImpl() throws Exception {
		try {
			resource = BaseQuestionDAO.fetchById(getParams().getLong("id"));
			question = resource.getArticle();

			if (question == null) {
				throw new ActionException("Không tìm thấy câu hỏi có mã số " + getParams().get("id"));
			} else {
				title("Câu hỏi "+question.getId());
			}
		} catch (NumberFormatException ex) {
			throw new ActionException("Id không hợp lệ: " + getParams().get("id"));
		}
	}
	
	public BaseQuestion getQuestion() {
		return question;
	}
	
}
