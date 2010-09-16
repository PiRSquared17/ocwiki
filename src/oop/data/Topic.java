package oop.data;

import javax.xml.bind.annotation.XmlTransient;

import oop.db.dao.NamespaceDAO;
import oop.util.Utils;

public class Topic extends Article {

	protected String name;
	private Resource<Topic> parent;

	Topic() {
		// default constructor
	}

	public Topic(String name, Resource<Topic> parent, Text content) {
		super(NamespaceDAO.fetch(Namespace.TOPIC), content);
		this.name = name;
		this.parent = parent;
	}
	
	@XmlTransient
	public Resource<Topic> getParent() {
		return parent;
	}

	public void setParent(Resource<Topic> parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return Utils.hashCode(getId());
	}

	protected <T> T copyTo(T obj) {
		Topic topic = (Topic) obj;
		topic.setName(getName());
		topic.setParent(getParent());
		return super.copyTo(obj);
	};
	
	@Override
	public Topic copy() {
		return copyTo(new Topic());
	}
	
}