package oop.controller.action.test;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.Test;
import oop.db.dao.ResourceDAO;

public class ViewAction extends AbstractResourceAction<Test> {

	private Test test;

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			long id = getParams().getLong("id");
			resource = ResourceDAO.fetchById(id, Test.class);
			test = resource.getArticle();
			title("Nội dung đề "+ test.getName());
		} catch (NullPointerException ex) {
			throw new ActionException("Không tìm thấy đề thi: " + getParams().get("id"));
		} catch (NumberFormatException ex) {
			throw new ActionException("Mã số đề thi không hợp lệ: " + getParams().get("id"));
		}
	}
	
	public Test getTest() {
		return test;
	}
	
}
