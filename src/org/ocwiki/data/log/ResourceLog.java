package org.ocwiki.data.log;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;
import org.ocwiki.data.User;

@XmlRootElement
public abstract class ResourceLog extends Log {

	private Resource<? extends Article> resource;

	ResourceLog() {
	}

	public ResourceLog(User user, Date timestamp, Resource<? extends Article> resource) {
		super();
		this.user = user;
		this.timestamp = timestamp;
		this.resource = resource;
	}

	@XmlElement
	public Resource<? extends Article> getResource() {
		return resource;
	}

}
