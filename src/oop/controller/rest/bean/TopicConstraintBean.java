package oop.controller.rest.bean;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TopicConstraintBean extends ConstraintBean {

	private Set<ResourceReferenceBean> topics = new HashSet<ResourceReferenceBean>();

	public TopicConstraintBean() {
	}

	public void setTopics(Set<ResourceReferenceBean> topics) {
		this.topics = topics;
	}

	public Set<ResourceReferenceBean> getTopics() {
		return topics;
	}

}
