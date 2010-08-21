package oop.controller.action.teststruct;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Status;
import oop.db.dao.ResourceDAO;

public class DeleteAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		try {
			long teststructureId = getParams().getLong("teststructureId");
			ResourceDAO.fetchById(teststructureId).setStatus(Status.DELETED);
			setNextAction("teststruct.view");
		} catch (NumberFormatException ex) {
			throw new ActionException("ID khong hop le");
		}
	}
}
