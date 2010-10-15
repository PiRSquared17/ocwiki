package oop.conf;

import java.util.HashSet;
import java.util.Set;

import oop.controller.action.Action;

import org.apache.commons.lang.StringUtils;

public class ActionDescriptor {

	private String name;
	private Set<String> requiredGroups = new HashSet<String>();
	private boolean loginRequired;
	private Class<? extends Action> actionClass;
	private String javaScript;
	private String css;
	private String title;
	private String container;

	public boolean isLoginRequired() {
		return loginRequired;
	}

	public Set<String> getRequiredGroups() {
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
		Action action;
		if (actionClass == null) {
			action = Action.NULL_ACTION;
		} else {
			action = actionClass.newInstance();
		}
		action.setTitle(title);
		action.setDescriptor(this);
		return action;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public String getContainer() {
		if (StringUtils.isEmpty(container)) {
			return "index.jsp";
		}
		return container;
	}
	
}
