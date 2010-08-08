package oop.controller.action.sectstruct;

import oop.controller.action.AbstractAction;
import oop.db.dao.SectionStructureDAO;

public class MoveDownAction extends AbstractAction {
	
	@Override
	public void performImpl() throws Exception {
		long sectionId = getParams().getLong("ssw_id");
		long testId = getParams().getLong("ssw_testid");
		
		SectionStructureDAO.moveDown(sectionId);
		
		setNextAction("teststruct.view&tsv_id=" + testId);
	}

}
