package oop.controller.action.answer;

import oop.controller.action.AbstractResourceAction;
import oop.data.BaseQuestion;
import oop.db.dao.BaseQuestionDAO;

public class DeleteAction extends AbstractResourceAction<BaseQuestion> {

	@Override
	public void performImpl() throws Exception {
		resource = BaseQuestionDAO.fetchById(getParams().getLong("question"));
		BaseQuestion question = resource.getArticle().copy();
		question.getAnswers().get(getParams().getInt("answer"));
		saveNewRevision(resource, question);
		
		setNextAction("question.view&id=" + resource.getId());
	}

}
