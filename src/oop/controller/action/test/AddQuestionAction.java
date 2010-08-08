package oop.controller.action.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.BaseQuestion;
import oop.data.Section;
import oop.data.Test;
import oop.db.dao.BaseQuestionDAO;
import oop.db.dao.QuestionDAO;
import oop.db.dao.SectionDAO;
import oop.db.dao.SectionQuestionAnswerDAO;
import oop.db.dao.TestDAO;

import org.apache.commons.lang.StringUtils;

import com.oreilly.servlet.ParameterNotFoundException;

public class AddQuestionAction extends AbstractAction {

	private Test test;
	private Section section;

	public AddQuestionAction() {
	}

	@Override
	public void performImpl() throws Exception {
		try {
			long testId = getParams().getLong("taq_test");
			test = TestDAO.fetchById(testId);
			title("Thêm câu hỏi vào đề " + test.getName());
			request.setAttribute("test", test);
		} catch (NumberFormatException e1) {
			error("Mã đề thi không hợp lệ.");
			return;
		} catch (ParameterNotFoundException e1) {
			error("Bạn cần chọn đề thi.");
			return;
		}
		
		String submit = getParams().get("taq_submit");
		if ("add".equals(submit)) {
			try {
				long sectionId = getParams().getLong("taq_section");
				section = SectionDAO.fetchById(sectionId);
			} catch (NumberFormatException e) {
				sectionError = "Mã số phần không hợp lệ.";
				return;
			} catch (ParameterNotFoundException e) {
				Set<Section> sections = test.getSections();
				if (sections.isEmpty()) {
					section = SectionDAO.create(null, 1, test.getId());
				} else {
					section = sections.iterator().next();
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				sectionError = "Phần được chọn không tồn tại.";
				return;
			}
			
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

	private void addQuestionRandomly(Section section) throws Exception {
		long topicId = getParams().getLong("taq_topicid");
		int quantity = getParams().getInt("taq_quantity");
		
		List<BaseQuestion> questions = BaseQuestionDAO.fetchRandomly(
				section.getTest().getId(), topicId, quantity);
		for (Article question : questions) {
			QuestionDAO.create(section.getId(), question
					.getId());
			SectionQuestionAnswerDAO.addAnswers(section.getId(), question
					.getId(), 4);
		}
		
		if (questions.size() == 0) {
			quantityError = "Chưa có câu hỏi nào được thêm. Xin hãy kiểm tra lại chủ đề bạn chọn.";
		} else if (questions.size() < quantity) {
			quantityError = "Chỉ thêm được " + questions.size()
					+ " câu hỏi. Có thể do chủ đề quá hẹp hoặc đã có trong bài kiểm tra.";
			setNextAction("test.addquestion&taq_quantity=" + (quantity - questions.size()));
		} else {
			message("Đã thêm " + questions.size() + " câu hỏi.");
			goNextAction();
		}
	}
	
	private void addQuestionByAjaxSearch(Section section) throws Exception {
		try {
			long questionId = getParams().getLong("taq_question");
			QuestionDAO.create(section.getId(), questionId);
			SectionQuestionAnswerDAO.addAnswers(section.getId(), questionId, 4);
			
			message("Đã thêm 1 câu hỏi.");
			goNextAction();
		} catch (NumberFormatException ex) {
			request.setAttribute("searchErr", "Định dạng không hợp lệ");
		} catch (SQLException ex) {
			switch (ex.getErrorCode()) {
			case 1062:
				request.setAttribute("searchErr", "Câu hỏi đã có từ trước.");
				break;
			case 1452:
				request.setAttribute("searchErr", "Câu hỏi không tồn tại.");
				break;
			default:
				throw ex;
			}
		}
	}

	private void addQuestionById(Section section) throws Exception {
		try {
			long questionId = getParams().getLong("taq_question_id");
			QuestionDAO.create(section.getId(), questionId);
			SectionQuestionAnswerDAO.addAnswers(section.getId(), questionId, 4);
			
			message("Đã thêm 1 câu hỏi.");
			goNextAction();
		} catch (NumberFormatException ex) {
			request.setAttribute("idErr", "Định dạng không hợp lệ");
		} catch (SQLException ex) {
			switch (ex.getErrorCode()) {
			case 1062:
				request.setAttribute("idErr", "Câu hỏi đã có từ trước.");
				break;
			case 1452:
				request.setAttribute("idErr", "Không tồn tại câu hỏi với ID "
						+ getParams().get("taq_question_id"));
				break;
			default:
				throw ex;
			}
		}
	}

	private void goNextAction() {
		if (getParams().hasParameter("more")) {
			setNextAction("test.addquestion&taq_question_id=&taq_question=&taq_content=&taq_topicid=&taq_topicname=&taq_submit=");
		} else {
			setNextAction("test.view&tv_id=" + test.getId());
		}
	}
	
	private String sectionError;
	private String quantityError;
	
	public String getSectionError() {
		return sectionError;
	}

	public String getQuantityError() {
		return quantityError;
	}
	
}
