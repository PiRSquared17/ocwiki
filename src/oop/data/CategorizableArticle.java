package oop.data;

import java.util.HashSet;
import java.util.Set;

public abstract class CategorizableArticle extends Article {

	private Set<Resource<Topic>> topics = new HashSet<Resource<Topic>>();

	public CategorizableArticle() {
		super();
	}

	public CategorizableArticle(Namespace namespace, Text content) {
		super(namespace, content);
	}

	public void setTopics(Set<Resource<Topic>> topics) {
		this.topics = topics;
	}

	public Set<Resource<Topic>> getTopics() {
		return topics;
	}

}