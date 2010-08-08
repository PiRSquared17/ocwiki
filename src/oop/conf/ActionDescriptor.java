package oop.conf;

import java.util.HashSet;
import java.util.Set;

import oop.controller.action.Action;

public class ActionDescriptor {

	private String name;
	private Set<String> requiredGroups = new HashSet<String>();
	private boolean loginRequired;
	private Class<? extends Action> actionClass;
	private String javaScript;
	private String css;

	public boolean isLoginRequired() {
		return loginRequired;
	}

	public Set<String> getRequiredGroup() {
		return requiredGroups;
	}

	public String getName() {
		return name;
	}

	public Class<? extends Action> getActionClass() {
		return actionClass;
	}

	public void setActionClass(Class<? extends Action> clazz) {
		this.actionClass = clazz;
	}
	
	public String getJavaScript() {
		if ("".equals(javaScript)) {
			return getName() + ".js";
		}
		return javaScript;
	}

	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}

	public String getCss() {
		if ("".equals(css)) {
			return getName() + ".css";
		}
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRequiredGroups(Set<String> requiredGroups) {
		this.requiredGroups = requiredGroups;
	}

	public void setLoginRequired(boolean loginRequired) {
		this.loginRequired = loginRequired;
	}

	public Action createAction() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		Action action = getActionClass().newInstance();
		action.setDescriptor(this);
		return action;
	}
	
}
