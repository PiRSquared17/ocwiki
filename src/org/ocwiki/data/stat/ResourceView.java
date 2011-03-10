package org.ocwiki.data.stat;

import java.util.Date;

import org.ocwiki.data.Resource;
import org.ocwiki.data.User;

public class ResourceView {

	private Resource<?> resource;
	private User user;
	private Date timestamp;

	public ResourceView() {
	}
	
	public ResourceView(Resource<?> resource, User user) {
		super();
		this.resource = resource;
		this.user = user;
		this.timestamp = new Date();
	}

	public Resource<?> getResource() {
		return resource;
	}

	public User getUser() {
		return user;
	}

	public Date getTimestamp() {
		return timestamp;
	}

}
