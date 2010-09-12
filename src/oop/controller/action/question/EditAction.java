package oop.controller.action.question;

import java.util.HashSet;
import java.util.Set;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Article;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.Text;
import oop.data.Topic;
import oop.db.dao.BaseQuestionDAO;
import oop.db.dao.TopicDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class EditAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		resource = BaseQuestionDAO.fetchById(getParams().getLong("id"));
		if (resource == null) {
			throw new ActionException("Không tìm thấy câu hỏi");
		}

		title("Sửa câu hỏi " + resource.getId());

		if (getParams().hasParameter("submit")) {
			doEdit();
		}
	}

	private void doEdit() throws Exception {
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
	private Resource<BaseQuestion> resource;	
	public Resource<BaseQuestion> getResource() {
		return resource;
	}

	public Article getQuestion() {
		return resource.getArticle();
	}

}
