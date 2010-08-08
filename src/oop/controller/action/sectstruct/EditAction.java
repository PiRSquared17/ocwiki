package oop.controller.action.sectstruct;

import oop.controller.action.AbstractAction;
import oop.data.SectionStructure;
import oop.data.TestStructure;
import oop.db.dao.SectionStructureDAO;
import oop.db.dao.TextDAO;

import com.mysql.jdbc.StringUtils;

public class EditAction extends AbstractAction {
	
	@Override
	public void performImpl() throws Exception {
		try {
			long sectionId = getParams().getLong("sse_id");
			SectionStructure sectionStructure = SectionStructureDAO.fetchById(sectionId);
			request.setAttribute("section", sectionStructure);
			TestStructure testStructure = sectionStructure.getTestStructure();
			request.setAttribute("test", testStructure);
			title("Sửa phần " + sectionStructure.getOrder() + " trong cấu trúc đề " + testStructure.getName());

			String submit = getParams().get("sse_submit");
			if (!StringUtils.isNullOrEmpty(submit)) {
				sectionStructure.setContent(TextDAO.create(getParams().getString(
						"sse_text")));
				sectionStructure.setOrder(getParams().getInt("sse_order"));

				SectionStructureDAO.shiftDown(testStructure.getId(),
						sectionStructure.getOrder());
				SectionStructureDAO.persist(sectionStructure);
				SectionStructureDAO.normalize(testStructure.getId());

				setNextAction("teststruct.view&tsv_id="
						+ testStructure.getId());
			}
		} catch (NumberFormatException ex) {
			request.setAttribute("message", "ID không hợp lệ");
			setNextAction("error");
		}
	}

}
