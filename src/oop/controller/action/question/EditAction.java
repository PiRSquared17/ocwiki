package oop.controller.action.question;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils;

import oop.change.article.ArticleContentChange;
import oop.change.article.ArticleTopicsChange;
import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.BaseQuestion;
import oop.data.Topic;
import oop.db.dao.BaseQuestionDAO;
import oop.db.dao.TextDAO;
import oop.db.dao.TopicDAO;
import oop.util.Utils;

import com.oreilly.servlet.ParameterNotFoundException;

public class EditAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		question = BaseQuestionDAO.fetchById(getParams().getLong("id"));
		if (question == null) {
			error("Không tìm thấy câu hỏi");
			return;
		}

		title("Sửa câu hỏi " + question.getId());

		if (getParams().hasParameter("submit")) {
			doEdit();
		}
	}

	private void doEdit() throws Exception {
		Set<Topic> newTopics = new HashSet<Topic>();
		for (int i = 0; i < getParams().count("topics"); i++) {
			newTopics.add(TopicDAO.fetchById(getParams().getIndexedLong(
					"topics", i)));
		}
		change(question, new ArticleTopicsChange(newTopics), "", false);

		int level = 3;
		try {
			level = getParams().getInt("qe_level");
			if (level < 1 || level > 5) {
				levelError = "Độ khó không hợp lệ.";
			} else {
				question.setLevel(level);
			}
		} catch (NumberFormatException ex) {
			levelError = "Độ khó không hợp lệ.";
		} catch (ParameterNotFoundException e) {
			levelError = "Bạn cần chọn độ khó.";
		}

		String content = getParams().get("qe_content");
		if (!question.getContent().getText().equals(content)) {
			change(question, new ArticleContentChange(TextDAO.create(content)),
					"", false);
		}

		if (Utils.isNull(levelError)) {
			setNextAction("question.view&qv_id=" + question.getId());
		}
	}

	private String levelError;
	private BaseQuestion question;

	public String getLevelError() {
		return levelError;
	}

	public Article getQuestion() {
		return question;
	}

}
