package oop.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Namespace implements Entity {

	// don't change these constants!!!
	public static final int MAIN = 0;
	public static final int OCWIKI = 1;
	public static final int TOPIC = 2;
	public static final int QUESTION = 3;
	public static final int TEST = 4;
	public static final int TEST_STRUCTURE = 5;
	public static final int FILE = 6;
	
	private long id;
	private String name;

	Namespace() {
	}
	
	public Namespace(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@XmlAttribute
	public long getId() {
		return id;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getId() + ": " + getName();
	}

}
