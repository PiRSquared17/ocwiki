package oop.data;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;

public class Question implements Entity {

	private long id;
	private Section section;
	private BaseQuestion base;
	private int mark;
	private Status status = Status.NORMAL;
	private Set<Answer> answers = new HashSet<Answer>();
	private int version;

	Question() {
	}
	
	public Question(BaseQuestion base, Section section, int mark) {
		this.base = base;
		this.section = section;
	}

	/**
	 * Dành cho JSP, tránh sử dụng hàm này.
	 * @return
	 */
	@Deprecated
	public Map<Long, Answer> getAnswerById() {
		Map<Long, Answer> usedAnswerMap = new HashMap<Long, Answer>();
		for (Answer answer : getAnswers()) {
			usedAnswerMap.put(answer.getId(), answer);
		}
		return usedAnswerMap;
	}
	
	@XmlAttribute
	public long getId() {
		return id;
	}
	
	public Set<Answer> getAnswers() {
		return answers;
	}
	
	public void setAnswers(Set<Answer> usedAnswers) {
		this.answers = usedAnswers;
	}
	
	public int getMark() {
		return mark;
	}
	
	public void setMark(int mark) {
		this.mark = mark;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Article getTest() {
		return getSection().getTest();
	}
	
	public User getAuthor() {
		return base.getAuthor();
	}

	public Text getContent() {
		return base.getContent();
	}

	public Date getCreateDate() {
		return base.getCreateDate();
	}

	public int getLevel() {
		return base.getLevel();
	}

	public boolean isDeleted() {
		return status == Status.DELETED;
	}

	public BaseQuestion getBase() {
		return base;
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
