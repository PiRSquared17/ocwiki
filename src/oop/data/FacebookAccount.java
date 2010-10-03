package oop.data;

public class FacebookAccount {

	private String uid;
	private User user;

	public FacebookAccount() {
	}

	public FacebookAccount(String uid, User user) {
		super();
		this.uid = uid;
		this.user = user;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
