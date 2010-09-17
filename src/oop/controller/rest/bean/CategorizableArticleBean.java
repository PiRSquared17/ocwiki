package oop.controller.rest.bean;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CategorizableArticleBean extends ArticleBean {

	private Set<ResourceBean> topics = new HashSet<ResourceBean>();

	public CategorizableArticleBean() {
	}
	
	public Set<ResourceBean> getTopics() {
		return topics;
	}

	public void setTopics(Set<ResourceBean> topics) {
		this.topics = topics;
	}

}
