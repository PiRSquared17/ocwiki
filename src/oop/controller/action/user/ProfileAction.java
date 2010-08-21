package oop.controller.action.user;

import java.util.List;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.History;
import oop.data.Resource;
import oop.data.Test;
import oop.data.User;
import oop.db.dao.HistoryDAO;
import oop.db.dao.TestDAO;
import oop.db.dao.UserDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class ProfileAction extends AbstractAction {

	private User user;
	private List<History> histories;
	private List<Resource<Test>> tests;

	@Override
	public void performImpl() throws Exception {
		try {
			long userId = getParams().getLong("user");
			user = UserDAO.fetchById(userId);
			
			title("Hồ sơ người dùng " + user.getName());
			
			histories = HistoryDAO.fetchByUser(userId, 0, 5);
			tests = TestDAO.fetchByAuthor(userId, 0, 5);
		} catch (ParameterNotFoundException ex) {
			throw new ActionException("Bạn cần chọn người sử dụng.");
		} catch (NumberFormatException ex) {
			throw new ActionException("ID không hợp lệ.");
		}
	}
	
	public User getDisplayedUser() {
		return user;
	}
	
	public List<History> getHistories() {
		return histories;
	}
	
	public List<Resource<Test>> getTests() {
		return tests;
	}

}
