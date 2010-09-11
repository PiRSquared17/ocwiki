package oop.controller.action.user;

import oop.controller.action.AbstractAction;
import oop.util.SessionUtils;

public class LogOutAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		SessionUtils.setUser(getSession(), null);
		setNextAction("homepage");
	}

}
