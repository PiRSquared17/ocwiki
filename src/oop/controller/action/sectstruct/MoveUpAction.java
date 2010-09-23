package oop.controller.action.sectstruct;

import oop.controller.action.AbstractAction;
import oop.data.SectionStructure;
import oop.data.TestStructure;
import oop.db.dao.ResourceDAO;

public class MoveUpAction extends AbstractAction<TestStructure> {

	private TestStructure test;

	@Override
	public void performImpl() throws Exception {
		resource = ResourceDAO.fetchById(getParams().getLong("testid"));
		test = resource.getArticle().copy();
		int sectionIndex = getParams().getInt("id");
		SectionStructure section = test.getSectionStructures().remove(
				sectionIndex);
		test.getSectionStructures().add(sectionIndex - 1, section);
		saveNewRevision(resource, test);

		setNextAction("teststruct.view&tstr=" + resource.getId());
	}

}
