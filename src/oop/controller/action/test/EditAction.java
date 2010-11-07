package oop.controller.action.test;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.Test;
import oop.data.Text;
import oop.db.dao.TestDAO;

import com.oreilly.servlet.ParameterList;
import com.oreilly.servlet.ParameterNotFoundException;

public class EditAction extends AbstractResourceAction<Test> {

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			long id = getParams().getLong("te_id");
			resource = TestDAO.fetchById(id);
			test = resource.getArticle();
			title("Thay đổi nội dung đề " + resource.getId());

			String submit = getParams().get("te_submit");
			if ("update".equals(submit)) {
				doUpdate(getParams(), test);
			}
		} catch (NullPointerException ex) {
			throw new ActionException("Không tìm thấy đề thi: " + getParams().get("te_id"));
		} catch (NumberFormatException ex) {
			throw new ActionException("Mã số đề thi không hợp lệ: " + getParams().get("te_id"));
		}
	}

	private void doUpdate(ParameterList parser, Test test) {
		test = test.copy();

		try {
			String name = parser.getString("te_name");
			test.setName(name);
		} catch (ParameterNotFoundException ex) {
			addError("name", "Bạn cần nhập tên đề thi.");
		}

		String contentStr = getParams().get("te_description");
		test.setContent(new Text(contentStr));

		try {
			int time = parser.getInt("te_time");
			test.setTime(time);
		} catch (NumberFormatException e) {
			addError("time", "Định dạng thời gian không hợp lệ");
		} catch (ParameterNotFoundException e) {
			addError("time", "Bạn cần nhập thời gian");
		}

		try {
			String type = parser.getString("te_type");
			test.setType(type);
		} catch (ParameterNotFoundException e1) {
			addError("type", "Bạn cần chọn kiểu đề thi.");
		}

		if (hasNoErrors()) {
			saveNewRevision(resource, test);
			setNextAction("test.view&id=" + resource.getId());
		}
	}

	private Test test;

	public Test getTest() {
		return test;
	}
}
