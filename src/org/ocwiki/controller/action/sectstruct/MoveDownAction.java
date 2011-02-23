package org.ocwiki.controller.action.sectstruct;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.data.SectionStructure;
import org.ocwiki.data.TestStructure;
import org.ocwiki.db.dao.ResourceDAO;
public class MoveDownAction extends AbstractResourceAction<TestStructure> {
	
	private TestStructure test;

	@Override
	public void performImpl() throws IOException, ServletException {
		resource = ResourceDAO.fetchById(getParams().getLong("testid"));
		test = resource.getArticle().copy();
		int sectionIndex = getParams().getInt("id");
		SectionStructure section = test.getSectionStructures().remove(
				sectionIndex);
		test.getSectionStructures().add(sectionIndex+1, section);
		saveNewRevision(resource, test);

		setNextAction("teststruct.view&tstr=" + resource.getId());
	}

}
