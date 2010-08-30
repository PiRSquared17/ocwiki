package oop.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class Article implements Entity {

	@XmlElement
	private long id;
	private Text content;
	private String name;
	private Namespace namespace;

	Article() {
	}

	public Article(Namespace namespace, Text content) {
		super();
		this.namespace = namespace;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public Text getContent() {
		return content;
	}

	public void setContent(Text content) {
		this.content = content;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	protected <T> T copyTo(T obj) {
		Article article = (Article) obj;
		article.setName(getName());
		article.setNamespace(getNamespace());
		article.setContent(getContent());
		return obj;
	}

	public abstract Article copy();

	public void setNamespace(Namespace namespace) {
		this.namespace = namespace;
	}

	public Namespace getNamespace() {
		return namespace;
	}

}
