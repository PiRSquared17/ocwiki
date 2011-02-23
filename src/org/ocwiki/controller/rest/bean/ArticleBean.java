package org.ocwiki.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.ocwiki.data.Namespace;

@XmlRootElement
public class ArticleBean {

	private long id;
	private TextBean content;
	
	public ArticleBean() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TextBean getContent() {
		return content;
	}

	public void setContent(TextBean content) {
		this.content = content;
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

	private String name;
	private Namespace namespace;

}
