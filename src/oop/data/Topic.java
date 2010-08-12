package oop.data;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import oop.util.Utils;

public class Topic extends Article {

	protected String name;
	private Topic parent;
	private Set<Topic> children = new HashSet<Topic>();

	Topic() {
		// default constructor
	}

	public Topic(String name, Topic parent, User author) {
		super(author, null);
		this.name = name;
		this.parent = parent;
	}

	public Topic getParent() {
		return parent;
	}

	public void setParent(Topic parent) {
		this.parent = parent;
	}

	@XmlTransient
	public Set<Topic> getChildren() {
		return children;
	}

	public void setChildren(Set<Topic> children) {
		this.children = children;
	}
	
	public List<Topic> getAncestors() {
		List<Topic> ancestors = new LinkedList<Topic>();
		addAncestorRecursively(this, ancestors);
		return ancestors;
	}
	
	private void addAncestorRecursively(Topic child, List<Topic> ancestors) {
		if (child.getParent() == null) {
			return;
		}
		ancestors.add(child.getParent());
		addAncestorRecursively(child.getParent(), ancestors);
	}
	
	public Set<Topic> getDescendants() {
		Set<Topic> descendants = new HashSet<Topic>();
		for (Topic topic : getChildren()) {
			addDescendantRecursively(topic, descendants);
		}
		return descendants;
	}

	private static void addDescendantRecursively(Topic topic,
			Set<Topic> descendants) {
		descendants.add(topic);
		for (Topic child : topic.getChildren()) {
			addDescendantRecursively(child, descendants);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName() + " (parent: " + (getParent() == null ? "none"
				: getParent().getName()) + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Topic) {
			return getId() == ((Topic) obj).getId();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Utils.hashCode(id);
	}
	
	@Override
	public Namespace getNamespace() {
		return Namespace.TOPIC;
	}

}