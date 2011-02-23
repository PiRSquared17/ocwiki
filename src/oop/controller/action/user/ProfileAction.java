package oop.controller.action.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.CommentStatus;
import oop.data.TestAttempt;
import oop.data.Resource;
import oop.data.Test;
import oop.data.User;
import oop.db.dao.CommentCustomizationDAO;
import oop.db.dao.CommentDAO;
import oop.db.dao.HistoryDAO;
import oop.db.dao.TestDAO;
import oop.db.dao.UserDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class ProfileAction extends AbstractAction {

	private User user;
	private List<TestAttempt> histories;
	private List<Resource<Test>> tests;
	private long likedComments;
	private long hiddenComments;
	private long postedComments;


	@Override
	public void performImpl() throws IOException, ServletException {
		try {
			long userId = getParams().getLong("user");
			user = UserDAO.fetchById(userId);
		} catch (ParameterNotFoundException ex) {
			throw new ActionException("Bạn cần chọn người sử dụng.");
		} catch (NumberFormatException ex) {
			user = UserDAO.fetchByUsername(getParams().get("user"));
			if (user == null) {
				throw new ActionException("Không tìm thấy người dùng");
			}
		}

		title("Hồ sơ người dùng " + user.getName());

		histories = HistoryDAO.fetchByUser(user.getId(), 0, 5);
		tests = TestDAO.fetchByAuthor(user.getId(), 0, 5);
		likedComments = CommentCustomizationDAO.countByCommentAuthorAndStatus(
				user.getId(), CommentStatus.LIKE);
		hiddenComments = CommentCustomizationDAO.countByCommentAuthorAndStatus(
				user.getId(), CommentStatus.HIDDEN);
		postedComments = CommentDAO.countByAuthor(user.getId());
	}
	
	public User getDisplayedUser() {
		return user;
	}
	
	public List<TestAttempt> getHistories() {
		return histories;
	}
	
	public List<Resource<Test>> getTests() {
		return tests;
	}
	
	public long getLikedComments() {
		return likedComments;
	}

	public long getHiddenComments() {
		return hiddenComments;
	}


	public long getPostedComments() {
		return postedComments;
	}


}
