package oop.controller.action.answer;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.Text;
import oop.db.dao.ResourceDAO;
import oop.util.Utils;

import com.oreilly.servlet.ParameterNotFoundException;

public class EditAction extends AbstractResourceAction<BaseQuestion> {

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			resource = ResourceDAO.fetchById(getParams().getLong("question"));
		} catch (NumberFormatException e) {
			throw new ActionException("Mã câu hỏi không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			throw new ActionException("Bạn cần chọn câu hỏi.");
		}
		
		int answerIndex;
		try {
			answerIndex = getParams().getInt("answer");
		} catch (NumberFormatException e) {
			throw new ActionException("Mã phương án trả lời không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			throw new ActionException("Bạn cần chọn phương án trả lời.");
		}
		
		title("Sửa câu trả lời #" + answerIndex);
		request.setAttribute("question", resource.getArticle());
		
		String submit = getParams().get("submit");
		if ("save".equals(submit)) {

			BaseQuestion question = resource.getArticle().copy();
			Answer answer = Utils.replaceByCopy(question.getAnswers(),
					answerIndex);

			try {
				String content = getParams().getString("content");
				answer.setContent(new Text(content));
			} catch (ParameterNotFoundException e) {
				addError("content", "Bạn cần nhập nội dung phương án trả lời.");
			}

			boolean correct = getParams().getBoolean("correct", false);
			answer.setCorrect(correct);

			if (hasNoErrors()) {
				saveNewRevision(resource, question);
				setNextAction("question.view&id=" + resource.getId());
			}
		}
	}	

}
