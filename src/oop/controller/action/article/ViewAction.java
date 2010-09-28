package oop.controller.action.article;

import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.Article;
import oop.db.dao.ResourceDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class ViewAction extends AbstractResourceAction<Article> {

	@Override
	protected void performImpl() {
		long id;
		try {
			id = getParams().getLong("id");
			resource = ResourceDAO.fetchById(id);
		} catch (NumberFormatException e) {
			throw new ActionException("Mã bài viết không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			throw new ActionException("Bạn cần chọn bài viết.");
		}
	}
	
}
