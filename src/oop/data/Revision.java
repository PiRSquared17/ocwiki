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

	@XmlElement
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@XmlElement
	public T getArticle() {
		return article;
	}

	public void setArticle(T article) {
		this.article = article;
	}
	
	@XmlElement
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}

	@XmlElement
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@XmlElement
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}

	@XmlElement
	public boolean isMinor() {
		return minor;
	}
	
	public void setMinor(boolean minor) {
		this.minor = minor;
	}

	@XmlTransient
	public Resource<T> getResource() {
		return resource;
	}
	
}
