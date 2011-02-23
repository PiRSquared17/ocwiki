package org.ocwiki.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AnswerBean {
	private long id;
	private TextBean content;
	private boolean correct;

	public AnswerBean() {
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

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

}
