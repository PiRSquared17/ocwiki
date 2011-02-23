package org.ocwiki.controller.action.sectstruct;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.Resource;
import org.ocwiki.data.TestStructure;
import org.ocwiki.db.dao.TestStructureDAO;

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
