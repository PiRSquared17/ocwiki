package oop.data;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import oop.util.Utils;

@XmlRootElement
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private long id;
	@XmlElement
	private User user;
	@XmlElement
	private Date timestamp;
	private String message;
	@XmlTransient
	private Resource<? extends Article> resource;
	@XmlTransient
	private Revision<? extends Article> revision;

	public Comment() {
	}
	
	public Comment(User user, Date timestamp, String message,
			Resource<? extends Article> resource,
			Revision<? extends Article> revision) {
		super();
		this.user = user;
		this.timestamp = timestamp;
		this.message = message;
		this.resource = resource;
		this.revision = revision;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Resource<? extends Article> getResource() {
		return resource;
	}
	
	public Revision<? extends Article> getRevision() {
		return revision;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Comment) {
			Comment comment = (Comment) obj;
			return this.getId() == comment.getId();
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return Utils.hashCode(getId());
	}
}
