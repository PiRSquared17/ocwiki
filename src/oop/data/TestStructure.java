package oop.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TestStructure extends BaseArticle {

	private String name;
	private String type;
	private int time;
	private Set<SectionStructure> sectionStructures = new HashSet<SectionStructure>();

	TestStructure() {
	}

	public TestStructure(String name, Text content, User author, 
			Date createDate) {
		super();
		this.name = name;
		this.content = content;
		this.author = author;
		this.createDate = createDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SectionStructure> getSectionStructures() {
		return sectionStructures;
	}
	
	public void setSectionStructures(Set<SectionStructure> sectionStructures) {
		this.sectionStructures = sectionStructures;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTime() {
		return time;
	}

	@Override
	public Namespace getNamespace() {
		return Namespace.TEST_STRUCTURE;
	}

}
