package oop.data;

import java.util.Set;

public class SectionStructure implements Entity {

	private long id;
	private TestStructure testStructure;
	private int order;
	private Text content;
	private Set<Constraint> constraints = null;
	private boolean deleted;
	private int version;
	
	SectionStructure() {
	}

	public SectionStructure(TestStructure testStructure, int order, Text content) {
		super();
		this.testStructure = testStructure;
		this.order = order;
		this.content = content;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Text getContent() {
		return content;
	}

	public void setContent(Text content) {
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public Set<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(Set<Constraint> constraints) {
		this.constraints = constraints;
	}

	public TestStructure getTestStructure() {
		return testStructure;
	}

	public void setTestStructure(TestStructure testStructure) {
		this.testStructure = testStructure;
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getVersion() {
		return version;
	}

}
