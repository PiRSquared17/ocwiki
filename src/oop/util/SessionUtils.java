package oop.util;

import javax.servlet.http.HttpSession;

import oop.data.User;

import org.apache.commons.lang.math.RandomUtils;
import org.hibernate.Hibernate;

public final class SessionUtils {

	public static void login(HttpSession session, User user)
			throws BlockedUserException {
		if (user.isBlocked()) {
			throw new BlockedUserException();
		}
		// remove proxies
		Hibernate.initialize(user);
		session.setAttribute("user", user);
		session.setAttribute("login", true);
		if (user != null) {
			session.setAttribute("editToken", generateEditToken());
		}
	}
	
	public static void logout(HttpSession session) {
		session.removeAttribute("user");
		session.setAttribute("login", false);
		session.removeAttribute("editToken");
	}
	
	public static void updateUser(HttpSession session, User user) {
		if (user.getId() != getUser(session).getId()) {
			throw new IllegalArgumentException("Not the same user");
		}
		Hibernate.initialize(user);
		session.setAttribute("user", user);
	}

	public static User getUser(HttpSession session) {
		return (User) session.getAttribute("user");
	}

	private static String generateEditToken() {
		return Integer.toHexString(RandomUtils.nextInt());
	}

	public static String getEditToken(HttpSession session) {
		return (String) session.getAttribute("editToken");
	}

	public static boolean isLoggedIn(HttpSession session) {
		return getUser(session) != null;
	}

}
