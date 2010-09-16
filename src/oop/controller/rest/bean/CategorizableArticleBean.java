package oop.controller.rest.bean;

import java.util.HashSet;
import java.util.Set;

public class CategorizableArticleBean extends ArticleBean {

	private Set<ResourceReferenceBean> topics = new HashSet<ResourceReferenceBean>();

	public Set<ResourceReferenceBean> getTopics() {
		return topics;
	}

	public void setTopics(Set<ResourceReferenceBean> topics) {
		this.topics = topics;
	}

}
