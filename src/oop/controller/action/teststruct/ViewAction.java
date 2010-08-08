package oop.controller.action.teststruct;

import oop.controller.action.AbstractAction;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;

public class ViewAction extends AbstractAction {
	@Override
	public void performImpl() throws Exception {
		try {
			long id = getParams().getLong("tsv_id");
			TestStructure testStruct = TestStructureDAO.fetchById(id);
			title("Cấu trúc đề: " + testStruct.getName());
			request.setAttribute("teststruct", testStruct);
		} catch (NumberFormatException ex) {
			error("Id không hợp lệ: " + getParams().get("tsv_id"));
		}
	}
}
