package oop.controller.action.section;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractResourceAction;
import oop.data.Section;
import oop.data.Test;
import oop.db.dao.ResourceDAO;

public class MoveDownAction extends AbstractResourceAction<Test> {
	
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
