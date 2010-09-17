package oop.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TopicBean extends ArticleBean {

	private ResourceBean parent;

	public TopicBean() {
	}
	
	public ResourceBean getParent() {
		return parent;
	}

	public void setParent(ResourceBean parent) {
		this.parent = parent;
	} 
	
}
