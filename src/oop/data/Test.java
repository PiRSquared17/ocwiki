package oop.data;

import java.util.ArrayList;
import java.util.List;

public class Test extends BaseArticle {

	private String type;
	private int time;
	private List<Section> sections = new ArrayList<Section>();

	public Test() {
	}
	
	public Test(Namespace namespace, String name, Text content, String type, int time) {
		super(namespace, content);
		setName(name);
		this.type = type;
		this.time = time;
	}
	
	public List<Section> getSections() {
		return sections;
	}
	
	public void setSections(List<Section> sections) {
		this.sections = sections;
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

	protected <T> T copyTo(T obj) {
		Test test = (Test)obj;
		test.setName(getName());
		test.setType(getType());
		test.setTime(getTime());
		test.setSections(new ArrayList<Section>(getSections()));
		return super.copyTo(obj);
	};
	
	@Override
	public Test copy() {
		return copyTo(new Test());
	}

}
