package oop.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class CommentCustomization implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private User user;
	@XmlTransient
	private Comment comment;
	@XmlElement
	private CommentStatus status;

	public CommentCustomization() {
	}
	
	public CommentCustomization(Comment comment, User user) {
		this(comment, user, CommentStatus.NORMAL);
	}
	
	public CommentCustomization(Comment comment, User user, CommentStatus status) {
		super();
		this.user = user;
		this.comment = comment;
		this.status = status;
	}

	public CommentStatus getStatus() {
		return status;
	}

	public void setStatus(CommentStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public Comment getComment() {
		return comment;
	}

}
