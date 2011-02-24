package org.ocwiki.controller.action.test;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.data.TestQuestion;
import org.ocwiki.data.Section;
import org.ocwiki.data.Test;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.util.Utils;

import com.oreilly.servlet.ParameterNotFoundException;

public class EditQuestionAction extends AbstractResourceAction<Test> {

	private Section section;
	private Test test;
	private TestQuestion question;

	@Override
	public void performImpl() throws IOException, ServletException {
		resource = ResourceDAO.fetchById(getParams().getLong("test"));
		test = resource.getArticle();
		section = Utils.replaceByCopy(test.getSections(), getParams().getInt(
				"section"));
		question = Utils.replaceByCopy(section.getQuestions(), getParams()
				.getInt("question"));

		String submit = getParams().get("submit");
		if ("save".equals(submit)) {
			doEdit();
		}
	}

	private void doEdit() {
		test = test.copy();

		try {
			Section newSection = Utils.replaceByCopy(test.getSections(),
					getParams().getInt("section_new"));
			newSection.getQuestions().add(question);
			section.getQuestions().remove(question);
		} catch (NumberFormatException e) {
			addError("section", "Mã số phần không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			addError("section", "Bạn cần chọn phần.");
		} catch (ArrayIndexOutOfBoundsException e) {
			addError("section", "Phần được chọn không tồn tại.");
		}

		if (hasNoErrors()) {
			saveNewRevision(resource, test);
			setNextAction("test.view&id=" + test.getId());
		}
	}
}
