package oop.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import oop.data.Namespace;

@XmlRootElement
public class ResourceReferenceBean {
	private long id;
	private String name;
	private Namespace namespace;
	
	public ResourceReferenceBean() {
	}

	public ResourceReferenceBean(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Namespace getNamespace() {
		return namespace;
	}

	public void setNamespace(Namespace namespace) {
		this.namespace = namespace;
	}

}
