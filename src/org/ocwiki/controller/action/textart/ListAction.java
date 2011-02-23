package org.ocwiki.controller.action.textart;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.Resource;
import org.ocwiki.data.TextArticle;
import org.ocwiki.db.dao.TextArticleDAO;

public class ListAction extends AbstractAction {

	private List<Resource<TextArticle>> textArticles;
	private long count;
	private int start;

	@Override
	public void performImpl() throws IOException, ServletException {
		start = getParams().getInt("start", 0);
		int size = getParams().getInt("size", 20);
		textArticles = TextArticleDAO.listOrderByName(start, size);
		count = TextArticleDAO.count();
	}
	
	public List<Resource<TextArticle>> getTextArticles() {
		return textArticles;
	}
	
	public int getStart() {
		return start;
	}
	
	public long getCount() {
		return count;
	}

}
