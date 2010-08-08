package oop.controller.action.section;

import oop.controller.action.AbstractAction;
import oop.db.dao.SectionDAO;

public class MoveDownAction extends AbstractAction {
	
	@Override
	public void performImpl() throws Exception {
		long sectionId = getParams().getLong("sw_id");
		long testId = getParams().getLong("sw_testid");
		
		SectionDAO.moveDown(sectionId);
		
		setNextAction("test.view&tv_id=" + testId);
	}

}
