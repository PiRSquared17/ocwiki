package oop.data.stat;

import java.io.Serializable;

import oop.data.Article;
import oop.data.Resource;

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

}
