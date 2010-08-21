package oop.controller.action.test;

import java.util.List;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.BaseQuestion;
import oop.data.Question;
import oop.data.Resource;
import oop.data.Section;
import oop.data.Test;
import oop.data.Text;
import oop.db.dao.BaseQuestionDAO;
import oop.db.dao.ResourceDAO;
import oop.db.dao.TestDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class AddQuestionAction extends AbstractAction {

	private Test test;

	public AddQuestionAction() {
	}

	@Override
	public void performImpl() throws Exception {
		try {
			long testId = getParams().getLong("taq_test");
			resource = TestDAO.fetchById(testId);
			test = resource.getArticle().copy();
			title("Thêm câu hỏi vào đề " + test.getName());
			request.setAttribute("test", test);
		} catch (NumberFormatException e1) {
			throw new ActionException("Mã đề thi không hợp lệ.");
		} catch (ParameterNotFoundException e1) {
			throw new ActionException("Bạn cần chọn đề thi.");
		}

		String submit = getParams().get("taq_submit");
		if ("add".equals(submit)) {
			Section section = null;
			try {
				int sectionIndex = getParams().getInt("taq_section");
				section = test.getSections().get(sectionIndex).copy();
				test.getSections().set(sectionIndex, section);
			} catch (NumberFormatException e) {
				addError("section", "Mã số phần không hợp lệ.");
			} catch (ParameterNotFoundException e) {
				section = new Section(new Text(""));
				test.getSections().add(section);
			} catch (ArrayIndexOutOfBoundsException e) {
				addError("section", "Phần được chọn không tồn tại.");
			}

			if (!hasErrors()) {
				String mode = getParams().getString("mode");
				if ("id".equals(mode)) {
					addQuestionById(section);
				} else if ("search".equals(mode)) {
					addQuestionByAjaxSearch(section);
				} else if ("random".equals(mode)) {
					addQuestionRandomly(section);
				}
			}
		}
	}

	private void addQuestionRandomly(Section section) throws Exception {
		long topicId = getParams().getLong("taq_topicid");
		int quantity = getParams().getInt("taq_quantity");

		List<Resource<BaseQuestion>> bases = BaseQuestionDAO.fetchRandomly(
				resource.getId(), topicId, quantity);
		for (Resource<BaseQuestion> base : bases) {
			Question question = new Question(base, 1);
			section.getQuestions().add(question);
		}

		if (bases.size() == 0) {
			addError("quantity", "Chưa có câu hỏi nào được thêm. Xin hãy kiểm tra lại chủ đề bạn chọn.");
		} else if (bases.size() < quantity) {
			addError("quantity", "Chỉ thêm được " + bases.size()
					+ " câu hỏi. Có thể do chủ đề quá hẹp hoặc đã có trong bài kiểm tra.");
			setNextAction("test.addquestion&taq_quantity="
					+ (quantity - bases.size()));
		} else {
			addMessage("Đã thêm " + bases.size() + " câu hỏi.");
			goNextAction();
		}
	}

	private void addQuestionByAjaxSearch(Section section) throws Exception {
		try {
			Resource<BaseQuestion> base = ResourceDAO.fetchById(getParams()
					.getLong("taq_question"), BaseQuestion.class);
			section.getQuestions().add(new Question(base, 1));
			addMessage("Đã thêm 1 câu hỏi.");
			goNextAction();
		} catch (NumberFormatException ex) {
			addError("search", "Định dạng không hợp lệ");
		}
	}

	private void addQuestionById(Section section) throws Exception {
		try {
			Resource<BaseQuestion> base = ResourceDAO.fetchById(getParams()
					.getLong("taq_question"), BaseQuestion.class);
			section.getQuestions().add(new Question(base, 1));
			addMessage("Đã thêm 1 câu hỏi.");
			goNextAction();
		} catch (NumberFormatException ex) {
			addError("id", "Định dạng không hợp lệ");
		}
	}

	private void goNextAction() {
		if (getParams().hasParameter("more")) {
			setNextAction("test.addquestion&taq_question_id=&taq_question=&taq_content=&taq_topicid=&taq_topicname=&taq_submit=");
		} else {
			setNextAction("test.view&id=" + test.getId());
		}
	}
	private Resource<Test> resource;

	public Resource<Test> getResource() {
		return resource;
	}
}
