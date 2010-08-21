package oop.controller.action.section;

import oop.controller.action.AbstractAction;
import oop.data.Resource;
import oop.data.Test;
import oop.db.dao.ResourceDAO;

public class DeleteAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		Resource<Test> resource = ResourceDAO.fetchById(getParams().getLong(
				"test"), Test.class); 
		Test test = resource.getArticle().copy();
		test.getSections().remove(getParams().getInt("section"));
		saveNewRevision(resource, test);

		setNextAction("test.view&id=" + resource.getId());
	}

}
