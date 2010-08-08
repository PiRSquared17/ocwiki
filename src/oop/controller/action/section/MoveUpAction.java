package oop.controller.action.section;

import oop.controller.action.AbstractAction;
import oop.db.dao.SectionDAO;

public class MoveUpAction extends AbstractAction {
	
	@Override
	public void performImpl() throws Exception {
		long sectionId = getParams().getLong("su_id");
		long testId = getParams().getLong("su_testid");
		
		SectionDAO.moveUp(sectionId);
		
		setNextAction("test.view&tv_id=" + testId);
	}

}
