package org.ocwiki.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;

public class HomeAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		title("Ocwiki");
	}

}
