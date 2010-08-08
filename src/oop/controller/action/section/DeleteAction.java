package oop.controller.action.section;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.db.dao.SectionDAO;
import oop.db.dao.TestDAO;

public class DeleteAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		long sectionId = getParams().getLong("sd_id");
		long testId = getParams().getLong("sd_testid");
		Article test = TestDAO.fetchById(testId);

		SectionDAO.fetchById(sectionId).setDeleted(true);
		SectionDAO.normalize(testId);

		setNextAction("test.view&tv_id=" + testId);
	}

}
