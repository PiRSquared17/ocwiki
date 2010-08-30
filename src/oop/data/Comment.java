package oop.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Comment implements HasVersion {

	@XmlElement
	private long id;
	@XmlElement
	private User user;
	@XmlElement
	private int version;
	@XmlElement
	private Date timestamp;
	@XmlElement
	private String message;
	@XmlTransient
	private Resource<? extends Article> resource;
	@XmlTransient
	private Revision<? extends Article> revision;
	@XmlElement
	private CommentStatus status;

	public Comment() {
	}
	
	public Comment(User user, Date timestamp, String message,
			Resource<? extends Article> resource,
			Revision<? extends Article> revision, CommentStatus status) {
		super();
		this.user = user;
		this.timestamp = timestamp;
		this.message = message;
		this.resource = resource;
		this.revision = revision;
		this.status = status;
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

	@XmlTransient
	public ArticleContainer<? extends Article> getArticleContainer() {
		return revision == null ? resource : revision;
	}
	
	public void setStatus(CommentStatus status) {
		this.status = status;
	}

	public CommentStatus getStatus() {
		return status;
	}

	@Override
	public int getVersion() {
		return version;
	}
}
