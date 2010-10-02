package oop.controller.rest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import oop.data.Answer;

@XmlRootElement
public class BaseQuestionBean extends BaseArticleBean {
	private int level;
	private List<Answer> answers = new ArrayList<Answer>();

	public BaseQuestionBean() {
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}
