package org.ocwiki.controller.action.question;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Status;
import org.ocwiki.db.dao.MultichoiceQuestionDAO;

public class DeleteAction extends AbstractAction {
	
	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			int count = getParams().count("ql_questions");
			for (int i = 0; i < count; i++) {
				long id = getParams().getIndexedLong("ql_questions", i);
				MultichoiceQuestionDAO.fetchById(id).setStatus(Status.DELETED);
			}
			addMessage("Đã xóa " + count + " mục.");
			setNextAction("question.list&ql_submit=");
		} catch (NumberFormatException ex) {
			throw new ActionException("ID không hợp lệ.");
		}
	}

}
