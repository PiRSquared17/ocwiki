package org.ocwiki.controller.rest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestStructureBean extends BaseArticleBean {
	private String type;
	private int time;
	private List<SectionStructureBean> sectionStructures = new ArrayList<SectionStructureBean>();

	public TestStructureBean() {
	}

	@XmlElement(name="testType")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<SectionStructureBean> getSectionStructures() {
		return sectionStructures;
	}

	public void setSectionStructures(
			List<SectionStructureBean> sectionStructures) {
		this.sectionStructures = sectionStructures;
	}
}
