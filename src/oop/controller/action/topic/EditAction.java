package oop.controller.action.topic;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.TopicDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class EditAction extends AbstractAction {

	private Resource<Topic> resource;
	private Topic topic;

	@Override
	public void performImpl() throws Exception {
		try {
			long id = getParams().getLong("ce_id");
			String submitted = getParams().get("ce_submit");

			resource = TopicDAO.fetchById(id);
			if (resource == null) {
				// kiểm tra chủ đề tồn tại
				throw new ActionException("Chủ đề không tồn tại.");
			}
			topic = resource.getArticle();

			title("Sửa chủ đề " + topic.getName());

			if ("update".equals(submitted)) {
				doEdit();
			}
		} catch (NumberFormatException ex) {
			throw new ActionException("id không hợp lệ");
		}
	}

	private void doEdit() throws Exception {
		topic = topic.copy();
		
		try {
			topic.setName(getParams().getString("ce_name"));
		} catch (ParameterNotFoundException ex) {
			addError("name", "Bạn cần nhập tên chủ đề.");
		}

		// cập nhật cha nếu không bị nối vòng
		try {
			int parentId = getParams().getInt("ce_parent");
			Resource<Topic> parent = TopicDAO.fetchById(parentId);
			if (parent == null) { // kiểm tra chủ đề cha tồn tại
				addError("parent", "Chủ đề cha không tồn tại");
			} else if (TopicDAO.getAncestors(parentId).contains(topic)) {
				addError("parent", "Không thể liên kết vòng");
			}
			topic.setParent(parent);
		} catch (NumberFormatException e) {
			addError("parent", "Mã số không hợp lệ");
		} catch (ParameterNotFoundException e) {
			topic.setParent(null);
		}

		// lưu nếu không có lỗi
		if (hasNoErrors()) {
			saveNewRevision(resource, topic);
			setNextAction("topic.list");
		}
	}

	public Resource<Topic> getResource() {
		return resource;
	}
	
	public Topic getTopic() {
		return topic;
	}
	
}
