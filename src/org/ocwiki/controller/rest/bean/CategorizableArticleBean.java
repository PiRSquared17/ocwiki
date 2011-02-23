package org.ocwiki.controller.rest.bean;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CategorizableArticleBean extends ArticleBean {

	private Set<ResourceReferenceBean> topics = new HashSet<ResourceReferenceBean>();

	public CategorizableArticleBean() {
	}
	
	public Set<ResourceReferenceBean> getTopics() {
		return topics;
	}

	public void setTopics(Set<ResourceReferenceBean> topics) {
		this.topics = topics;
	}

}
