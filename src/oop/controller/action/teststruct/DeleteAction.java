package oop.controller.action.teststruct;

import oop.controller.action.AbstractAction;
import oop.db.dao.TestStructureDAO;

public class DeleteAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		try {
			long teststructureId = getParams().getLong("teststructureId");
			TestStructureDAO.setDeleted(teststructureId, true);
			setNextAction("teststruct.view");
		} catch (NumberFormatException ex) {
			error("ID khong hop le");
		}
	}
}
