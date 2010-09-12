package oop.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class CommentReport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Comment comment;
	private User user;
	private CommentStatus status;
	private int likeCount;

	public CommentReport() {
	}
	
	public CommentReport(Comment comment, CommentStatus status,
			int likeCount) {
		super();
		this.comment = comment;
		this.status = status;
		this.likeCount = likeCount;
	}

	@XmlElement
	public Comment getComment() {
		return comment;
	}

	@XmlElement
	public CommentStatus getStatus() {
		return status;
	}

	@XmlElement
	public int getLikeCount() {
		return likeCount;
	}

	@XmlTransient
	public User getUser() {
		return user;
	}

}
