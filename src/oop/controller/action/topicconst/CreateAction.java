package oop.controller.action.topicconst;

import java.sql.SQLException;
import java.util.Set;

import oop.controller.action.AbstractAction;
import oop.data.SectionStructure;
import oop.data.TestStructure;
import oop.db.dao.SectionStructureDAO;
import oop.db.dao.TestStructureDAO;
import oop.db.dao.TopicConstraintDAO;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractAction {
	
	public CreateAction() {
	}

	@Override
	public void performImpl() throws Exception {
		long testStructureId = getParams().getLong("teststruct");
		TestStructure testStructure = TestStructureDAO.fetchById(testStructureId);
		request.setAttribute("test", testStructure);

		String submit = getParams().get("submit");
		if ("add".equals(submit)) {
			doCreate(testStructure);
		}
	}

	private void doCreate(TestStructure testStructure) throws SQLException {
		String sectionIdStr = getParams().get("section");
		SectionStructure sectionStructure;
		if (StringUtils.isEmpty(sectionIdStr)) {
			Set<SectionStructure> sectionStructures = testStructure.getSectionStructures();
			if (sectionStructures.isEmpty()) {
				sectionStructure = SectionStructureDAO.create(null,
						testStructure.getId(), 1);
			} else {
				if (sectionStructures.size() == 1) {
					sectionStructure = sectionStructures.iterator().next();
				} else {
					sectionError = "Bạn cần chọn một mục.";
					return;
				}
			}
		} else {
			try {
				long sectionId = Long.parseLong(sectionIdStr);
				sectionStructure = SectionStructureDAO.fetchById(sectionId);
			} catch (NumberFormatException ex) {
				sectionError = "Mã số mục không hợp lệ.";
				return;
			}
		}

		int quantity;
		try {
			quantity = getParams().getInt("quantity");
			if (quantity <= 0) {
				quantityError = "Số lượng phải nguyên dương.";
				return;
			}
		} catch (ParameterNotFoundException ex) {
			quantityError = "Bạn cần nhập số lượng.";
			return;
		} catch (NumberFormatException ex) {
			quantityError = "Số lượng không hợp lệ.";
			return;
		}

		long topicId;
		try {
			topicId = getParams().getLong("topicid");
		} catch (ParameterNotFoundException ex) {
			topicError = "Bạn cần chọn chủ đề.";
			return;
		} catch (NumberFormatException ex) {
			topicError = "Mã số chủ đề không hợp lệ.";
			return;
		}

		try {
			TopicConstraintDAO.create(sectionStructure.getId(), quantity);
			setNextAction("teststruct.view&tsv_id=" + testStructure);
		} catch (ConstraintViolationException ex) {
			//XXX kiểm tra!!!
			if (ex.getErrorCode() == 1062) {
				topicError = "Chủ đề đã được thêm từ trước.";
			} else if (ex.getErrorCode() == 1452) {
				topicError = "Chủ đề không tồn tại.";
			} else {
				throw ex;
			}
		}
	}

	private String sectionError = null;
	private String topicError = null;
	private String quantityError = null;

	public String getSectionError() {
		return sectionError;
	}
	
	public String getTopicError() {
		return topicError;
	}
	
	public String getQuantityError() {
		return quantityError;
	}
	
}
