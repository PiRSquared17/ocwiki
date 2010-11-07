package oop.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;

public class HomeAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		title("Ocwiki");
	}

}
