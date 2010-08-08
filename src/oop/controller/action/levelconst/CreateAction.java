package oop.controller.action.levelconst;

import java.util.Set;

import oop.controller.action.AbstractAction;
import oop.data.SectionStructure;
import oop.data.TestStructure;
import oop.db.dao.LevelConstraintDAO;
import oop.db.dao.SectionStructureDAO;
import oop.db.dao.TestStructureDAO;

import org.hibernate.exception.ConstraintViolationException;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractAction {

	public CreateAction() {
	}

	@Override
	public void performImpl() throws Exception {
		TestStructure testStructure = TestStructureDAO.fetchById(getParams()
				.getLong("teststruct"));
		request.setAttribute("test", testStructure);

		String submit = getParams().get("submit");
		if ("add".equals(submit)) {
			doCreate(testStructure);
		}
	}

	private void doCreate(TestStructure testStructure) throws Exception {
		SectionStructure sectionStructure = null;
		try {
			long sectionId = getParams().getLong("section");
			sectionStructure = SectionStructureDAO.fetchById(sectionId);
		} catch (NumberFormatException ex) {
			sectionError = "Mã số mục không hợp lệ.";
		} catch (ParameterNotFoundException ex) {
			Set<SectionStructure> sectionStructures = testStructure
					.getSectionStructures();
			if (sectionStructures.isEmpty()) {
				sectionStructure = SectionStructureDAO.create(null,
						testStructure.getId(), 1);
			} else {
				if (sectionStructures.size() == 1) {
					sectionStructure = sectionStructures.iterator().next();
				} else {
					sectionError = "Bạn cần chọn một mục.";
				}
			}
		}

		int quantity = 0;
		try {
			quantity = getParams().getInt("quantity");
			if (quantity <= 0) {
				quantityError = "Số lượng phải nguyên dương.";
			}
		} catch (ParameterNotFoundException ex) {
			quantityError = "Bạn cần nhập số lượng.";
		} catch (NumberFormatException ex) {
			quantityError = "Số lượng không hợp lệ.";
		}

		int level = -1;
		try {
			level = getParams().getInt("level");
			if (level < 1 || level > 5) {
				levelError = "Độ khó không hợp lệ.";
			}
		} catch (ParameterNotFoundException ex) {
			levelError = "Bạn cần chọn độ khó.";
		} catch (NumberFormatException ex) {
			levelError = "Độ khó không hợp lệ.";
		}

		if (sectionError == null && levelError == null && quantityError == null) {
			try {
				LevelConstraintDAO.create(sectionStructure.getId(), level,
						quantity);
				setNextAction("teststruct.view&tsv_id=" + testStructure);
			} catch (ConstraintViolationException ex) {
				levelError = "Độ khó đã được thêm từ trước.";
			}
		}
	}

	private String sectionError = null;
	private String levelError = null;
	private String quantityError = null;

	public String getSectionError() {
		return sectionError;
	}
	
	public String getLevelError() {
		return levelError;
	}
	
	public String getQuantityError() {
		return quantityError;
	}
	
}
