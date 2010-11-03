package oop.data;

public class OpenIDAccount {

	private String url;
	private User user;
	private String providerUrl;

	public OpenIDAccount() {
	}

	public OpenIDAccount(String url, User user) {
		super();
		this.url = url;
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

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

}
