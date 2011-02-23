package org.ocwiki.controller.action.question;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Article;
import org.ocwiki.data.BaseQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Text;
import org.ocwiki.data.Topic;
import org.ocwiki.db.dao.BaseQuestionDAO;
import org.ocwiki.db.dao.TopicDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class EditAction extends AbstractResourceAction<BaseQuestion> {

	@Override
	public void performImpl() throws IOException, ServletException {
		resource = BaseQuestionDAO.fetchById(getParams().getLong("id"));
		if (resource == null) {
			throw new ActionException("Không tìm thấy câu hỏi");
		}

		title("Sửa câu hỏi " + resource.getId());

		if (getParams().hasParameter("submit")) {
			doEdit();
		}
	}

	private void doEdit() {
		BaseQuestion question = resource.getArticle().copy();
		
		Set<Resource<Topic>> newTopics = new HashSet<Resource<Topic>>();
		for (int i = 0; i < getParams().count("topics"); i++) {
			newTopics.add(TopicDAO.fetchById(getParams().getIndexedLong(
					"topics", i)));
		}
		question.setTopics(newTopics);

		int level = 3;
		try {
			level = getParams().getInt("level");
			if (level < 1 || level > 5) {
				addError("level", "Độ khó không hợp lệ.");
			} else {
				question.setLevel(level);
			}
		} catch (NumberFormatException ex) {
			addError("level", "Độ khó không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			addError("level", "Bạn cần chọn độ khó.");
		}

		String contentStr = getParams().get("content");
		if (!question.getContent().getText().equals(contentStr)) {
			question.setContent(new Text(contentStr));
		}

		if (hasNoErrors()) {
			saveNewRevision(resource, question);
			setNextAction("question.view&id=" + resource.getId());
		}
	}

	public Article getQuestion() {
		return resource.getArticle();
	}

}
