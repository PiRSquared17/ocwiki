package oop.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {

	private long id;
	private User user;
	private Date timestamp;
	private String message;
	private Resource<? extends Article> resource;
	private Revision<? extends Article> revision;
	private CommentStatus status;

	public Comment() {
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlAttribute
	public long getId() {
		return id;
	}

	@XmlElement
	public User getUser() {
		return user;
	}

	@XmlAttribute
	public Date getTimestamp() {
		return timestamp;
	}

	public ArticleContainer<? extends Article> getArticleContainer() {
		return revision == null ? resource : revision;
	}
	
	public void setStatus(CommentStatus status) {
		this.status = status;
	}

	@XmlAttribute
	public CommentStatus getStatus() {
		return status;
	}

}
