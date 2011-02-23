package org.ocwiki.data.log;

import java.util.Date;

import org.ocwiki.data.Article;
import org.ocwiki.data.Comment;
import org.ocwiki.data.Resource;
import org.ocwiki.data.User;

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
