package org.ocwiki.controller.action.sectstruct;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Resource;
import org.ocwiki.data.SectionStructure;
import org.ocwiki.data.TestStructure;
import org.ocwiki.data.Text;
import org.ocwiki.db.dao.TestStructureDAO;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			Resource<TestStructure> resource = TestStructureDAO
					.fetchById(getParams().getLong("tstr"));
			TestStructure test = resource.getArticle();
			request.setAttribute("test", test);
			title("Tạo phần mới trong cấu trúc đề " + test.getName());

			String submit = getParams().get("ssubmit");
			if ("create".equals(submit)) {
				String text = getParams().get("stext");

				test = test.copy();
				SectionStructure sectionStructure = new SectionStructure(
						new Text(text));
				test.getSectionStructures().add(sectionStructure);
				saveNewRevision(resource, test);

				setNextAction("teststruct.view&tstr=" + resource.getId());
			}
		} catch (NumberFormatException ex) {
			throw new ActionException("ID không hợp lệ.");
		}
	}

}
