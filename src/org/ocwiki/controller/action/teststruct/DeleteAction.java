package org.ocwiki.controller.action.teststruct;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Status;
import org.ocwiki.db.dao.ResourceDAO;

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
