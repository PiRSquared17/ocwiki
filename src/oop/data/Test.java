package oop.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Test extends BaseArticle {

	private String name;
	private String type;
	private int time;
	private Set<Section> sections = new HashSet<Section>();

	Test() {
	}
	
	public Test(String name, Text content, Date createDate,
			User author, String type, int time) {
		super();
		this.name = name;
		this.content = content;
		this.createDate = createDate;
		this.author = author;
		this.type = type;
		this.time = time;
	}
	
	public Set<Section> getSections() {
		return sections;
	}
	
	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public int getQuestionCount() {
		int sum = 0;
		for (Section section : getSections()) {
			sum += section.getQuestions().size();
		}
		return sum;
	}

	@Override
	public Namespace getNamespace() {
		return Namespace.TEST;
	}

}
