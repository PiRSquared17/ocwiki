package oop.controller.action.sectstruct;

import oop.controller.action.AbstractAction;
import oop.data.SectionStructure;
import oop.data.TestStructure;
import oop.db.dao.SectionStructureDAO;
import oop.db.dao.TestStructureDAO;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		try {
			long testStructureId = getParams().getLong("ssc_testid");
			TestStructure test = TestStructureDAO.fetchById(testStructureId);
			request.setAttribute("test", test);
			title("Tạo phần mới trong cấu trúc đề " + test.getName());

			String submit = getParams().get("ssc_submit");
			if ("create".equals(submit)) {
				String text = getParams().get("ssc_text");
				int order = getParams().getInt("ssc_order");

				SectionStructure sectionStructure = SectionStructureDAO.create(
						text, testStructureId, order);
				setNextAction("teststruct.view&tsv_id=" + testStructureId);
			}
		} catch (NumberFormatException ex) {
			error("ID không hợp lệ.");
		}
	}

}
