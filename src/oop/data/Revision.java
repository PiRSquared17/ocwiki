package oop.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Revision<T extends Article> implements ArticleContainer<T> {

	private long id;
	private Resource<T> resource;
	private T article;
	private User author;
	private Date timestamp;
	private String summary;
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

	@XmlAttribute
	public long getId() {
		return id;
	}
	
	@XmlElement
	public T getArticle() {
		return article;
	}

	@XmlElement
	public User getAuthor() {
		return author;
	}

	@XmlAttribute
	public Date getTimestamp() {
		return timestamp;
	}

	@XmlElement
	public String getSummary() {
		return summary;
	}

	@XmlAttribute
	public boolean isMinor() {
		return minor;
	}

	@XmlTransient
	public Resource<T> getResource() {
		return resource;
	}
	
}
