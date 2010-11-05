package oop.data;

import java.io.Serializable;

import oop.util.Utils;

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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TopicSet) {
			return getResource().getId() == ((TopicSet) obj).getResource().getId();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Utils.hashCode(getResource().getId());
	}
	
}
