package oop.controller.action.test;

import java.util.List;

import oop.controller.action.AbstractAction;
import oop.data.Status;
import oop.data.Test;
import oop.data.Topic;
import oop.data.User;
import oop.db.dao.TestDAO;
import oop.db.dao.TopicDAO;
import oop.db.dao.UserDAO;
import oop.taglib.UtilFunctions;

import org.apache.commons.lang.StringUtils;

public class ListAction extends AbstractAction {

	public static final int PAGE_LENGTH = 30;

	@Override
	public void performImpl() throws Exception {
		
		title("Danh sách các đề thi trên website trắc nghiệm CSForce");

		String submit = getParams().get("tl_submit");
		if ("delete".equals(submit)) {
			try {
				String[] items = request.getParameterValues("tl_tests");
				int count = 0;
				for (String item : items) {
					long id = Long.parseLong(item);
					TestDAO.fetchById(id).setStatus(Status.DELETED);
				}
				message("Đã xóa " + count + " mục.");
			} catch (NumberFormatException ex) {
				error("ID không hợp lệ.");
			}
		}

		int page = getParams().getInt("page", 1);
		String topicIdStr = getParams().get("topic");
		String authorIdStr = getParams().get("author");

		List<Test> tests;
		long count;
		if (!StringUtils.isEmpty(topicIdStr)) {
			long id = Long.parseLong(topicIdStr);
			Topic topic = TopicDAO.fetchById(id);
			if (topic == null) {
				error("Không tìm thấy chủ đề có mã số: " + id);
				return;
			} else {
				tests = TestDAO.fetchByTopic(id, (page - 1) * PAGE_LENGTH,
						PAGE_LENGTH);
				title("Danh sách các đề thi thuộc chủ đề " + topic.getName() + " trên website trắc nghiệm CSForce");
				count = TestDAO.countByTopic(id);
			}
		} else if (!StringUtils.isEmpty(authorIdStr)) {
			long id = Long.parseLong(authorIdStr);
			tests = TestDAO.fetchByAuthor(id, (page - 1) * PAGE_LENGTH,
					PAGE_LENGTH);
			User user = UserDAO.fetchById(id);
			title("Danh sách các đề thi của tác giả " + user.getName());
			count = TestDAO.countByAuthor(id);
		} else {
			tests = TestDAO.fetch((page - 1) * PAGE_LENGTH, PAGE_LENGTH);
			count = TestDAO.count();
		}

		request.setAttribute("tests", tests);
		request.setAttribute("page", page);
		request.setAttribute("pageCount", UtilFunctions.ceil(count / (double)PAGE_LENGTH));
	}

}
