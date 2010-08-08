package oop.controller.action.section;

import java.sql.SQLException;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.db.dao.SectionDAO;
import oop.db.dao.TestDAO;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		try {
			long testId = getParams().getLong("sc_testid");
			Article test = TestDAO.fetchById(testId);
			if (test == null) {
				error("Không tìm thấy đề thi được yêu cầu.");
				return;
			}

			title("Thêm mục mới vào đề " + test.getName());
			request.setAttribute("test", test);

			String submit = getParams().get("sc_submit");
			if ("create".equals(submit)) {
				doCreate(testId, test);
			}
		} catch (NumberFormatException ex) {
			error("ID không hợp lệ.");
		}
	}

	private void doCreate(long testId, Article test) throws SQLException {
		String text = getParams().get("sc_text");
		String orderStr = getParams().get("sc_order");

		int order = 0;
		if ("last".equals(orderStr)) {
			order = SectionDAO.nextAvailableOrder(testId);
		} else {
			order = Integer.parseInt(orderStr);
			SectionDAO.shiftDown(testId, order);
		}

		SectionDAO.create(text, order, testId);

		setNextAction("test.view&tv_id=" + test.getId());
	}

}
