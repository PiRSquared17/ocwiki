package oop.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.User;
import oop.db.dao.UserDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class EditGroupAction extends AbstractAction {
	private User user;

	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			long userId = getParams().getLong("user");
			user = UserDAO.fetchById(userId);
			
			if (getParams().hasParameter("submit")) {
				doUpdate();
			}
		} catch (ParameterNotFoundException ex) {
			throw new ActionException("Bạn cần chọn người sử dụng.");
		} catch (NumberFormatException ex) {
			throw new ActionException("ID không hợp lệ.");
		}
	}
	
	private void doUpdate() {
		try {
			String group = getParams().getString("group");
			user.setGroup(group);
			setNextAction("user.profile&user=" + user.getId());
		} catch (ParameterNotFoundException ex) {
			addError("group", "Bạn cần chọn nhóm người dùng");
		}
	}

	public User getEditedUser() {
		return user;
	}

}
