package org.ocwiki.util;

import javax.servlet.http.HttpServletRequest;

import org.ocwiki.controller.OcwikiApp;
import org.ocwiki.data.User;

public class TemplateUtils {

	public static String getTemplate(HttpServletRequest request) {
		User user = SessionUtils.getUser(request.getSession());
		if (user == null) {
			return OcwikiApp.get().getConfig().getDefaultTemplate();
		}
		return user.getPreferences().getTemplate();
	}

}
