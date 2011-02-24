package org.ocwiki.controller.action.solution;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Solution;
import org.ocwiki.db.dao.MultichoiceQuestionDAO;
import org.ocwiki.db.dao.SolutionDAO;

public class ListAction extends AbstractAction {

	private int size;
	private int start;
	private List<Resource<Solution>> solutions;
	private Resource<MultichoiceQuestion> question;

	@Override
	protected void performImpl() throws IOException, ServletException {
		if (!getParams().hasParameter("id")) {
			throw new ActionException("Bạn cần chọn câu hỏi.");
		}
		
		size = getParams().getInt("size", 20);
		start = getParams().getInt("start", 0);
		question = MultichoiceQuestionDAO.fetchById(getParams().getLong("id"));
		solutions = SolutionDAO.fetchByQuestion(question.getId(), start, size);
	}

	public int getSize() {
		return size;
	}

	public int getStart() {
		return start;
	}

	public List<Resource<Solution>> getSolutions() {
		return solutions;
	}
	
	public int getCount() {
		return solutions.size();
	}
	
	public Resource<MultichoiceQuestion> getQuestion() {
		return question;
	}

}
