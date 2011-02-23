package org.ocwiki.controller.action.test;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Section;
import org.ocwiki.data.Test;
import org.ocwiki.db.dao.ResourceDAO;

public class DeleteQuestionAction extends AbstractAction {

	public DeleteQuestionAction() {
	}

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			Resource<Test> resource = ResourceDAO.fetchById(getParams().getLong("test"));
			Test newTest = resource.getArticle().copy();
			int sectionIndex = getParams().getInt("section");
			Section newSection = newTest.getSections().get(sectionIndex).copy();
			newTest.getSections().set(sectionIndex, newSection);
			int questionIndex = getParams().getInt("question");
			newSection.getQuestions().remove(questionIndex);
			saveNewRevision(resource, newTest);
			
			setNextAction("test.view&id=" + resource.getId());
		} catch (NumberFormatException ex) {
			throw new ActionException("ID không hợp lệ");
		}
	}

}
