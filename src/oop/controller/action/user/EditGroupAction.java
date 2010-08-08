package oop.controller.action.user;

import oop.controller.action.AbstractAction;
import oop.data.User;
import oop.db.dao.UserDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class EditGroupAction extends AbstractAction {
	private User user;

	@Override
	public void performImpl() throws Exception {
		try {
			long userId = getParams().getLong("user");
			user = UserDAO.fetchById(userId);
			
			if (getParams().hasParameter("submit")) {
				doUpdate();
			}
		} catch (ParameterNotFoundException ex) {
			error("Bạn cần chọn người sử dụng.");
		} catch (NumberFormatException ex) {
			error("ID không hợp lệ.");
		}
	}
	
	private void doUpdate() {
		try {
			String group = getParams().getString("group");
			user.setGroup(group);
			setNextAction("user.profile&user=" + user.getId());
		} catch (ParameterNotFoundException ex) {
			request.setAttribute("groupErr", "Bạn cần chọn nhóm người dùng");
		}
	}

	public User getEditedUser() {
		return user;
	}

}
