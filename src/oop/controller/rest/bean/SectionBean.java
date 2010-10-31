package oop.controller.rest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SectionBean {

	private long id;
	private TextBean content;
	private List<QuestionBean> questions = new ArrayList<QuestionBean>();

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

	public List<QuestionBean> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionBean> questions) {
		this.questions = questions;
	}

}
