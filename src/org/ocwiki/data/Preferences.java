package org.ocwiki.data;

import java.io.Serializable;

public class Preferences implements Serializable {

	private static final long serialVersionUID = 8549895382026010129L;
	private String template = "default";

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTemplate() {
		return template;
	}

}
