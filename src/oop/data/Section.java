package oop.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;

public class Section implements Entity {

	private long id;
	private Text text;
	private int order;
	private Article test;
	private Status status = Status.NORMAL;
	private Set<Question> questions = new HashSet<Question>();
	private int version;

	/**
	 * For Hibernate
	 */
	Section() {
	}
	
	public Section(Text text, int order, Article test) {
		this.text = text;
		this.order = order;
		this.test = test;
	}

	public Map<Long, Question> getQuestionById() {
		HashMap<Long, Question> questionMap = new HashMap<Long, Question>();
		for (Question q : getQuestions()) {
			questionMap.put(q.getId(), q);
		}
		return questionMap;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	public long getId() {
		return id;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	void setTest(Article test) {
		this.test = test;
	}

	public Article getTest() {
		return test;
	}

	public boolean isDeleted() {
		return status == Status.DELETED;
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
