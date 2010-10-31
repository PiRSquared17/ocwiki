package oop.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SolutionBean extends TextArticleBean {

	private ResourceReferenceBean question;

	public SolutionBean() {
	}
	
	public void setQuestion(ResourceReferenceBean question) {
		this.question = question;
	}

	public ResourceReferenceBean getQuestion() {
		return question;
	}
	
}
