package oop.controller.action.user;

import java.util.SortedMap;

import oop.controller.action.AbstractAction;
import oop.data.FacebookAccount;
import oop.db.dao.FacebookAccountDAO;
import oop.util.FacebookUtils;
import oop.util.SessionUtils;

public class FacebookLoginAction extends AbstractAction {

	@Override
	protected void performImpl() throws Exception {
		SortedMap<String, String> facebook = FacebookUtils
				.readFacebookCookie(getRequest());
		if (facebook != null) {
			FacebookAccount account = FacebookAccountDAO.fetchByUid(facebook
					.get("uid"));
			if (account == null) {
				createAccount(facebook.get("uid"));
			} else {
				SessionUtils.login(getRequest().getSession(), account
						.getUser());
			}
		}
		setRedirect(getParams().get("returnUrl"));
	}

	private void createAccount(String uid) {
		// TODO Auto-generated method stub
	}

}
