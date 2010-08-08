package oop.controller.action.question;

import java.sql.SQLException;

import oop.controller.action.AbstractAction;
import oop.data.BaseQuestion;
import oop.db.dao.BaseQuestionDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		title("Tạo câu hỏi mới");

		String submit = getParams().get("qc_submit");
		if ("create".equals(submit)) {
			doCreate();
		}
	}

	private void doCreate() throws SQLException {
		boolean error = false;

		String content = getParams().get("qc_content");

		int level = 3;
		try {
			level = getParams().getInt("qc_level");
			if (level < 1 || level > 5) {
				levelError = "Độ khó không hợp lệ.";
				error = true;
			}
		} catch (NumberFormatException ex) {
			levelError = "Độ khó không hợp lệ.";
			error = true;
		} catch (ParameterNotFoundException e) {
			levelError = "Bạn cần chọn độ khó.";
			error = true;
		}

		if (!error) {
			BaseQuestion question = BaseQuestionDAO.create(content, level,
					getUser().getId());
			setNextAction("answer.create&question=" + question.getId());
		}
	}

	private String levelError;

	public String getLevelError() {
		return levelError;
	}

}
