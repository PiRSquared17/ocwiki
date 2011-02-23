package org.ocwiki.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResourceSearchReport<T extends Article> {

	private Resource<T> resource;
	private int score;
	
	public ResourceSearchReport() {
	}

	public ResourceSearchReport(Resource<T> resource, long score) {
		super();
		this.resource = resource;
		this.score = (int) score;
	}

	public Resource<T> getResource() {
		return resource;
	}
	
	public void setResource(Resource<T> resource) {
		this.resource = resource;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

}
