package oop.db.dao;

import oop.data.Article;
import oop.data.Resource;

public class ResourceSearchReport<T extends Article> {

	private Resource<T> resource;
	private int score;

	public ResourceSearchReport(Resource<T> resource, int score) {
		super();
		this.resource = resource;
		this.score = score;
	}

	public Resource<T> getResource() {
		return resource;
	}

	public int getScore() {
		return score;
	}

}
