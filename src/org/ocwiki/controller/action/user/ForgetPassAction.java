package org.ocwiki.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.User;
import org.ocwiki.db.dao.UserDAO;
import org.ocwiki.util.UserUtils;

public class ForgetPassAction extends AbstractAction {

	@Override
	public void performImpl() throws IOException, ServletException {
		title("Gửi mật khẩu");
		if (getParams().hasParameter("sendPassword")) {
			if (UserUtils.isValidEmail(getParams().get("userEmail")) == false) {
				addError("email", "Email sai quy cách.");
			} else {
				User user = UserDAO.fetchByEmail(getParams().getString(
						"userEmail"));
				if (user == null) {
					addError("email", "Địa chỉ email không tồn tại.");
				}
				// XXX viết code gửi email
			}
		}
	}
}
