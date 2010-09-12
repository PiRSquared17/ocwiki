package oop.controller.action;

import java.util.Set;

import oop.conf.ActionDescriptor;
import oop.conf.Config;
import oop.data.User;
import oop.util.Utils;

public class ActionUtil {

	public static String getActionURL(String name) {
		return Config.get().getActionPath() + "/" + name;
	}

	public static String getActionURL(String name, String query) {
		return getActionURL(name) + "?" + query;
	}

	public static String getAPIURL(String name) {
		return Config.get().getApiPath() + "/" + name;
	}

	public static boolean checkPermission(User user, String actionName) {
		ActionDescriptor actionDescriptor = Config.get().getActionDescriptor(
				actionName);
		Set<String> groups = actionDescriptor.getRequiredGroups();
		boolean loginRequired = actionDescriptor.isLoginRequired()
				|| Utils.isNotEmpty(groups);
		return !(loginRequired && user == null) && (Utils.isEmpty(groups)
				|| groups.contains(user.getGroup()));
	}

}
