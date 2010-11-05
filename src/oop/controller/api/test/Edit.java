package oop.controller.api.test;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.Article;
import oop.data.Test;
import oop.db.dao.ResourceDAO;

public class Edit extends AbstractResourceAction<Test>  {

	private Article test;

	@Override
	public void performImpl() throws IOException, ServletException {
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
