package oop.controller.action.section;

import oop.controller.action.AbstractAction;
import oop.data.Section;
import oop.data.Test;
import oop.db.dao.ResourceDAO;

public class MoveUpAction extends AbstractAction<Test> {
	
	private Test test;
	
	@Override
	public void performImpl() throws Exception {
		resource = ResourceDAO.fetchById(getParams().getLong("testid"));
		test = resource.getArticle().copy();
		int sectionIndex = getParams().getInt("id");
		Section section = test.getSections().remove(sectionIndex);
		test.getSections().add(sectionIndex+1, section);
		saveNewRevision(resource, test);
		
		setNextAction("test.view&id=" + resource.getId());
	}

}
