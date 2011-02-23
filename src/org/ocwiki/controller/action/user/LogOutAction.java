package org.ocwiki.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.util.SessionUtils;

public class LogOutAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		SessionUtils.logout(getSession());
		setNextAction("homepage");
	}

}
