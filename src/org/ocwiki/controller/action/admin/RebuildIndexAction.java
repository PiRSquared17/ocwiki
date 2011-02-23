package org.ocwiki.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.persistence.HibernateUtil;


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
