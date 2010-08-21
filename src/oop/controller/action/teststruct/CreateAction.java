package oop.controller.action.teststruct;

import oop.controller.action.AbstractAction;
import oop.data.Namespace;
import oop.data.Resource;
import oop.data.TestStructure;
import oop.data.Text;
import oop.db.dao.NamespaceDAO;

import org.apache.commons.lang.StringUtils;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
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

			if (!hasErrors()) {
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
