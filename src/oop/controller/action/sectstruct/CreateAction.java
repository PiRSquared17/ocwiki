package oop.controller.action.sectstruct;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Resource;
import oop.data.SectionStructure;
import oop.data.TestStructure;
import oop.data.Text;
import oop.db.dao.TestStructureDAO;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		try {
			Resource<TestStructure> resource = TestStructureDAO
					.fetchById(getParams().getLong("tstr"));
			TestStructure test = resource.getArticle();
			request.setAttribute("test", test);
			title("Tạo phần mới trong cấu trúc đề " + test.getName());

			String submit = getParams().get("ssubmit");
			if ("create".equals(submit)) {
				String text = getParams().get("stext");

				test = test.copy();
				SectionStructure sectionStructure = new SectionStructure(
						new Text(text));
				test.getSectionStructures().add(sectionStructure);
				saveNewRevision(resource, test);

				setNextAction("teststruct.view&tstr=" + resource.getId());
			}
		} catch (NumberFormatException ex) {
			throw new ActionException("ID không hợp lệ.");
		}
	}

}
