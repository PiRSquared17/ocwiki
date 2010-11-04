package oop.controller.action.sectstruct;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.data.Resource;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;

public class DeleteAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		Resource<TestStructure> resource = TestStructureDAO
				.fetchById(getParams().getLong("tstr"));
		TestStructure test = resource.getArticle().copy();
		test.getSectionStructures().remove(getParams().getInt("section"));
		saveNewRevision(resource, test);
		setNextAction("teststruct.view&tstr=" + resource.getId());
	}

}
