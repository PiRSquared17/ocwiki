package oop.controller.action.topicconst;

import java.util.Set;

import oop.controller.action.AbstractAction;
import oop.data.SectionStructure;
import oop.data.TestStructure;
import oop.db.dao.SectionStructureDAO;
import oop.db.dao.TestStructureDAO;
import oop.db.dao.TopicConstraintDAO;

import org.apache.commons.lang.StringUtils;

public class EditAction extends AbstractAction {

	public EditAction() {
	}

	@Override
	public void performImpl() throws Exception {
		long testStructureId = getParams().getLong("teststruct");
		TestStructure testStructure = TestStructureDAO.fetchById(testStructureId);
		request.setAttribute("test", testStructure);

		String submit = getParams().get("submit");
		if ("add".equals(submit)) {
			String sectionIdStr = getParams().get("section");
			SectionStructure sectionStructure;
			if (StringUtils.isEmpty(sectionIdStr)) {
				Set<SectionStructure> sectionStructures = testStructure.getSectionStructures();
				if (sectionStructures.isEmpty()) {
					sectionStructure = SectionStructureDAO.create(null, testStructureId, 1);
				} else {
					sectionStructure = sectionStructures.iterator().next();
				}
			} else {
				long sectionId = Long.parseLong(sectionIdStr);
				sectionStructure = SectionStructureDAO.fetchById(sectionId);
			}
			
			int quantity = getParams().getInt("quantity");

			long topicId = getParams().getLong("topicid");
			
			TopicConstraintDAO.create(sectionStructure.getId(), quantity);
			
			setNextAction("teststruct.view&tsv_id=" + testStructureId);
		}
	}
	
}
