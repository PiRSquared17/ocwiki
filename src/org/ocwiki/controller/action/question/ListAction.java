package org.ocwiki.controller.action.question;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.db.dao.MultichoiceQuestionDAO;

public class ListAction extends AbstractAction {

	public static final int PAGE_LENGTH = 10;
	private int start;
	private int size;
	private long count;
	private List<Resource<MultichoiceQuestion>> questions;

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

		questions = MultichoiceQuestionDAO.fetch(start, size);
		count = MultichoiceQuestionDAO.count();

		request.setAttribute("questions", questions);
	}
	
	public List<Resource<MultichoiceQuestion>> getQuestions() {
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
