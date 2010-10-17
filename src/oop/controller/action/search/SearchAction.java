package oop.controller.action.search;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Resource;

public class SearchAction extends AbstractAction {

	private String query;
	private Resource<? extends Article> results;

	@Override
	protected void performImpl() throws Exception {
		setQuery(getParams().get("query"));
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public void setResults(Resource<? extends Article> results) {
		this.results = results;
	}

	public Resource<? extends Article> getResults() {
		return results;
	}

}
