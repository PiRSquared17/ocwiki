package oop.controller.action.topic;

import oop.controller.action.AbstractAction;
import oop.data.Topic;
import oop.db.dao.TopicDAO;

import org.hibernate.exception.ConstraintViolationException;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		title("Tạo chủ đề mới");
		try {
			String submitted = getParams().get("cc_submit");
			if ("create".equals(submitted)) {
				boolean error = false;

				String name = "";
				try {
					name = getParams().getString("cc_name");
				} catch (ParameterNotFoundException ex) {
					error = true;
					request.setAttribute("nameErr", "Bạn cần nhập tên chủ đề.");
				}
				
				Topic parent = null;
				try {
					parent = TopicDAO.fetchById(getParams().getLong("cc_parent"));
				} catch (ParameterNotFoundException e) {
					parent = null;
				} catch (NumberFormatException ex) {
					error = true;
					request.setAttribute("parentErr", "Chủ đề không hợp lệ");
				}

				if (!error) {
					TopicDAO.create(name, parent, getUser());
					setNextAction("topic.list");
					return;
				}
			}
		} catch (NumberFormatException ex) {
			error("ID không hợp lệ");
		} catch (ConstraintViolationException ex) {
			if ("top_name".equals(ex.getConstraintName())) { // trùng tên
				request.setAttribute("nameErr", "Đã có chủ đề trùng tên");
			} else {
				throw ex;
			}
		}
	}

}
