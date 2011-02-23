package org.ocwiki.controller.action.section;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Section;
import org.ocwiki.data.Test;
import org.ocwiki.data.Text;
import org.ocwiki.db.dao.ResourceDAO;

public class CreateAction extends AbstractResourceAction<Test> {

	private Test test;

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			resource = ResourceDAO.fetchById(getParams().getLong("test"));
			if (resource == null) {
				throw new ActionException("Không tìm thấy đề thi được yêu cầu.");
			}
			test = resource.getArticle().copy();

			title("Thêm mục mới vào đề " + test.getName());

			String submit = getParams().get("submit");
			if ("create".equals(submit)) {
				doCreate();
			}
		} catch (NumberFormatException ex) {
			throw new ActionException("ID không hợp lệ.");
		}
	}

	private void doCreate() {
		String contentStr = getParams().get("text");
		int index = getParams().getInt("index", test.getSections().size());
		Section section = new Section(new Text(contentStr));
		test.getSections().add(index, section);
		saveNewRevision(resource, test);

		setNextAction("test.view&id=" + test.getId());
	}

	public Test getTest() {
		return test;
	}
	
}
