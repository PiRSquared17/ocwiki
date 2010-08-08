package oop.controller.action.teststruct;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import oop.controller.action.AbstractAction;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;
import oop.db.dao.TextDAO;

public class EditAction extends AbstractAction {

	protected TestStructure testStructure;

	@Override
	public void performImpl() throws Exception {
		long id = getParams().getLong("tse_id");
		TestStructure testStructure = TestStructureDAO.fetchById(id);
		request.setAttribute("testStruct", testStructure);

		String submit = getParams().get("tse_submit");
		if ("update".equals(submit)) {
			try {
				testStructure.setName(getParams().get("tse_name"));
				testStructure.setContent(TextDAO.create(getParams()
						.getString("tse_description")));
				TestStructureDAO.persist(testStructure);

				setNextAction("teststruct.view&tsv_id=" + testStructure.getId());
			} catch (ConstraintViolationException ex) {
					request.setAttribute("nameErr",
							"Tên cấu trúc đề thi đã dùng.");
			}
		}
	}

}
