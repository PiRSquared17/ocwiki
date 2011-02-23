package org.ocwiki.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LevelConstraintBean extends ConstraintBean {

	private int level;

	public LevelConstraintBean() {
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
}
