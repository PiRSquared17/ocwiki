package org.ocwiki.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.User;
import org.ocwiki.db.dao.UserDAO;
import org.ocwiki.util.BlockedUserException;
import org.ocwiki.util.SessionUtils;
import org.ocwiki.util.UserUtils;


public class SignUpAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		title("Đăng kí thành viên");
		if (isUserLoggedIn()){
			setNextAction("homepage");		
			return;
		}
		if ("Sign Up".equals(getParams().get("signUp"))) {
			int error = 0;
			String userName = getParams().get("userName").trim();
			String email = getParams().get("userEmail").trim();
			String password = getParams().get("password");
			String confirm = getParams().get("confirmPass");
			String fullname = getParams().getString("fullName").trim();

			if (UserDAO.fetchByUsername(userName) != null) {
				// nguoi dung da ton tai
				request.setAttribute("userExist", "true");
				error++;
			}
			if (UserDAO.fetchByEmail(email) != null) {
				// email da duoc dung
				request.setAttribute("emailUnavailable", "true");
				error++;
			}
			if (!UserUtils.isValidUserName(userName)) {
				// Ten nguoi dung nhap sai qui cach
				request.setAttribute("invalidUserName", "true");
				error++;
			}
			if (!UserUtils.isValidEmail(email)) {
				// Email sai qui cach
				request.setAttribute("invalidEmail", "true");
				error++;
			}
			if (!password.equals(confirm) || !UserUtils.isValidPassword(password)) {
				// Password sai qui cach
				request.setAttribute("invalidPass", "true");
				error++;
			}

			if (error > 0) {
				request.setAttribute("error", "true");
			} else {
				User user = UserDAO.create(userName, fullname, "", "user",
						password, email);
				SessionUtils.login(getSession(), user);
				setNextAction("homepage");
			}
		}
	}

}
