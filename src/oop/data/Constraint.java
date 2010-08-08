package oop.data;

public abstract class Constraint implements Entity {

	private long id;
	private SectionStructure sectionStructure;
	private int count;
	private boolean deleted;
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

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDeleted() {
		return deleted;
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

}
