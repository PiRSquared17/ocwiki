package oop.data;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseArticle extends Article {

	private Set<Topic> topics = new HashSet<Topic>();

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

}
