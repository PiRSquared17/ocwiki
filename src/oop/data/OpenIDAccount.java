package oop.data;

public class OpenIDAccount {

	private String url;
	private User user;

	public OpenIDAccount() {
	}

	public OpenIDAccount(String uid, User user) {
		super();
		this.url = uid;
		this.user = user;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
