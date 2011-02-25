package org.ocwiki.controller.action.answer;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.db.dao.MultichoiceQuestionDAO;

public class DeleteAction extends AbstractResourceAction<MultichoiceQuestion> {

	@Override
	public void performImpl() throws IOException, ServletException {
		resource = MultichoiceQuestionDAO.fetchById(getParams().getLong("question"));
		MultichoiceQuestion question = resource.getArticle().copy();
		question.getChoices().get(getParams().getInt("answer"));
		saveNewRevision(resource, question);
		
		setNextAction("question.view&id=" + resource.getId());
	}

}
