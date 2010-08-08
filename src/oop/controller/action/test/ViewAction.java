package oop.controller.action.test;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.db.dao.TestDAO;

public class ViewAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		try {
			long id = getParams().getLong("tv_id");
			Article test = TestDAO.fetchById(id);
			title("Nội dung đề "+ test.getName());
			
			request.setAttribute("test", test);
		} catch (NullPointerException ex) {
			request.setAttribute("message", "Không tìm thấy đề thi: "
					+ getParams().get("tv_id"));
			setNextAction("error");
		} catch (NumberFormatException ex) {
			request.setAttribute("message", "Mã số đề thi không hợp lệ: "
					+ getParams().get("tv_id"));
			setNextAction("error");
		}
	}

}
