package org.ocwiki.controller.rest.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MultichoiceQuestionBean extends BaseArticleBean {
	private int level;
	private List<ChoiceBean> choices = new ArrayList<ChoiceBean>();

	public MultichoiceQuestionBean() {
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<ChoiceBean> getChoices() {
		return choices;
	}

	public void setChoices(List<ChoiceBean> choices) {
		this.choices = choices;
	}

}
