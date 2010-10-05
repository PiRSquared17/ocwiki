package oop.controller.rest.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommentBean {

	private long id;
	private UserReferenceBean user;
	private Date timestamp;
	private String message;
	private ResourceReferenceBean resource;
	private RevisionBean<? extends ArticleBean> revision;

	public CommentBean() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserReferenceBean getUser() {
		return user;
	}

	public void setUser(UserReferenceBean user) {
		this.user = user;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResourceReferenceBean getResource() {
		return resource;
	}

	public void setResource(ResourceReferenceBean resource) {
		this.resource = resource;
	}

	public RevisionBean<? extends ArticleBean> getRevision() {
		return revision;
	}

	public void setRevision(RevisionBean<? extends ArticleBean> revision) {
		this.revision = revision;
	}

}
