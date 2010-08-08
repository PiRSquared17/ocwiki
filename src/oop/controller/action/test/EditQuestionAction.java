package oop.controller.action.test;

import java.sql.SQLException;
import java.util.Arrays;

import oop.controller.action.AbstractAction;
import oop.data.Answer;
import oop.data.Article;
import oop.data.Question;
import oop.data.Section;
import oop.db.dao.QuestionDAO;
import oop.db.dao.SectionDAO;
import oop.db.dao.SectionQuestionAnswerDAO;
import oop.db.dao.TestDAO;

import org.apache.commons.lang.ArrayUtils;

import com.oreilly.servlet.ParameterNotFoundException;

public class EditQuestionAction extends AbstractAction {

	private Section section;
	private Article test;
	private Question question;
	private String sectionError;
	private String answersError;

	@Override
	public void performImpl() throws Exception {
		test = TestDAO.fetchById(getParams().getLong("teq_test"));
		section = SectionDAO.fetchById(getParams().getLong("teq_section"));
		question = QuestionDAO.fetchBySectionQuestion(section.getId(),
				getParams().getLong("teq_question"));

		request.setAttribute("test", test);
		request.setAttribute("section", section);
		request.setAttribute("question", question);

		String submit = getParams().get("teq_submit");
		if ("save".equals(submit)) {
			doEdit();
		}
	}

	private void doEdit() throws SQLException {
		Section newSection;
		try {
			long newSectionId = getParams().getLong("teq_section_new");
			newSection = SectionDAO.fetchById(newSectionId);
		} catch (NumberFormatException e) {
			sectionError = "Mã số phần không hợp lệ.";
			return;
		} catch (ParameterNotFoundException e) {
			sectionError = "Bạn cần chọn phần.";
			return;
		} catch (ArrayIndexOutOfBoundsException e) {
			sectionError = "Phần được chọn không tồn tại.";
			return;
		}
		
		String[] params = request.getParameterValues("teq_answers");
		if (ArrayUtils.isEmpty(params)) {
			answersError = "Phải chọn ít nhất một đáp án.";
			return;
		}
		long[] answerIds = new long[params.length];
		for (int i = 0; i < params.length; i++) {
			answerIds[i] = Long.parseLong(params[i]);
		}
		Arrays.sort(answerIds);

		boolean noCorrect = true;
		for (Answer answer : question.getAnswers()) {
			boolean enabled = Arrays.binarySearch(answerIds, answer.getId()) >= 0;
			if (answer.isCorrect() && enabled) {
				noCorrect = false;
			}
		}
		if (noCorrect) {
			answersError = "Phải có ít nhất một đáp án đúng.";
			return;
		}

		if (section.getId() != newSection.getId()) {
			//XXX sửa sửa sửa!!!
//			SectionQuestionDAO
//					.removeQuestion(section.getId(), question.getId());
//			QuestionDAO
//					.create(newSection.getId(), question.getId());
//
//			Set<Answer> usedAnswers = question.getAnswers(); // lưu trước tránh mất dữ liệu
//			SectionQuestionAnswerDAO.removeAnswers(section.getId(), question
//					.getId());
//			for (Answer answer : usedAnswers) {
//				SectionQuestionAnswerDAO.addAnswer(newSection.getId(), question
//						.getId(), answer.getId());
//			}
//			section = newSection;
		}

		for (Answer answer : question.getBase().getAnswers()) {
			long answerId = answer.getId();
			boolean used = question.getAnswers().contains(answer);
			boolean enabled = Arrays.binarySearch(answerIds, answerId) >= 0;
			if (used && !enabled) {
				SectionQuestionAnswerDAO.removeAnswer(section.getId(), question
						.getId(), answerId);
			} else if (!used && enabled) {
				SectionQuestionAnswerDAO.addAnswer(section.getId(), question
						.getId(), answerId);
			}
		}
		
		setNextAction("test.view&tv_id=" + test.getId());
	}

	public String getSectionError() {
		return sectionError;
	}
	
	public String getAnswersError() {
		return answersError;
	}
	
}
