package oop.controller.action.question;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.db.dao.BaseQuestionDAO;

public class ViewAction extends AbstractAction {
	
	private BaseQuestion question;
	private Resource<BaseQuestion> resource;

	@Override
	public void performImpl() throws Exception {
		try {
			resource = BaseQuestionDAO.fetchById(getParams().getLong("id"));
			if (resource == null) {
				throw new ActionException("Không tìm thấy câu hỏi");
			} 
			question = resource.getArticle();

			title("Câu hỏi " + resource.getId());
		} catch (NumberFormatException ex) {
			throw new ActionException("Id không hợp lệ: " + getParams().getString("id"));
		}
	}
	
	public BaseQuestion getQuestion() {
		return question;
	}
	
	public Resource<BaseQuestion> getResource() {
		return resource;
	}
	
}
