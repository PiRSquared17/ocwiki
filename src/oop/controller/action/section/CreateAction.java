package oop.controller.action.section;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Section;
import oop.data.Test;
import oop.data.Text;
import oop.db.dao.ResourceDAO;

public class CreateAction extends AbstractAction<Test> {

	private Test test;

	@Override
	public void performImpl() throws Exception {
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

	private void doCreate() throws Exception {
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
