package oop.controller.action.question;

import oop.controller.action.AbstractAction;
import oop.data.Status;
import oop.db.dao.BaseQuestionDAO;

public class DeleteAction extends AbstractAction {
	
	@Override
	public void performImpl() throws Exception {
		try {
			int count = getParams().count("ql_questions");
			for (int i = 0; i < count; i++) {
				long id = getParams().getIndexedLong("ql_questions", i);
				BaseQuestionDAO.fetchById(id).setStatus(Status.DELETED);
			}
			message("Đã xóa " + count + " mục.");
			setNextAction("question.list&ql_submit=");
		} catch (NumberFormatException ex) {
			error("ID không hợp lệ.");
		}
	}

}
