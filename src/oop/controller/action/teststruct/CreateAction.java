package oop.controller.action.teststruct;

import oop.controller.action.AbstractAction;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;

import org.apache.commons.lang.StringUtils;

public class CreateAction extends AbstractAction {
	
	@Override
	public void performImpl() throws Exception {
		String submit = getParams().get("tsc_submit");
		if ("create".equals(submit)) {
			boolean error = false;
			String name = getParams().get("tsc_name");
			if (StringUtils.isEmpty(name)) {
				request
						.setAttribute("nameErr",
								"Bạn cần nhập tên cấu trúc đề.");
				error = true;
			}

			String description = getParams().get("tsc_description");
			if (StringUtils.isEmpty(description)) {
				request.setAttribute("descriptionErr",
						"Bạn cần nhập mô tả cấu trúc đề");
				error = true;
			}
			
			if (!error) {
				TestStructure structure = TestStructureDAO.create(name,
						description, getUser().getId());
				setNextAction("teststruct.view&tsv_id=" + structure.getId());
			} else if ("cancel".equals(submit)) {

			}
		}
	}
}
