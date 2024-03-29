package org.ocwiki.controller.action.topic;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Topic;
import org.ocwiki.db.dao.TopicDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class EditAction extends AbstractResourceAction<Topic> {

	private Topic topic;

	@Override
	public void performImpl() throws IOException, ServletException {
		if (!getParams().hasParameter("id")) {
			throw new ActionException("Bạn cần chọn chủ đề");
		}
		
		try {
			resource = TopicDAO.fetchById(getParams().getLong("id"));
		} catch (NumberFormatException ex) {
			throw new ActionException("id không hợp lệ");
		}

		if (resource == null) {
			throw new ActionException("Chủ đề không tồn tại.");
		}
			
		topic = (Topic) resource.getArticle();

		title("Sửa chủ đề " + topic.getName());

		if ("update".equals(getParams().get("submit"))) {
			doEdit();
		}
	}

	private void doEdit() {
		topic = topic.copy();
		
		try {
			topic.setName(getParams().getString("name"));
		} catch (ParameterNotFoundException ex) {
			addError("name", "Bạn cần nhập tên chủ đề.");
		}

		if (getParams().hasParameter("parentname") || getParams().hasParameter("parent")) {
			if (getParams().hasParameter("parent")) {
				// cập nhật cha nếu không bị nối vòng
				try {
					Resource<Topic> parent = TopicDAO.fetchById(getParams().getInt("parent"));
					if (parent == null) { // kiểm tra chủ đề cha tồn tại
						addError("parent-id", "Chủ đề cha không tồn tại");
					} else if (TopicDAO.getAncestors(parent.getId()).contains(resource)) {
						addError("parent-id", "Không thể liên kết vòng");
					} else {
						topic.setParent(parent);
					}
				} catch (NumberFormatException e) {
					addError("parent-id", "Mã số không hợp lệ");
				}
			} else {
				Resource<Topic> parent = TopicDAO.fetchByName(getParams()
						.getString("parentname"));
				if (parent == null) { // kiểm tra chủ đề cha tồn tại
					addError("parent-name", "Chủ đề cha không tồn tại");
				} else if (TopicDAO.getAncestors(parent.getId()).contains(topic)) {
					addError("parent-name", "Không thể liên kết vòng");
				} else {
					topic.setParent(parent);
				}
			}
		} else {
			topic.setParent(null);
		}

		String contentStr = getParams().get("content");
		topic.setContentString(contentStr);
		
		// lưu nếu không có lỗi
		if (hasNoErrors()) {
			saveNewRevision(resource, topic);
			setNextAction("article.view?id=" + resource.getId());
		}
	}

	public Topic getTopic() {
		return topic;
	}
	
}
