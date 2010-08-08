package oop.controller.action.teststruct;

import java.sql.SQLException;
import java.util.List;

import oop.controller.action.AbstractAction;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;
import oop.taglib.UtilFunctions;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

public class ListAction extends AbstractAction {

	public static final int PAGE_LENGTH = 30;

	@Override
	public void performImpl() throws Exception {

		title("Danh sách các cấu trúc đề thi trên website trắc nghiệm CSForce");
		String submit = getParams().get("tsl_submit");
		if ("delete".equals(submit)) {
			try {
				if (!ArrayUtils.isEmpty(request.getParameterValues("selected"))) {
					for (String item : request.getParameterValues("selected")) {
						long id = Long.parseLong(item);
						TestStructureDAO.setDeleted(id, true);
					}
					message("Đã xóa " + getParams().count("selected") + " mục.");
				}
			} catch (NumberFormatException ex) {
				error("ID không hợp lệ.");
			} catch (ConstraintViolationException ex) {
				error("Không thể xóa cấu trúc đề thi đang sử dụng");
			}
		}

		int page = getParams().getInt("page", 1);
		String topicIdStr = getParams().get("topic");
		String authorIdStr = getParams().get("author");

		List<TestStructure> testStructures;
		long count;
		if (!StringUtils.isEmpty(topicIdStr)) {
			long id = Long.parseLong(topicIdStr);
			testStructures = TestStructureDAO.fetchByTopic(id, (page - 1)
					* PAGE_LENGTH, PAGE_LENGTH);
			count = TestStructureDAO.countByTopic(id);
		} else if (!StringUtils.isEmpty(authorIdStr)) {
			long id = Long.parseLong(authorIdStr);
			testStructures = TestStructureDAO.fetchByAuthor(id, (page - 1)
					* PAGE_LENGTH, PAGE_LENGTH);
			count = TestStructureDAO.countByAuthor(id);
		} else {
			testStructures = TestStructureDAO.fetch((page - 1) * PAGE_LENGTH,
					PAGE_LENGTH);
			count = TestStructureDAO.count();
		}

		request.setAttribute("structs", testStructures);
		request.setAttribute("page", page);
		request.setAttribute("pageCount", UtilFunctions.ceil(count
				/ (double) PAGE_LENGTH));
	}

}
