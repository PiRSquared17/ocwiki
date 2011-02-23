package org.ocwiki.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TopicReport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Resource<Topic> resource;
	private int childrenCount;
	private int articleCount;

	public TopicReport() {
	}
	
	public TopicReport(Resource<Topic> resource, int childrenCount,
			int articleCount) {
		super();
		this.resource = resource;
		this.childrenCount = childrenCount;
		this.articleCount = articleCount;
	}

	public Resource<Topic> getResource() {
		return resource;
	}

	public void setResource(Resource<Topic> resource) {
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
