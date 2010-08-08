package oop.controller.action.topic;

import oop.controller.action.AbstractAction;
import oop.data.Topic;
import oop.db.dao.TopicDAO;

import org.hibernate.exception.ConstraintViolationException;

public class ListAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		title("Danh sách các chủ đề trên website trắc nghiệm CSForce");
		String submit = getParams().get("cl_submit");
		if ("delete".equals(submit)) {
			String[] items = request.getParameterValues("cl_topics");
			StringBuilder mess = new StringBuilder();
			int deleted = 0;
			for (String item : items) {
				try {
					int id = Integer.parseInt(item);
					try {
						deleted += TopicDAO.drop(TopicDAO.fetchById(id));
					} catch (ConstraintViolationException ex) {
						Topic topic = TopicDAO.fetchById(id);
						mess.append("Không thể xóa chủ đề đang sử dụng: ").append(topic.getName()).append("<br />");
					}
				} catch (NumberFormatException ex) {
					mess.append("Id không hợp lệ: ").append(item).append("<br />");
				}
			}
			if (deleted > 0) {
				mess.append("Đã xóa ").append(deleted).append(" mục");
			}
			request.setAttribute("message", mess.toString());
		}

		request.setAttribute("topLevels", TopicDAO.fetchTopLevels());
	}

}
