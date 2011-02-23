package org.ocwiki.data;

public abstract class Constraint implements Entity {

	private long id;
	private int count;

	Constraint() {
	}

	public Constraint(int count) {
		super();
		this.count = count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public long getId() {
		return id;
	}

}
