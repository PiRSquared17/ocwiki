package oop.controller.action.teststruct;

import oop.controller.action.AbstractAction;
import oop.data.Resource;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;
import oop.db.dao.TextDAO;

import org.hibernate.exception.ConstraintViolationException;

public class EditAction extends AbstractAction {

	protected TestStructure testStructure;
	private Resource<TestStructure> resource;

	@Override
	public void performImpl() throws Exception {
		long id = getParams().getLong("tid");
		resource = TestStructureDAO.fetchById(id);
		testStructure = resource.getArticle();

		String submit = getParams().get("tsubmit");
		if ("update".equals(submit)) {
			try {
				testStructure.copy();
				testStructure.setName(getParams().get("tname"));
				testStructure.setContent(TextDAO.create(getParams()
						.getString("tdescription")));
				saveNewRevision(resource, testStructure);

				setNextAction("teststruct.view&tstr=" + resource.getId());
			} catch (ConstraintViolationException ex) {
				addError("name", "Tên cấu trúc đề thi đã dùng.");
			}
		}
	}

	public Resource<TestStructure> getResource() {
		return resource;
	}
	
	public TestStructure getTestStructure() {
		return testStructure;
	}
	
}
