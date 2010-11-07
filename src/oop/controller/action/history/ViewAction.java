package oop.controller.action.history;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.History;
import oop.db.dao.HistoryDAO;

public class ViewAction extends AbstractAction {

	private History history;

	@Override
	protected void performImpl() throws IOException, ServletException {
		if (!getParams().hasParameter("id")) {
			throw new ActionException("Bạn cần chọn bài làm muốn xem.");
		}
		history = HistoryDAO.fetchById(getParams().getInt("id"));
		if (!history.getUser().equals(getUser())) {
			throw new ActionException("Bạn không thể xem bài làm của người khác.");
		}
	}
	
	public History getHistory() {
		return history;
	}

}
