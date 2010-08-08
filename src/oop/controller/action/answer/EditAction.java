package oop.controller.action.answer;

import oop.controller.action.AbstractAction;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.Text;
import oop.db.dao.BaseQuestionDAO;

import com.mysql.jdbc.StringUtils;
import com.oreilly.servlet.ParameterNotFoundException;

public class EditAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		long questionId;
		try {
			questionId = getParams().getLong("question");
		} catch (NumberFormatException e) {
			error("Mã câu hỏi không hợp lệ.");
			return;
		} catch (ParameterNotFoundException e) {
			error("Bạn cần chọn câu hỏi.");
			return;
		}
		BaseQuestion question = BaseQuestionDAO.fetchById(questionId);
		
		long answerId;
		try {
			answerId = getParams().getLong("answer");
		} catch (NumberFormatException e) {
			error("Mã phương án trả lời không hợp lệ.");
			return;
		} catch (ParameterNotFoundException e) {
			error("Bạn cần chọn phương án trả lời.");
			return;
		}
		
		title("Sửa câu trả lời #" + answerId);
		request.setAttribute("question", question);
		request.setAttribute("editing", answerId);
		
		String submit = getParams().get("submit");
		if ("save".equals(submit)) {

			String content;
			try {
				content = getParams().getString("content");
			} catch (ParameterNotFoundException e) {
				contentError = "Bạn cần nhập nội dung phương án trả lời.";
				return;
			}

			boolean correct = getParams().getBoolean("correct", false);
			
			Answer answer = question.getAnswerById().get(answerId);
			answer.setContent(new Text(content));
			answer.setCorrect(correct);
			
			setNextAction("question.view&qv_id=" + questionId);
		}
	}

	private String contentError;

	public String getContentError() {
		return contentError;
	}

}
