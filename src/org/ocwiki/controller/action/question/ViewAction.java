package org.ocwiki.controller.action.question;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.db.dao.MultichoiceQuestionDAO;

public class ViewAction extends AbstractResourceAction<MultichoiceQuestion> {
	
	private MultichoiceQuestion question;

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			resource = MultichoiceQuestionDAO.fetchById(getParams().getLong("id"));
			if (resource == null) {
				throw new ActionException("Không tìm thấy câu hỏi");
			} 
			question = resource.getArticle();

			title("Câu hỏi " + resource.getId());
		} catch (NumberFormatException ex) {
			throw new ActionException("Id không hợp lệ: " + getParams().getString("id"));
		}
	}
	
	public MultichoiceQuestion getQuestion() {
		return question;
	}
	
}
