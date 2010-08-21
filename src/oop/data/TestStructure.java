package oop.data;

import java.util.ArrayList;
import java.util.List;

public class TestStructure extends BaseArticle {

	private String type;
	private int time;
	private List<SectionStructure> sectionStructures = new ArrayList<SectionStructure>();

	TestStructure() {
	}

	public TestStructure(Namespace namespace, String name, Text content, String type, int time) {
		super(namespace, content);
		setName(name);
		this.type = type;
		this.time = time;
	}


	public List<SectionStructure> getSectionStructures() {
		return sectionStructures;
	}
	
	public void setSectionStructures(List<SectionStructure> sectionStructures) {
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

	protected <T> T copyTo(T obj) {
		TestStructure struct = (TestStructure) obj;
		struct.setName(getName());
		struct.setTime(getTime());
		struct.setType(getType());
		struct.setSectionStructures(new ArrayList<SectionStructure>(
				getSectionStructures()));
		return super.copyTo(obj);
	};
	
	@Override
	public TestStructure copy() {
		return copyTo(new TestStructure());
	}

}
