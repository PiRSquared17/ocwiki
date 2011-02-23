package org.ocwiki.controller.action.section;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.data.Section;
import org.ocwiki.data.Test;
import org.ocwiki.db.dao.ResourceDAO;

public class MoveUpAction extends AbstractResourceAction<Test> {
	
	private Test test;
	
	@Override
	public void performImpl() throws IOException, ServletException {
		resource = ResourceDAO.fetchById(getParams().getLong("testid"));
		test = resource.getArticle().copy();
		int sectionIndex = getParams().getInt("id");
		Section section = test.getSections().remove(sectionIndex);
		test.getSections().add(sectionIndex+1, section);
		saveNewRevision(resource, test);
		
		setNextAction("test.view&id=" + resource.getId());
	}

}
