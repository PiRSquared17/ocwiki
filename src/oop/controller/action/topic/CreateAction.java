package oop.controller.action.topic;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.ResourceDAO;
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
				String name = "";
				try {
					name = getParams().getString("cc_name");
				} catch (ParameterNotFoundException ex) {
					addError("name", "Bạn cần nhập tên chủ đề.");
				}

				Resource<Topic> parent = null;
				try {
					parent = ResourceDAO.fetchById(getParams().getLong(
							"cc_parent"), Topic.class);
				} catch (ParameterNotFoundException e) {
					parent = null;
				} catch (NumberFormatException ex) {
					addError("parent", "Chủ đề không hợp lệ");
				}

				if (!hasErrors()) {
					TopicDAO.create(name, parent, getUser());
					setNextAction("topic.list");
					return;
				}
			}
		} catch (NumberFormatException ex) {
			throw new ActionException("ID không hợp lệ");
		} catch (ConstraintViolationException ex) {
			if ("top_name".equals(ex.getConstraintName())) { // trùng tên
				addError("name", "Đã có chủ đề trùng tên");
			} else {
				throw ex;
			}
		}
	}

}
