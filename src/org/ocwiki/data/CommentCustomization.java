package org.ocwiki.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.ObjectUtils;

@XmlRootElement
public class CommentCustomization implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement
	private User user;
	private Comment comment;
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

	@XmlElement
	public CommentStatus getStatus() {
		return status;
	}

	public void setStatus(CommentStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	@XmlElement
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "customization: {comment: " + getComment().getId() + ", user: "
				+ getUser().getId() + ", status: " + getStatus().name() + "}";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CommentCustomization) {
			CommentCustomization cc = (CommentCustomization) obj;
			return ObjectUtils.equals(comment, cc.comment)
					&& ObjectUtils.equals(user, cc.user);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return ObjectUtils.hashCode(comment) ^ ObjectUtils.hashCode(user);
	}
	
}
