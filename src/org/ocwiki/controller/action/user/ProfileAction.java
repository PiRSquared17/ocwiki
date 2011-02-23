package org.ocwiki.controller.action.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.CommentStatus;
import org.ocwiki.data.TestAttempt;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Test;
import org.ocwiki.data.User;
import org.ocwiki.db.dao.CommentCustomizationDAO;
import org.ocwiki.db.dao.CommentDAO;
import org.ocwiki.db.dao.HistoryDAO;
import org.ocwiki.db.dao.TestDAO;
import org.ocwiki.db.dao.UserDAO;

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
