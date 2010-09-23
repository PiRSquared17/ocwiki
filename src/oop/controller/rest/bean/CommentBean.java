package oop.controller.rest.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommentBean {

	private long id;
	private UserBean user;
	private Date timestamp;
	private String message;
	private ResourceReferenceBean resource;
	private RevisionBean revision;

	public CommentBean() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
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

	public RevisionBean getRevision() {
		return revision;
	}

	public void setRevision(RevisionBean revision) {
		this.revision = revision;
	}

}
