package oop.controller.action.test;

import oop.controller.action.AbstractAction;
import oop.data.Resource;
import oop.data.Test;
import oop.data.Text;

import org.hibernate.exception.ConstraintViolationException;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		title("Tạo đề thi mới");
		String submit = getParams().get("submit");
		if ("create".equals(submit)) {
			doCreate();
		}
	}

	private void doCreate() throws Exception {
		Test test = new Test();

		try {
			String name = getParams().getString("name");
			test.setName(name);
		} catch (ParameterNotFoundException ex) {
			addError("name", "Bạn cần nhập tên đề thi.");
		}

		String contentStr = getParams().get("content");
		test.setContent(new Text(contentStr));

		try {
			int time = getParams().getInt("time");
			test.setTime(time);
		} catch (NumberFormatException e) {
			addError("time", "Định dạng thời gian không hợp lệ");
		} catch (ParameterNotFoundException e) {
			addError("time", "Bạn cần nhập thời gian");
		}

		try {
			String type = getParams().getString("type");
			test.setType(type);
		} catch (ParameterNotFoundException e1) {
			addError("type", "Bạn cần chọn kiểu đề thi.");
		}

		// boolean useStructure = getParams().hasParameter("usestruct");
		// TestStructure structure = null;
		// if (useStructure) {
		// long structureId;
		// try {
		// structureId = getParams().getLong("struct");
		// structure = TestStructureDAO.fetchById(structureId).getArticle();
		// } catch (NumberFormatException e) {
		// addError("struct", "Mã cấu trúc đề không hợp lệ.");
		// return;
		// } catch (ParameterNotFoundException e) {
		// addError("struct", "Bạn cần chọn cấu trúc đề.");
		// return;
		// }
		// }

		if (!hasErrors()) {
			try {
				Resource<Test> resource = saveNewResource(test);
				setNextAction("test.view&id=" + resource.getId());
			} catch (ConstraintViolationException ex) {
				addError("name", "Tên đề thi đã được sử dụng.");
			}
		}
	}
}
