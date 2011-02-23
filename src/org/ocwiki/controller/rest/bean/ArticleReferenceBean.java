package org.ocwiki.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.ocwiki.data.Namespace;

@XmlRootElement
public class ArticleReferenceBean {

	private long id;
	private String name;
	private Namespace namespace;

	public ArticleReferenceBean() {
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
