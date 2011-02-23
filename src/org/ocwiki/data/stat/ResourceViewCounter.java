package org.ocwiki.data.stat;

import java.io.Serializable;

import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;

public class ResourceViewCounter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Resource<? extends Article> resource;
	private long viewCount;

	public ResourceViewCounter() {
	}
	
	public ResourceViewCounter(Resource<? extends Article> resource) {
		super();
		this.resource = resource;
		this.viewCount = 0;
	}


	public Resource<? extends Article> getResource() {
		return resource;
	}

	public void setResource(Resource<? extends Article> resource) {
		this.resource = resource;
	}

	public long getViewCount() {
		return viewCount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ResourceViewCounter) {
			ResourceViewCounter counter = (ResourceViewCounter) obj;
			return resource.equals(counter.resource);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return resource.hashCode();
	}
	
}
