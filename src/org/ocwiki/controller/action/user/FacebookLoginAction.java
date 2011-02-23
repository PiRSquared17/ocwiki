package org.ocwiki.controller.action.user;

import java.io.IOException;
import java.util.SortedMap;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.FacebookAccount;
import org.ocwiki.db.dao.FacebookAccountDAO;
import org.ocwiki.util.FacebookUtils;
import org.ocwiki.util.SessionUtils;

public class FacebookLoginAction extends AbstractAction {

	@Override
	protected void performImpl() throws IOException, ServletException {
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
