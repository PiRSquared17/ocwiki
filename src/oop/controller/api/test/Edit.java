package oop.controller.api.test;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Article;
import oop.data.Test;
import oop.db.dao.ResourceDAO;

public class Edit extends AbstractAction<Test>  {

	private Article test;

	@Override
	public void performImpl() throws Exception {
		try {
			resource = ResourceDAO.fetchById(getParams().getLong("testId"));
			test = resource.getArticle();
			title("Sửa đề " + resource.getId());
		} catch (NullPointerException ex) {
			throw new ActionException("Không tìm thấy đề thi.");
		} catch (NumberFormatException ex) {
			throw new ActionException("Mã số đề thi không hợp lệ.");
		}
	}
	
	public Article getTest() {
		return test;
	}

}
