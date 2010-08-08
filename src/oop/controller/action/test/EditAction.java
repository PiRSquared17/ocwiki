package oop.controller.action.test;

import java.sql.SQLException;

import oop.controller.action.AbstractAction;
import oop.data.Test;
import oop.data.Text;
import oop.db.dao.TestDAO;

import org.hibernate.exception.ConstraintViolationException;

import com.oreilly.servlet.ParameterList;
import com.oreilly.servlet.ParameterNotFoundException;

public class EditAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		try {
			long id = getParams().getLong("te_id");
			Test test = TestDAO.fetchById(id);
			request.setAttribute("test", test);
			title("Thay đổi nội dung đề " + test.getId());

			String submit = getParams().get("te_submit");
			if ("update".equals(submit)) {
				doUpdate(getParams(), test);
			}
		} catch (NullPointerException ex) {
			error("Không tìm thấy đề thi: " + getParams().get("te_id"));
		} catch (NumberFormatException ex) {
			error("Mã số đề thi không hợp lệ: " + getParams().get("te_id"));
		}
	}

	private void doUpdate(ParameterList parser, Test test)
			throws ParameterNotFoundException, SQLException {
		String name = null;
		try {
			name = parser.getString("te_name");
		} catch (ParameterNotFoundException ex) {
			nameError = "Bạn cần nhập tên đề thi.";
			return;
		}

		String descpription = getParams().get("te_description");

		int time;
		try {
			time = parser.getInt("te_time");
		} catch (NumberFormatException e) {
			timeError = "Định dạng thời gian không hợp lệ";
			return;
		} catch (ParameterNotFoundException e) {
			timeError = "Bạn cần nhập thời gian";
			return;
		}

		String type;
		try {
			type = parser.getString("tc_type");
		} catch (ParameterNotFoundException e1) {
			typeError = "Bạn cần chọn kiểu đề thi.";
			return;
		}

		try {
			test.setName(name);
			test.setContent(new Text(descpription));
			test.setTime(time);
			test.setType(type);

			setNextAction("test.view&tv_id=" + test.getId());
		} catch (ConstraintViolationException ex) {
			if (ex.getErrorCode() == 1451) {
				error("Không thể sửa đề thi đang sử dụng");
				return;
			} else if (ex.getErrorCode() == 1062) { // XXX bắt lỗi
				request.setAttribute("nameErr", "Tên đề thi đã dùng.");
			} else {
				throw ex;
			}
		}
	}

	private String timeError;
	private String typeError;
	private String nameError;

	public String getTimeError() {
		return timeError;
	}

	public String getTypeError() {
		return typeError;
	}

	public String getNameError() {
		return nameError;
	}

}
