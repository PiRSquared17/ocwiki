package oop.controller.action.question;

import java.util.List;

import oop.controller.action.AbstractAction;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.Topic;
import oop.data.User;
import oop.db.dao.BaseQuestionDAO;
import oop.db.dao.TopicDAO;
import oop.db.dao.UserDAO;
import oop.taglib.UtilFunctions;

public class ListAction extends AbstractAction {

	public static final int PAGE_LENGTH = 10;

	@Override
	public void performImpl() throws Exception {

		title("Danh sách câu hỏi");

		String submit = getParams().get("ql_submit");
		if ("delete".equals(submit)) {
			setNextAction("question.delete");
			return;
		}

		String pageStr = getParams().getString("page", "1");
		int page = Integer.parseInt(pageStr) - 1;

		List<Resource<BaseQuestion>> questions;
		long count;
		if (getParams().hasParameter("topic")) {
			long id = getParams().getLong("topic");
			questions = BaseQuestionDAO.fetchByTopic(id, page * PAGE_LENGTH,
					PAGE_LENGTH);
			Resource<Topic> topic = TopicDAO.fetchById(id);
			title("Danh sách các câu hỏi thuộc chủ đề " + topic.getName());
			count = BaseQuestionDAO.countByTopic(id);
		} else if (getParams().hasParameter("author")) {
			long id = getParams().getLong("author");
			questions = BaseQuestionDAO.fetchByAuthor(id, page * PAGE_LENGTH,
					PAGE_LENGTH);
			User author = UserDAO.fetchById(id);
			title("Danh sách các đề thi của tác giả " + author.getName());
			count = BaseQuestionDAO.countByAuthor(id);
		} else {
			questions = BaseQuestionDAO.fetch(page * PAGE_LENGTH, PAGE_LENGTH);
			count = BaseQuestionDAO.count();
		}

		request.setAttribute("questions", questions);
		request.setAttribute("page", page + 1);
		request.setAttribute("pageCount", UtilFunctions.ceil(count
				/ (double) PAGE_LENGTH));
	}

}
