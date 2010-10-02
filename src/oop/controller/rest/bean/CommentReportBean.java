package oop.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import oop.data.CommentStatus;

@XmlRootElement
public class CommentReportBean {

	private CommentBean comment;
	private CommentStatus status;
	private int likeCount;

	public CommentReportBean() {
	}

	public CommentBean getComment() {
		return comment;
	}

	public void setComment(CommentBean comment) {
		this.comment = comment;
	}

	public CommentStatus getStatus() {
		return status;
	}

	public void setStatus(CommentStatus status) {
		this.status = status;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

}
