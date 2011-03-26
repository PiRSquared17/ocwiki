package org.ocwiki.controller.action.search;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;

import com.oreilly.servlet.ParameterNotFoundException;

public class SearchAction extends AbstractAction {

	SearchHelper helper = new SearchHelper();

	@Override
	protected void performImpl() throws IOException, ServletException {
		try {
			helper.setQuery(getParams().getString("search_query"));
			helper.setStart(getParams().getInt("start", 0));
			helper.setSize(getParams().getInt("size", 50));
			helper.search(getRequest());
			title("Tìm \"" + helper.getQuery() + "\"");
		} catch (ParameterNotFoundException e) {
			setNextAction("homepage");
		}
	}

	public String getQuery() {
		return helper.getQuery();
	}

	public long getCount() {
		return getResults().size();
	}

	public List<Resource<? extends Article>> getResults() {
		return helper.getResults();
	}

	public int getStart() {
		return helper.getStart();
	}
	
	public int getSize() {
		return helper.getSize();
	}
	
}
