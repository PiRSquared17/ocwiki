package oop.data.log;

import java.util.Date;

import oop.data.Article;
import oop.data.Comment;
import oop.data.Resource;
import oop.data.User;

public class CommentLog extends ResourceLog {

	private Comment comment;

	public CommentLog() {
	}
	
	public CommentLog(User user, Date timestamp,
			Resource<? extends Article> resource, Comment comment) {
		super(user, timestamp, resource);
		this.comment = comment;
	}

	public Comment getComment() {
		return comment;
	}
	
}
