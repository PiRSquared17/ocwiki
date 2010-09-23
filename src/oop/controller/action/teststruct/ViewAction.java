package oop.controller.action.teststruct;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Resource;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;

public class ViewAction extends AbstractAction<TestStructure> {

	private TestStructure testStruct;

	@Override
	public void performImpl() throws Exception {
		try {
			resource = TestStructureDAO.fetchById(getParams().getLong("tstr"));
			testStruct = resource.getArticle();
			title("Cấu trúc đề: " + testStruct.getName());
		} catch (NumberFormatException ex) {
			throw new ActionException("Id không hợp lệ: " + getParams().get("tstr"));
		}
	}

	public TestStructure getTest() {
		return testStruct;
	}
}
