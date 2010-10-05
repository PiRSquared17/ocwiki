package oop.util;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import oop.conf.Config;
import oop.data.FacebookAccount;
import oop.data.User;

public final class FacebookUtils {

	private FacebookUtils() {
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

	public static SortedMap<String,String> readFacebookCookie(HttpServletRequest request) {
		final String cookieName = "fbs_" + Config.get().getFacebookAppId();
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals(cookieName)) {
				SortedMap<String,String> map = getQueryMap(cookie.getValue());
				// check signature
				StringBuilder payload = new StringBuilder(); 
				for (Entry<String, String> entry : map.entrySet()) {
					if (!entry.getKey().equals("sig")) {
						payload.append(entry.getKey()).append("=").append(
								entry.getValue());
					}
				}
				payload.append(Config.get().getFacebookSecret());
				String signature = Utils.md5(payload.toString());
				if (signature.equalsIgnoreCase(map.get("sig"))) {
					return map; 
				}
				return null;
			}
		}
		return null;
	}

	public static FacebookAccount register(String uid) {
		User user = new User(); //XXX
		return new FacebookAccount(uid, user);
	}
	
}
