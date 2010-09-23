package oop.controller.action.test;

import java.util.HashMap;
import java.util.Map;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Article;
import oop.data.History;
import oop.data.Test;
import oop.db.dao.HistoryDAO;
import oop.db.dao.ResourceDAO;
import oop.taglib.UtilFunctions;

public class SolveAction extends AbstractAction<Test> {

	private Test test;

	@Override
	public void performImpl() throws Exception {
		String submit = getParams().getString("submit", "");
		resource = ResourceDAO.fetchById(getParams()
				.getLong("testId"));
		test = resource.getArticle();
		if ("done".equals(submit)) {
			if (test.getQuestionCount() <= 0) {
				throw new ActionException("Đề thi chưa hoàn thiện.");
			}
			
			Map<Long, long[]> choices = getChoices();
			double mark = UtilFunctions.getMark(test, choices);
			int time = getParams().getInt("time");
			History history = HistoryDAO.create(getUser().getId(), test
					.getId(), mark, time);

			setNextAction("history.view&id=" + history.getId());
		} else {
			if (test.getQuestionCount() <= 0) {
				throw new ActionException("Đề thi chưa hoàn thiện.");
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
