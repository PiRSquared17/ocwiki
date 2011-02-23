package org.ocwiki.controller.action.statistics;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;


public class ListAction extends AbstractAction {

		@Override
		public void performImpl() throws IOException, ServletException {
			title("Thống kê");
		}

}
