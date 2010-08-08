package oop.controller.action.test;

import java.sql.SQLException;

import oop.controller.action.AbstractAction;
import oop.data.Test;
import oop.data.TestStructure;
import oop.db.dao.TestDAO;
import oop.db.dao.TestStructureDAO;

import org.hibernate.exception.ConstraintViolationException;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractAction {
	
	@Override
	public void performImpl() throws Exception {
		title("Tạo đề thi mới");
		String submit = getParams().get("tc_submit");
		if ("create".equals(submit)) {
			doCreate();
		}
	}

	private void doCreate() throws SQLException {
		String name = null;
		try {
			name = getParams().getString("tc_name");
		} catch (ParameterNotFoundException ex) {
			request.setAttribute("nameErr", "Bạn cần nhập tên đề thi.");
			return;
		}
		
		String description = getParams().get("tc_description");
		
		int time;
		try {
			time = getParams().getInt("tc_time");
		} catch (NumberFormatException e) {
			timeError = "Định dạng thời gian không hợp lệ";
			return;
		} catch (ParameterNotFoundException e) {
			timeError = "Bạn cần nhập thời gian";
			return;
		}
		
		String type;
		try {
			type = getParams().getString("tc_type");
		} catch (ParameterNotFoundException e1) {
			typeError = "Bạn cần chọn kiểu đề thi.";
			return;
		}
		
		boolean useStructure = getParams().hasParameter("tc_usestruct");
		TestStructure structure = null;
		if (useStructure) {
			long structureId;
			try {
				structureId = getParams().getLong("tc_struct");
				structure = TestStructureDAO.fetchById(structureId);
			} catch (NumberFormatException e) {
				structError = "Mã cấu trúc đề không hợp lệ.";
				return;
			} catch (ParameterNotFoundException e) {
				structError = "Bạn cần chọn cấu trúc đề.";
				return;
			}
		}

		try {
			Test test = TestDAO.create(name, description, getUser()
					.getId(), type, time);
			setNextAction("test.view&tv_id=" + test.getId());
		} catch (ConstraintViolationException ex) {
			request.setAttribute("nameErr", "Tên đề thi đã được sử dụng.");
			return;
		}
	}

	private String timeError;
	private String structError;
	private String typeError;
	
	public String getTimeError() {
		return timeError;
	}
	
	public String getStructError() {
		return structError;
	}
	
	public String getTypeError() {
		return typeError;
	}
}
