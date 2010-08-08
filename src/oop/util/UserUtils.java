package oop.util;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.regex.Pattern;



import org.apache.commons.lang.StringUtils;

public final class UserUtils {

	private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9-._]+@+[a-zA-Z0-9.-_]+.+[a-zA-z0-9]{2,4}");
	
	public static boolean isValidEmail(String email) {
		if (EMAIL_PATTERN.matcher(email).matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidUserName(String userName) {
		String userExp = "[a-zA-Z][a-zA-Z0-9]*";
		if ((userName.matches(userExp)) && (isEmpty(userName) == false))
			return true;
		else
			return false;
	}

	public static boolean isValidPassword(String password) {
		if (StringUtils.isEmpty(password)) {
			return false;
		}
		return password.length() < 6;
	}

}
