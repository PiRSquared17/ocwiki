package oop.diff;

public class SimpleDiff implements Diff {

	private String property;

	public SimpleDiff(String property) {
		super();
		this.property = property;
	}
	
	public Difference diferentiate(Object obj1, Object obj2) {
		return null; //XXX
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
}
