package oop.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Revision<T extends Article> implements ArticleContainer<T> {

	public String getQualifiedName() {
		return article.getQualifiedName();
	}

	@XmlElement
	private long id;
	@XmlTransient
	private Resource<T> resource;
	@XmlElement
	private T article;
	@XmlElement
	private User author;
	@XmlElement
	private Date timestamp;
	@XmlElement
	private String summary;
	@XmlElement
	private boolean minor;

	Revision() {
	}
	
	public Revision(long id, Resource<T> resource, T article, User author,
			Date timestamp, String summary, boolean minor) {
		super();
		this.id = id;
		this.resource = resource;
		this.article = article;
		this.author = author;
		this.timestamp = timestamp;
		this.summary = summary;
		this.minor = minor;
	}

	public long getId() {
		return id;
	}
	
	public T getArticle() {
		return article;
	}

	public User getAuthor() {
		return author;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getSummary() {
		return summary;
	}

	public boolean isMinor() {
		return minor;
	}

	public Resource<T> getResource() {
		return resource;
	}
	
}
