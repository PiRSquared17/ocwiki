package org.ocwiki.data;

public class Link {

	private long id;
	private String url;
	private String password;
	private boolean broken;

	public Link() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBroken() {
		return broken;
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Link) {
			return url.equals(((Link) obj).url);
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return url.hashCode();
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
}
