package oop.controller.api.section;

import oop.controller.api.AbstractAPI;
import oop.data.Section;
import oop.db.dao.SectionDAO;

public class Create extends AbstractAPI {

	@Override
	protected Object performImpl() throws Exception {
		long testId = getParams().getLong("testId");
		String text = getParams().getString("text");
		int order = SectionDAO.nextAvailableOrder(testId);
		Section section = SectionDAO.create(text, order, testId);

		return success().addProperty("newId", section.getId()).addProperty(
				"newText", section.getText().getText());
	}

}
