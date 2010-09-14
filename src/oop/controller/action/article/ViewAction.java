package oop.controller.action.article;

import com.oreilly.servlet.ParameterNotFoundException;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Article;
import oop.data.Resource;
import oop.db.dao.ResourceDAO;

public class ViewAction extends AbstractAction {
	private Resource<? extends Article> resource;
	@Override
	protected void performImpl() {
		long id;
		try {
			id = getParams().getLong("id");
			resource = ResourceDAO.fetchById(id);
			setResource(resource);
		} catch (NumberFormatException e) {
			throw new ActionException("Mã bài viết không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			throw new ActionException("Bạn cần chọn bài viết.");
		}
	}
	
	public Resource<? extends Article> getResource(){
		return resource;
	}

}
