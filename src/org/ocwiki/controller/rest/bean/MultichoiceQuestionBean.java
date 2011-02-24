package org.ocwiki.controller.rest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MultichoiceQuestionBean extends BaseArticleBean {
	private int level;
	private List<AnswerBean> answers = new ArrayList<AnswerBean>();

	public MultichoiceQuestionBean() {
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<AnswerBean> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerBean> answers) {
		this.answers = answers;
	}

}
