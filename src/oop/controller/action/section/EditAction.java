package oop.controller.action.section;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Resource;
import oop.data.Section;
import oop.data.Test;
import oop.data.Text;
import oop.db.dao.ResourceDAO;
import oop.util.Utils;

import com.mysql.jdbc.StringUtils;

public class EditAction extends AbstractAction {

	private Test test;

	@Override
	public void performImpl() throws Exception {
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

				if (!hasErrors()) {
					section.setContent(new Text(text));
					saveNewRevision(resource, test);

					setNextAction("test.view&id=" + test.getId());
				}
			}
		} catch (NumberFormatException ex) {
			throw new ActionException("ID không hợp lệ");
		}
	}

	private Resource<Test> resource;
}
