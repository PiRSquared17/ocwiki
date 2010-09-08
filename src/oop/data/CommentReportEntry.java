package oop.data;

public class CommentReportEntry {

	private Comment comment;
	private CommentStatus status;
	private int likeCount;

	public CommentReportEntry(Comment comment, CommentStatus status,
			int likeCount) {
		super();
		this.comment = comment;
		this.status = status;
		this.likeCount = likeCount;
	}

	public Comment getComment() {
		return comment;
	}

	public CommentStatus getStatus() {
		return status;
	}

	public int getLikeCount() {
		return likeCount;
	}

}
