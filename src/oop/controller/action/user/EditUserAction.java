package oop.controller.action.user;

import static org.apache.commons.lang.StringUtils.isEmpty;
import oop.controller.action.AbstractAction;
import oop.persistence.HibernateUtil;
import oop.util.UserUtils;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.exception.ConstraintViolationException;

public class EditUserAction extends AbstractAction {

	@Override
	public void performImpl() throws Exception {
		title("Thay đổi thông tin cá nhân của " + getUser().getName());
		if ("Change".equals(getParams().get("change"))) {
			int errorEmail = updateEmail();
			int errorPass = updatePass();
			int error = 0;
			// if ((errorEmail == 0)&&(errorPass == 0));
			if (errorEmail == -1) {
				request.setAttribute("invalidEmail", "true");
				error++;
			} else if (errorEmail == -2) {
				request.setAttribute("emailUnavailable", "true");
				error++;
			} else if (errorEmail == -3) {
				request.setAttribute("dbError", "true");
				error++;
			}
			if ((errorPass == -1) || (errorPass == -3)) {
				request.setAttribute("wrongPass", "true");
				error++;
			} else if (errorPass == -2) {
				request.setAttribute("invalidPass", "true");
				error++;
			} else if (errorPass == -4) {
				request.setAttribute("dbError", "true");
				error++;
			}
			if (((errorEmail >= 0) && (errorPass >= 0))
					&& ((errorEmail != 0) || (errorPass != 0)))
				request.setAttribute("success", "true");
			if (error > 0)
				request.setAttribute("error", "true");
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
