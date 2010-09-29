package oop.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import oop.data.Namespace;
import oop.data.Text;

@XmlRootElement
public class ArticleBean {

	private long id;
	private Text content;
	
	public ArticleBean() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Text getContent() {
		return content;
	}

	public void setContent(Text content) {
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
