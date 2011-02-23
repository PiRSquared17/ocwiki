package org.ocwiki.controller.action.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.log.ResourceLog;
import org.ocwiki.db.dao.LogDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class ChangeLogAction extends AbstractAction {

	private List<ResourceLog> logs;

	@Override
	protected void performImpl() throws IOException, ServletException {
		try {
			long resourceId = getParams().getLong("resid");
			int start = getParams().getInt("start");
			int size = getParams().getInt("length");
			logs = LogDAO.fetchByResource(resourceId, start, size);
		} catch (ParameterNotFoundException ex) {
		}
	}

	public List<ResourceLog> getLogs() {
		return logs;
	}
	
}
