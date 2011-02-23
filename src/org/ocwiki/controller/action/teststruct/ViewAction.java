package org.ocwiki.controller.action.teststruct;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.TestStructure;
import org.ocwiki.db.dao.TestStructureDAO;

public class ViewAction extends AbstractResourceAction<TestStructure> {

	private TestStructure testStruct;

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			resource = TestStructureDAO.fetchById(getParams().getLong("tstr"));
			testStruct = resource.getArticle();
			title("Cấu trúc đề: " + testStruct.getName());
		} catch (NumberFormatException ex) {
			throw new ActionException("Id không hợp lệ: " + getParams().get("tstr"));
		}
	}

	public TestStructure getTest() {
		return testStruct;
	}
}
