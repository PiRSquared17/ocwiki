package oop.data;


public class ResourceSearchReport<T extends Article> {

	private Resource<T> resource;
	private int score;

	public ResourceSearchReport(Resource<T> resource, long score) {
		super();
		this.resource = resource;
		this.score = (int) score;
	}

	public Resource<T> getResource() {
		return resource;
	}

	public int getScore() {
		return score;
	}

}
