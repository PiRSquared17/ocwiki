package oop.controller.action.question;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.db.dao.BaseQuestionDAO;

public class ListAction extends AbstractAction {

	public static final int PAGE_LENGTH = 10;
	private int start;
	private int size;
	private long count;
	private List<Resource<BaseQuestion>> questions;

	@Override
	public void performImpl() throws IOException, ServletException {

		title("Danh sách câu hỏi");

		String submit = getParams().get("ql_submit");
		if ("delete".equals(submit)) {
			setNextAction("question.delete");
			return;
		}

		start = getParams().getInt("start", 0);
		size = getParams().getInt("size", PAGE_LENGTH);

		questions = BaseQuestionDAO.fetch(start, size);
		count = BaseQuestionDAO.count();

		request.setAttribute("questions", questions);
	}
	
	public List<Resource<BaseQuestion>> getQuestions() {
		return questions;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getSize() {
		return size;
	}
	
	public long getCount() {
		return count;
	}

}
