package org.ocwiki.data;

import java.util.HashSet;
import java.util.Set;

public class TopicConstraint extends Constraint {

	private Set<Resource<Topic>> topics = new HashSet<Resource<Topic>>();

	public TopicConstraint() {
	}
	
	public TopicConstraint(int count) {
		super(count);
	}

	public Set<Resource<Topic>> getTopics() {
		return topics;
	}
	
	public void setTopics(Set<Resource<Topic>> topics) {
		this.topics = topics;
	}
	
}
