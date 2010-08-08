package oop.controller.api.test;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.db.dao.TestDAO;

public class Edit extends AbstractAction {

	private Article test;

	@Override
	public void performImpl() throws Exception {
		try {
			long id = getParams().getLong("testId");
			test = TestDAO.fetchById(id);
			title("Sửa đề " + test.getId());
		} catch (NullPointerException ex) {
			error("Không tìm thấy đề thi.");
		} catch (NumberFormatException ex) {
			error("Mã số đề thi không hợp lệ.");
		}
	}
	
	public Article getTest() {
		return test;
	}

}
