package oop.controller.action.user;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;

import oop.conf.Config;
import oop.controller.action.AbstractAction;
import oop.data.FacebookAccount;
import oop.db.dao.FacebookAccountDAO;
import oop.util.SessionUtils;
import oop.util.Utils;

public class FacebookLoginAction extends AbstractAction {

	@Override
	protected void performImpl() throws Exception {
		final String cookieName = "fbs_" + Config.get().getFacebookAppId();
		for (Cookie cookie : getRequest().getCookies()) {
			if (cookie.getName().equals(cookieName)) {
				SortedMap<String, String> facebook = readFacebookCookie(cookie);
				if (facebook != null) {
					String uid = facebook.get("uid");
					FacebookAccount facebookAccount = FacebookAccountDAO
							.fetchByUid(uid);
					if (facebookAccount == null) {
						createAccount(uid);
					} else {
						SessionUtils.setUser(getRequest().getSession(),
								facebookAccount.getUser());
					}
				}
				break;
			}
		}
		setRedirect(Utils.urlDecode(getParams().get("returnUrl")));
	}

	private void createAccount(String uid) {
		// TODO Auto-generated method stub
	}

	private SortedMap<String,String> readFacebookCookie(Cookie cookie) {
		SortedMap<String,String> map = getQueryMap(cookie.getValue());
		StringBuilder payload = new StringBuilder(); 
		for (Entry<String, String> entry : map.entrySet()) {
			if (!entry.getKey().equals("sig")) {
				payload.append(entry.getKey()).append("=").append(
						entry.getValue());
			}
		}
		payload.append(Config.get().getFacebookSecret());
		if (Utils.md5(payload.toString()).equals(map.get("sig"))) {
			return null;
		}
		return map;
	}

	public static SortedMap<String, String> getQueryMap(String query) {
		String[] params = query.split("&");
		SortedMap<String, String> map = new TreeMap<String, String>();
		for (String param : params) {
			String[] pair = param.split("=");
			String name = Utils.urlDecode(pair[0]);
			String value = Utils.urlDecode(pair[1]);
			map.put(name, value);
		}
		return map;
	}

}
