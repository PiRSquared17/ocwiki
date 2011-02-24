package org.ocwiki.controller.action.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Article;
import org.ocwiki.data.User;
import org.ocwiki.data.stat.ResourceViewCounter;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.db.dao.stat.ResourceViewCounterDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class ViewAction extends AbstractResourceAction<Article> {

	public List<User> editors;
	private ResourceViewCounter viewCounter;
	
	@Override
	protected void performImpl() throws IOException, ServletException {
		long id;
		try {
			id = getParams().getLong("id");
			resource = ResourceDAO.fetchById(id);
			viewCounter = ResourceViewCounterDAO.incrementAndFetch(id);
			editors = ResourceDAO.fetchEditors(id);
			if (resource == null) {
				throw new ActionException("Không tìm thấy bài viết.");
			}
		} catch (NumberFormatException e) {
			throw new ActionException("Mã bài viết không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			throw new ActionException("Bạn cần chọn bài viết.");
		}
	}

	public List<User> getEditors() {
		return editors;
	}
	
	public ResourceViewCounter getViewCounter() {
		return viewCounter;
	}
	
}