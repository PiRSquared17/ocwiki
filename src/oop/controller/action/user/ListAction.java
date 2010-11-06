package oop.controller.action.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.data.User;
import oop.db.dao.UserDAO;
import oop.taglib.UtilFunctions;

public class ListAction extends AbstractAction {

	private static final int PAGE_LENGTH = 30;

	@Override
	public void performImpl() throws IOException, ServletException {
		int page = getParams().getInt("page", 1);

		List<User> users = UserDAO.fetch((page-1) * PAGE_LENGTH, PAGE_LENGTH);
		long count = UserDAO.count();

		request.setAttribute("users", users);
		request.setAttribute("page", page);
		request.setAttribute("pageCount", UtilFunctions.ceil(count / (double)PAGE_LENGTH));

		title("Danh sách thành viên - Trang " + page);
	}

}
