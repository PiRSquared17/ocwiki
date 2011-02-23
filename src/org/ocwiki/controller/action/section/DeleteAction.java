package org.ocwiki.controller.action.section;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Test;
import org.ocwiki.db.dao.ResourceDAO;

public class DeleteAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		Resource<Test> resource = ResourceDAO.fetchById(getParams().getLong(
				"test"), Test.class); 
		Test test = resource.getArticle().copy();
		test.getSections().remove(getParams().getInt("section"));
		saveNewRevision(resource, test);

		setNextAction("test.view&id=" + resource.getId());
	}

}
