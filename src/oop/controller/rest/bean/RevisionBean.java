package oop.controller.rest.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RevisionBean<T extends ArticleBean> {

	private long id;
	private ResourceReferenceBean resource;
	private T article;
	private UserBean author;
	private Date timestamp;
	private String summary;
	private boolean minor;

	public RevisionBean() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public T getArticle() {
		return article;
	}

	public void setArticle(T article) {
		this.article = article;
	}

	public UserBean getAuthor() {
		return author;
	}

	public void setAuthor(UserBean author) {
		this.author = author;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public boolean isMinor() {
		return minor;
	}

	public void setMinor(boolean minor) {
		this.minor = minor;
	}

	public ResourceReferenceBean getResource() {
		return resource;
	}

	public void setResource(ResourceReferenceBean resource) {
		this.resource = resource;
	}
	
}
