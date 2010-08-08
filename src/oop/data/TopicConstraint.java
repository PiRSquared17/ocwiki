package oop.data;

import java.util.HashSet;
import java.util.Set;

public class TopicConstraint extends Constraint {

	private Set<Topic> topics = new HashSet<Topic>();

	TopicConstraint() {
	}
	
	public TopicConstraint(SectionStructure sectionStructure,
			int count) {
		super(sectionStructure, count);
	}

	public Set<Topic> getTopics() {
		return topics;
	}
	
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	
}
