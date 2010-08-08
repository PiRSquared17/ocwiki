package oop.controller.action.topic;

import java.sql.SQLException;

import oop.controller.action.AbstractAction;
import oop.data.Topic;
import oop.db.dao.TopicDAO;

import com.oreilly.servlet.ParameterList;
import com.oreilly.servlet.ParameterNotFoundException;

public class EditAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		try {
			long id = getParams().getLong("ce_id");
			String submitted = getParams().get("ce_submit");

			Topic topic = TopicDAO.fetchById(id);
			if (topic == null) {
				// kiểm tra chủ đề tồn tại
				error("Chủ đề không tồn tại.");
				return;
			}

			request.setAttribute("topic", topic);
			title("Thay đổi thông tin chủ đề " + topic.getName());
			
			if ("update".equals(submitted)) {
				doEdit(getParams(), topic);
			}
		} catch (NumberFormatException ex) {
			request.setAttribute("message", "id không hợp lệ");
			setNextAction("error");
		} catch (SQLException ex) {
			if (ex.getErrorCode() == 1062) {
				request.setAttribute("nameErr", "Trùng tên!");
			} else {
				throw ex;
			}
		}
	}

	private void doEdit(ParameterList parser, Topic topic)
			throws SQLException {
		boolean error = false;

		try {
			topic.setName(parser.getString("ce_name"));
		} catch (ParameterNotFoundException ex) {
			error = true;
			request.setAttribute("nameErr", "Bạn cần nhập tên chủ đề.");
		}

		// cập nhật cha nếu không bị nối vòng
		int parentId;
		try {
			parentId = parser.getInt("ce_parent");

			Topic parent = TopicDAO.fetchById(parentId);
			if (parent == null) { // kiểm tra chủ đề cha tồn tại
				error = true;
				request.setAttribute("parentErr",
					"Chủ đề cha không tồn tại");
			} else if (parent.getAncestors().contains(topic)) {
				error = true;
				request.setAttribute("parentErr",
					"Không thể liên kết vòng");
			}
			topic.setParent(parent);
		} catch (NumberFormatException e) {
			request.setAttribute("parentErr",
					"Mã số không hợp lệ");
		} catch (ParameterNotFoundException e) {
			topic.setParent(null);
		}

		// lưu nếu không có lỗi
		if (!error) {
			TopicDAO.persist(topic);
			setNextAction("topic.list");
		}
	}

}
