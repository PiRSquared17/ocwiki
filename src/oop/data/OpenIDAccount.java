package oop.data;

public class OpenIDAccount {

	private String url;
	private String providerUrl;
	private User user;

	public OpenIDAccount() {
	}

	public OpenIDAccount(String url, String providerUrl, User user) {
		super();
		this.url = url;
		this.providerUrl = providerUrl;
		this.user = user;
	}
	
	public OpenIDAccount(OpenIDAccount anotherOIDA) {
		super();
		this.url = anotherOIDA.getUrl();
		this.providerUrl = anotherOIDA.getProviderUrl();
		this.user = anotherOIDA.getUser();
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
