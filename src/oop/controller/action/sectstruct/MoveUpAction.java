package oop.controller.action.sectstruct;

import oop.controller.action.AbstractAction;
import oop.db.dao.SectionStructureDAO;

public class MoveUpAction extends AbstractAction {
	
	@Override
	public void performImpl() throws Exception {
		long sectionId = getParams().getLong("ssu_id");
		long testId = getParams().getLong("ssu_testid");
		
		SectionStructureDAO.moveUp(sectionId);
		
		setNextAction("teststruct.view&tsv_id=" + testId);
	}

}
