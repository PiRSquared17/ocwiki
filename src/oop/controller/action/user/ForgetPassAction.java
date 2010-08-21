package oop.controller.action.user;

import oop.controller.action.AbstractAction;
import oop.data.User;
import oop.db.dao.UserDAO;
import oop.util.UserUtils;

public class ForgetPassAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
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
