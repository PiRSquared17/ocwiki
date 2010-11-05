package oop.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.persistence.HibernateUtil;


public class RebuildIndexAction extends AbstractAction {

	@Override
	protected void performImpl() throws IOException, ServletException {
	}

	/**
	 * Call it only once!!!
	 * @return
	 */
	public String getRun() {
		return HibernateUtil.rebuildIndex();
	}

}
