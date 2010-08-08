package oop.change;

import java.io.Serializable;

public abstract class ChangeDelegate implements Serializable {

	private static final long serialVersionUID = -492162520811902828L;

	public ChangeDelegate() {
	}

	public abstract void perform(Change change) throws Exception;

	public abstract String getSummary() throws Exception;

	public String getDetail() {
		return null;
	}

	public boolean hasDetail() {
		return false;
	}

	public abstract ChangeDelegate createReverse();

}
