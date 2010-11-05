package oop.controller.action.test;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;

import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.ChoiceAnswer;
import oop.data.History;
import oop.data.HistoryAnswer;
import oop.data.Test;
import oop.db.dao.HistoryDAO;
import oop.db.dao.TestDAO;
import oop.persistence.HibernateUtil;

public class SolveAction extends AbstractResourceAction<Test> {

	private Test test;

	@Override
	public void performImpl() throws IOException, ServletException {
		String submit = getParams().getString("submit", "");
		resource = TestDAO.fetchById(getParams().getLong("testId"));
		test = resource.getArticle();
		if (test.getQuestionCount() <= 0) {
			throw new ActionException("Đề thi chưa hoàn thiện.");
		}
		if ("done".equals(submit)) {
			Set<HistoryAnswer> answers = getChoiceAnswers();
			int time = getParams().getInt("time");
			History history = new History(getUser(), test, new Date(), answers,
					time);
			HistoryDAO.persist(history);
			setNextAction("history.view&id=" + history.getId());
		} else {
			if (test.getQuestionCount() <= 0) {
				throw new ActionException("Đề thi chưa hoàn thiện.");
			}
			title("Làm đề: " + test.getName());
		}
	}

	private Set<HistoryAnswer> getChoiceAnswers() {
		Set<HistoryAnswer> answers = new HashSet<HistoryAnswer>();
		for (Object obj : request.getParameterMap().keySet()) {
			String key = (String) obj;
			if (key.startsWith("q")) {
				ChoiceAnswer choiceAnswer = new ChoiceAnswer();
				long questionId = Long.parseLong(key.substring(1));
				choiceAnswer.setQuestion(HibernateUtil.load(BaseQuestion.class, questionId));
				String[] params = (String[]) request.getParameterMap().get(key);
				for (int i = 0; i < params.length; i++) {
					long answerId = Long.parseLong(params[i]);
					Answer answer = HibernateUtil.load(Answer.class, answerId);
					choiceAnswer.getAnswers().add(answer);
				}
				answers.add(choiceAnswer);
			}
		}
		return answers;
	}

	public Test getTest() {
		return test;
	}
	
}
