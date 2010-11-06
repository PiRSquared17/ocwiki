package oop.controller.action.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.data.log.ResourceLog;
import oop.db.dao.LogDAO;

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
