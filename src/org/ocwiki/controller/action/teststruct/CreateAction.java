package org.ocwiki.controller.action.teststruct;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.Namespace;
import org.ocwiki.data.Resource;
import org.ocwiki.data.TestStructure;
import org.ocwiki.data.Text;
import org.ocwiki.db.dao.NamespaceDAO;

import org.apache.commons.lang.StringUtils;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		String submit = getParams().get("tsubmit");
		if ("create".equals(submit)) {
			String name = getParams().get("tname");
			if (StringUtils.isEmpty(name)) {
				addError("name", "Bạn cần nhập tên cấu trúc đề.");
			}

			String description = getParams().get("tdescription");
			if (StringUtils.isEmpty(description)) {
				addError("description", "Bạn cần nhập mô tả cấu trúc đề");
			}

			if (hasNoErrors()) {
				Namespace namespace = NamespaceDAO
						.fetch(Namespace.TEST_STRUCTURE);
				TestStructure structure = new TestStructure(namespace, name,
						new Text(description), "abc", 90);
				Resource<TestStructure> resource = saveNewResource(structure);
				setNextAction("teststruct.view&tstr=" + resource.getId());
			} else if ("cancel".equals(submit)) {

			}
		}
	}
}
