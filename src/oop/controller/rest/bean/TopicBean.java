package oop.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TopicBean extends ArticleBean {

	private ResourceReferenceBean parent;

	public TopicBean() {
	}
	
	public ResourceReferenceBean getParent() {
		return parent;
	}

	public void setParent(ResourceReferenceBean parent) {
		this.parent = parent;
	} 
	
}
