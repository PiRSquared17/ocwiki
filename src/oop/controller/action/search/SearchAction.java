package oop.controller.action.search;

import java.util.List;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Resource;
import oop.data.ResourceLike;
import oop.data.ResourceTodo;
import oop.db.dao.ResourceDAO;

public class SearchAction extends AbstractAction {

	private String query;
	private List<Resource<? extends Article>> results;

	@Override
	protected void performImpl() throws Exception {
		query = getParams().getString("search_query");
		int start = getParams().getInt("start", 0);
		int size = getParams().getInt("size", 50);
		
		QueryParser parser = new QueryParser(query);
		boolean doneProcessing = false;
		if (parser.criteriaCount() == 1) {
			doneProcessing |= processSingleCriteria(start, size, parser);
		}
		if (!doneProcessing) {
			
		}
	}

	private boolean processSingleCriteria(int start, int size, QueryParser parser) {
		Criteria criteria = parser.next();
		switch (criteria.getTag()) {
		case NAME:
			results = ResourceDAO.fetchByNameLike("%"
					+ criteria.getContent() + "%", start, size);
			return true;

		case TOPIC:
			results = ResourceDAO.fetchByTopicLike("%"
					+ criteria.getContent() + "%", start, size);
			return true;
			
		case IS:
			if ("liked".equalsIgnoreCase(criteria.getContent()) ||
					"thích".equalsIgnoreCase(criteria.getContent())) {
				if (isUserLoggedIn()) {
					results = ResourceDAO.fetchLike(getUser().getId(),
							ResourceLike.LIKE, start, size);
				}
			} else if ("todo".equalsIgnoreCase(criteria.getContent())
					|| "cần-làm".equalsIgnoreCase(criteria.getContent())) {
				if (isUserLoggedIn()) {
					results = ResourceDAO.fetchTodo(getUser().getId(),
							ResourceTodo.TODO, start, size);
				}
			} else if ("done".equalsIgnoreCase(criteria.getContent())
					|| "xong".equalsIgnoreCase(criteria.getContent())) {
				if (isUserLoggedIn()) {
					results = ResourceDAO.fetchDone(getUser().getId(),
							true, start, size);
				}
			}
			return true;
		}
		return false;
	}

	public String getQuery() {
		return query;
	}

	public List<Resource<? extends Article>> getResults() {
		return results;
	}

}
