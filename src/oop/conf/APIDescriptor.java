package oop.conf;

import java.util.HashSet;
import java.util.Set;

import oop.controller.api.API;
import oop.controller.api.AbstractAPI;

public class APIDescriptor {

	private String name;
	private Set<String> requiredGroups = new HashSet<String>();
	private boolean loginRequired;
	private Class<? extends AbstractAPI> clazz;

	public APIDescriptor(String name, Set<String> requiredGroups,
			boolean loginRequired, Class<? extends AbstractAPI> clazz) {
		super();
		this.name = name;
		this.requiredGroups = requiredGroups;
		this.loginRequired = loginRequired;
		this.clazz = clazz;
	}

	public boolean isLoginRequired() {
		return loginRequired;
	}

	public Set<String> getRequiredGroups() {
		return requiredGroups;
	}

	public String getName() {
		return name;
	}

	public Class<? extends AbstractAPI> getActionClass() {
		return clazz;
	}

	public API createAPI() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		AbstractAPI action = getActionClass().newInstance();
		return action;
	}
	
	
}
