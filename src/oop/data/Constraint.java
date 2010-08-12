package oop.data;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class Constraint implements Entity {

	private long id;
	private SectionStructure sectionStructure;
	private int count;
	private Status status = Status.NORMAL;
	private int version;

	Constraint() {
	}
	
	public Constraint(SectionStructure sectionStructure, int count) {
		super();
		this.sectionStructure = sectionStructure;
		this.count = count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setSectionStructure(SectionStructure sectionStructure) {
		this.sectionStructure = sectionStructure;
	}

	public SectionStructure getSectionStructure() {
		return sectionStructure;
	}

	public boolean isDeleted() {
		return status == Status.DELETED;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getVersion() {
		return version;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@XmlAttribute
	public Status getStatus() {
		return status;
	}

}
