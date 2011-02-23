package org.ocwiki.controller.action.section;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Section;
import org.ocwiki.data.Test;
import org.ocwiki.data.Text;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.util.Utils;

import com.mysql.jdbc.StringUtils;

public class EditAction extends AbstractResourceAction<Test> {

	private Test test;

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			resource = ResourceDAO.fetchById(getParams().getLong("test"));
			test = resource.getArticle().copy();
			Section section = Utils.replaceByCopy(test.getSections(),
					getParams().getInt("section"));

			String submit = getParams().get("submit");
			if (!StringUtils.isNullOrEmpty(submit)) {
				String text = getParams().get("text");

				String orderStr = getParams().get("order");
				int order;
				try {
					if ("last".equals(orderStr)) {
						order = test.getSections().size() - 1;
					} else {
						order = Integer.parseInt(orderStr);
					}
					test.getSections().remove(section);
					test.getSections().add(order, section);
				} catch (NumberFormatException ex) {
					addError("order", "Định dạng không hợp lệ.");
				}

				if (hasNoErrors()) {
					section.setContent(new Text(text));
					saveNewRevision(resource, test);

					setNextAction("test.view&id=" + test.getId());
				}
			}
		} catch (NumberFormatException ex) {
			throw new ActionException("ID không hợp lệ");
		}
	}

}
