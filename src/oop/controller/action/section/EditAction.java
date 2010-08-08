package oop.controller.action.section;

import java.sql.SQLException;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Section;
import oop.data.Text;
import oop.db.dao.SectionDAO;

import com.mysql.jdbc.StringUtils;

public class EditAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		try {
			long sectionId = getParams().getLong("se_id");
			Section section = SectionDAO.fetchById(sectionId);
			Article test = section.getTest();
			request.setAttribute("section", section);
			request.setAttribute("test", test);

			String submit = getParams().get("se_submit");
			if (!StringUtils.isNullOrEmpty(submit)) {
				doEdit(section, test);
			}
		} catch (NumberFormatException ex) {
			error("ID không hợp lệ");
		}
	}

	private void doEdit(Section section, Article test) throws SQLException {
		String text = getParams().get("se_text");

		String orderStr = getParams().get("se_order");
		int order;
		try {
			if ("last".equals(orderStr)) {
				order = SectionDAO.nextAvailableOrder(test.getId());
			} else {
				order = Integer.parseInt(orderStr);
				if (section.getOrder() != order) {
					SectionDAO.shiftDown(test.getId(), order);
				}
			}
		} catch (NumberFormatException ex) {
			orderError = "Định dạng không hợp lệ.";
			return;
		}

		section.setText(new Text(text));
		section.setOrder(order);
		SectionDAO.persist(section);
		SectionDAO.normalize(test.getId());

		setNextAction("test.view&tv_id=" + test.getId());
	}

	private String orderError;

	public String getOrderError() {
		return orderError;
	}
	
}
