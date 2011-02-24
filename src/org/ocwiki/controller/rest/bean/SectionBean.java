package org.ocwiki.controller.rest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SectionBean {

	private long id;
	private TextBean content;
	private List<TestQuestionBean> questions = new ArrayList<TestQuestionBean>();

	public SectionBean() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TextBean getContent() {
		return content;
	}

	public void setContent(TextBean content) {
		this.content = content;
	}

	public List<TestQuestionBean> getQuestions() {
		return questions;
	}

	public void setQuestions(List<TestQuestionBean> questions) {
		this.questions = questions;
	}

}
