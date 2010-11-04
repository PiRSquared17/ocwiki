package oop.controller.action.teststruct;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Status;
import oop.db.dao.ResourceDAO;

public class DeleteAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			long teststructureId = getParams().getLong("teststructureId");
			ResourceDAO.fetchById(teststructureId).setStatus(Status.DELETED);
			setNextAction("teststruct.view");
		} catch (NumberFormatException ex) {
			throw new ActionException("ID khong hop le");
		}
	}
}
