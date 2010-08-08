package oop.controller.action.test;

import java.util.HashMap;
import java.util.Map;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.History;
import oop.data.Test;
import oop.db.dao.HistoryDAO;
import oop.db.dao.TestDAO;
import oop.taglib.UtilFunctions;

public class SolveAction extends AbstractAction {

	private Test test;

	@Override
	public void performImpl() throws Exception {
		String submit = getParams().getString("submit", "");
		if ("done".equals(submit)) {
			test = TestDAO.fetchById(getParams().getLong("testId"));

			if (test.getQuestionCount() <= 0) {
				error("Đề thi chưa hoàn thiện.");
				return;
			}
			
			Map<Long, long[]> choices = getChoices();
			double mark = UtilFunctions.getMark(test, choices);
			int time = getParams().getInt("time");
			History history = HistoryDAO.create(getUser().getId(), test
					.getId(), mark, time);

			setNextAction("history.view&id=" + history.getId());
		} else {
			long testId = getParams().getLong("testId");
			test = TestDAO.fetchById(testId);
			
			if (test.getQuestionCount() <= 0) {
				error("Đề thi chưa hoàn thiện.");
				return;
			}
			
			title("Làm đề: " + test.getName());
		}
	}

	private Map<Long, long[]> getChoices() {
		Map<Long, long[]> map = new HashMap<Long, long[]>();
		for (Object obj : request.getParameterMap().keySet()) {
			String key = (String) obj;
			if (key.startsWith("q")) {
				long questionId = Long.parseLong(key.substring(1));
				String[] params = (String[]) request.getParameterMap().get(key);
				long[] answerIds = new long[params.length];
				for (int i = 0; i < params.length; i++) {
					answerIds[i] = Long.parseLong(params[i]);
				}
				map.put(questionId, answerIds);
			}
		}
		return map;
	}

	public Article getTest() {
		return test;
	}
	
}
