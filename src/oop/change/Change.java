package oop.change;

import java.util.Date;

import oop.data.Article;
import oop.data.User;

public class Change {

	private long id;
	private User user;
	private Date datetime;
	private String comment;
	private Article article;
	private boolean minor;
	private ChangeDelegate delegate;

	Change() {
	}

	public Change(User user, Date datetime, String comment, Article article,
			boolean minor, ChangeDelegate delegate) {
		super();
		this.user = user;
		this.datetime = datetime;
		this.comment = comment;
		this.article = article;
		this.minor = minor;
		this.delegate = delegate;
	}

	public void perform() throws Exception {
		delegate.perform(this);
	}

	public Change createReverse(String comment, boolean minor) throws Exception {
		return new Change(user, new Date(), comment, article, 
				minor, delegate.createReverse());
	}

	public String getSummary() throws Exception {
		return delegate.getSummary();
	}

	public User getUser() {
		return user;
	}

	public Date getDatetime() {
		return datetime;
	}

	public String getComment() {
		return comment;
	}

	public Article getArticle() {
		return article;
	}

	public long getId() {
		return id;
	}

	public boolean isMinor() {
		return minor;
	}
	
	public boolean hasDetail() {
		return delegate.hasDetail();
	}

	public String getDetail() {
		return delegate.getDetail();
	}
	
}
