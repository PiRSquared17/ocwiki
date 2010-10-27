package oop.controller.action.user;

import static org.apache.commons.lang.StringUtils.isEmpty;
import oop.controller.action.AbstractAction;
import oop.data.User;
import oop.persistence.HibernateUtil;
import oop.util.UserUtils;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.exception.ConstraintViolationException;

public class ProfileEditAction extends AbstractAction {

	private User displayedUser;
	private boolean success;
	
	public boolean isSuccess() {
		return success;
	}

	public User getDisplayedUser() {
		return displayedUser;
	}

	@Override
	public void performImpl() throws Exception {
		displayedUser = getUser();
		title("Thay đổi thông tin cá nhân của " + displayedUser.getName());
		if ("Change".equals(getParams().get("change"))) {
			int errorEmail = updateEmail();
			int errorPass = updatePass();

			// if ((errorEmail == 0)&&(errorPass == 0));
			if (errorEmail == -1) {
				addError("Email", "Invalid");
			} else if (errorEmail == -2) {
				addError("Email", "Unavalaible");
			} else if (errorEmail == -3) {
				addError("Email", "Database error");
			}
			if ((errorPass == -1) || (errorPass == -3)) {
				addError("Password", "Wrong");
			} else if (errorPass == -2) {
				addError("Password", "Invalid");
			} else if (errorPass == -4) {
				addError("Password", "Database error");
			}
			if (((errorEmail >= 0) && (errorPass >= 0))
					&& ((errorEmail != 0) || (errorPass != 0)))
				success=true;
		}

	}

	private int updateEmail() throws Exception {
		String email = getParams().getString("userEmail");
		email = StringEscapeUtils.escapeSql(email);
		if (isEmpty(email))
			return 0;
		if (!UserUtils.isValidEmail(email)) {
			return -1;
		} else {
			try {
				getUser().setEmail(email);
				HibernateUtil.getSession().flush();
				return 1;
			} catch (ConstraintViolationException ex) {
				return -2;
			}
		}
	}

	private int updatePass() throws Exception {
		String pass = getParams().get("password");
		String changedPass = getParams().get("changedPassword");
		String confirmPass = getParams().get("confirmPass");
		if (isEmpty(pass) || isEmpty(changedPass) || isEmpty(confirmPass)) {
			return 0;}
		else if (!getUser().matchPassword(pass))
			return -1;
		else if (!pass.equals(confirmPass))
			return -2;
		else {
			getUser().setPassword(pass);
			return 1;
		}
	}

}
