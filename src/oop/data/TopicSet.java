package oop.data;

import java.io.Serializable;

public class TopicSet implements Serializable {

	private static final long serialVersionUID = 1L;

	private Resource<Topic> resource;
	private int leftIndex;
	private int rightIndex;
	
	public Resource<Topic> getResource() {
		return resource;
	}
	
	public int getLeftIndex() {
		return leftIndex;
	}
	
	public int getRightIndex() {
		return rightIndex;
	}

}
