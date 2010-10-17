package oop.controller.action.user;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.User;
import oop.db.dao.UserDAO;
import oop.util.SessionUtils;

public class LogInAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		title("Đăng nhập");

		if ("Log In".equals(getParams().get("logIn"))) {
			int error = 0;
			if (!getParams().hasParameter("userName")) {
				// empty user field
				request.setAttribute("userNameMissing", "true");
				error++;
			}
			if (!getParams().hasParameter("password")) {
				// empty password field
				request.setAttribute("passMissing", "true");
				error++;
			}

			if (error > 0) {
				request.setAttribute("error", "true");
			} else {
				User user = UserDAO.fetchByUsername(getParams().getString("userName"));
				if (user != null) {
					if (user.isBlocked()) {
						throw new ActionException("Tài khoản của bạn đã bị khoá.");
					}
					String password = getParams().getString("password");
					if (user.matchPassword(password)) {
						SessionUtils.setUser(getSession(), user);
						setNextAction("homepage");
					} else {
						// Sai mat khau
						request.setAttribute("wrongPass", "true");
					}
				} else {
					// Khong ton tai nguoi dung
					request.setAttribute("wrongUser", "true");
				}
			}
		}
	}

}
