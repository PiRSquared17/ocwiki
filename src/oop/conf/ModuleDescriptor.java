package oop.conf;

import java.util.HashSet;
import java.util.Set;

import oop.module.Module;

import org.apache.commons.lang.StringUtils;

public class ModuleDescriptor {

	private String name;
	private String title;
	private String position;
	private boolean loginRequired = false;
	private Set<String> requiredGroups = new HashSet<String>();
	private int order;
	private String page;
	private Class<? extends Module> clazz;

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}

	public String getTitle() {
		return title;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isLoginRequired() {
		return loginRequired;
	}

	public void setLoginRequired(boolean loginRequired) {
		this.loginRequired = loginRequired;
	}

	public Set<String> getRequiredGroups() {
		return requiredGroups;
	}

	protected void setRequiredGroup(Set<String> requiredGroup) {
		this.requiredGroups = requiredGroup;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getPage() {
		return StringUtils.defaultIfEmpty(page, name + ".jsp");
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<? extends Module> getModuleClass() {
		return clazz;
	}
	
	public Module createModule() throws InstantiationException, IllegalAccessException {
		Module module = clazz.newInstance();
		module.setTitle(title);
		module.setPage(StringUtils.defaultIfEmpty(page, name + ".jsp"));
		module.setOrder(order);
		return module;
	}
	
}
