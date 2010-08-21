package oop.controller.action.test;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Resource;
import oop.data.Test;
import oop.db.dao.ResourceDAO;

public class ViewAction extends AbstractAction {

	private Test test;
	private Resource<Test> resource;

	@Override
	public void performImpl() throws Exception {
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

	public Resource<Test> getResource() {
		return resource;
	}
	
	public Test getTest() {
		return test;
	}
	
}
