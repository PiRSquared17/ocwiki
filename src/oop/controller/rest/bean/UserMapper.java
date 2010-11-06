package oop.controller.rest.bean;

public class UserMapper {

	private static UserMapper DEFAULT_INSTANCE = new UserMapper();

	public static UserMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
