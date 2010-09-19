package oop.controller.action.test;

import oop.controller.action.AbstractAction;
import oop.data.Question;
import oop.data.Resource;
import oop.data.Section;
import oop.data.Test;
import oop.db.dao.ResourceDAO;
import oop.util.Utils;

import com.oreilly.servlet.ParameterNotFoundException;

public class EditQuestionAction extends AbstractAction {

	private Section section;
	private Test test;
	private Question question;
	private Resource<Test> resource;

	@Override
	public void performImpl() throws Exception {
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

	private void doEdit() throws Exception {
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
