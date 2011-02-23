package org.ocwiki.controller.action.answer;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.data.BaseQuestion;
import org.ocwiki.db.dao.BaseQuestionDAO;

public class DeleteAction extends AbstractResourceAction<BaseQuestion> {

	@Override
	public void performImpl() throws IOException, ServletException {
		resource = BaseQuestionDAO.fetchById(getParams().getLong("question"));
		BaseQuestion question = resource.getArticle().copy();
		question.getAnswers().get(getParams().getInt("answer"));
		saveNewRevision(resource, question);
		
		setNextAction("question.view&id=" + resource.getId());
	}

}
