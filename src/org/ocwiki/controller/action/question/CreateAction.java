package org.ocwiki.controller.action.question;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Namespace;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Text;
import org.ocwiki.db.dao.NamespaceDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class CreateAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		title("Tạo câu hỏi mới");

		String submit = getParams().get("qc_submit");
		if ("create".equals(submit)) {
			doCreate();
		}
	}

	private void doCreate() {
		String content = getParams().get("qc_content");

		int level = 3;
		try {
			level = getParams().getInt("qc_level");
			if (level < 1 || level > 5) {
				addError("level", "Độ khó không hợp lệ.");
			}
		} catch (NumberFormatException ex) {
			addError("level", "Độ khó không hợp lệ.");
		} catch (ParameterNotFoundException e) {
			addError("level", "Bạn cần chọn độ khó.");
		}

		if (hasNoErrors()) {
			Namespace namespace = NamespaceDAO.fetch(Namespace.QUESTION);
			MultichoiceQuestion question = new MultichoiceQuestion(namespace, new Text(
					content), level);
			Resource<MultichoiceQuestion> resource = saveNewResource(question);
			setNextAction("answer.create&question=" + resource.getId());
		}
	}
}
