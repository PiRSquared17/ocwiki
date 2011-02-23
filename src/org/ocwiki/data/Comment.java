package org.ocwiki.data;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.ocwiki.util.Utils;

@XmlRootElement
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private User user;
	private Date timestamp;
	private String message;
	private Resource<? extends Article> resource;
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

	@XmlElement
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@XmlElement
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@XmlElement
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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

	public void setResource(Resource<? extends Article> resource) {
		this.resource = resource;
	}

	public void setRevision(Revision<? extends Article> revision) {
		this.revision = revision;
	}
}
