package oop.controller.action.article;

import com.oreilly.servlet.ParameterNotFoundException;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.db.dao.ResourceDAO;

public class ViewAction extends AbstractAction {

	@Override
	protected void performImpl() {
		long id;
		try {
			id = getParams().getLong("id");
			setResource(ResourceDAO.fetchById(id));
		} catch (NumberFormatException e) {
			throw new ActionException("Mã bài viết không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			throw new ActionException("Bạn cần chọn bài viết.");
		}
	}

}
