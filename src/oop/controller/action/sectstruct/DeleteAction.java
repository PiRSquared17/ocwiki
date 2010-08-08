package oop.controller.action.sectstruct;

import oop.controller.action.AbstractAction;
import oop.db.dao.SectionStructureDAO;

public class DeleteAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		long sectionStructureId = getParams().getLong("ssd_id");
		SectionStructureDAO.drop(sectionStructureId, true);

		long testStructureId = getParams().getLong("ssd_testid");
		setNextAction("teststruct.view&tsv_id=" + testStructureId);

		SectionStructureDAO.drop(sectionStructureId, true);

		setNextAction("teststruct.view&tsv_id=" + testStructureId);
	}

}
