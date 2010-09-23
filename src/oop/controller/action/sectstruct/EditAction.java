package oop.controller.action.sectstruct;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.SectionStructure;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;
import oop.db.dao.TextDAO;
import oop.util.Utils;

public class EditAction extends AbstractAction<TestStructure> {

	@Override
	public void performImpl() throws Exception {
		try {
			resource = TestStructureDAO
					.fetchById(getParams().getLong("tstr"));
			TestStructure testStructure = resource.getArticle();
			int sectionIndex = getParams().getInt("section");
			SectionStructure sectionStructure = testStructure
					.getSectionStructures().get(sectionIndex);
			request.setAttribute("section", sectionStructure);
			request.setAttribute("test", testStructure);
			title("Sửa phần " + sectionIndex + " trong cấu trúc đề "
					+ testStructure.getName());

			if (getParams().hasParameter("ssubmit")) {
				testStructure = testStructure.copy();
				sectionStructure = Utils.replaceByCopy(testStructure
						.getSectionStructures(), sectionIndex);
				sectionStructure.setContent(TextDAO.create(getParams()
						.getString("stext")));
				if (getParams().hasParameter("sorder")) {
					sectionIndex = getParams().getInt("sorder");
					testStructure.getSectionStructures().remove(
							sectionStructure);
					testStructure.getSectionStructures().add(sectionIndex,
							sectionStructure);
				}
				saveNewRevision(resource, testStructure);
				
				setNextAction("teststruct.view&tstr=" + resource.getId());
			}
		} catch (NumberFormatException ex) {
			throw new ActionException("ID không hợp lệ");
		}
	}

}
