package org.ocwiki.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TopicReportBean {

	private ResourceReferenceBean resource;
	private int childrenCount;
	private int articleCount;
	
	public TopicReportBean() {
	}

	public ResourceReferenceBean getResource() {
		return resource;
	}

	public void setResource(ResourceReferenceBean resource) {
		this.resource = resource;
	}

	public int getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(int childrenCount) {
		this.childrenCount = childrenCount;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	
}
