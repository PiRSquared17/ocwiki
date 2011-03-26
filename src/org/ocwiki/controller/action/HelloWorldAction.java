package org.ocwiki.controller.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

public class HelloWorldAction extends AbstractAction {

	private Date currentTime;

	@Override
	protected void performImpl() throws IOException, ServletException {
		title("Hello world");
		currentTime = new Date();
	}
	
	public Date getCurrentTime() {
		return currentTime;
	}

}
