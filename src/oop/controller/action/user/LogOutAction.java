package oop.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.util.SessionUtils;

public class LogOutAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		SessionUtils.logout(getSession());
		setNextAction("homepage");
	}

}
