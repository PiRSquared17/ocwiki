package oop.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Answer implements Entity {

	private long id;
	private BaseQuestion question;
	private Text content;
	private boolean correct;
	private boolean deleted;
	private int version;

	/**
	 * For Hibernate only
	 */
	public Answer() {
	}
	
	public Answer(BaseQuestion question, Text content, boolean correct) {
		super();
		this.question = question;
		this.correct = correct;
		this.content = content;
	}

	@XmlAttribute
	public long getId() {
		return id;
	}

	public Text getContent() {
		return content;
	}
	
	public void setContent(Text content) {
		this.content = content;
	}
	
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setQuestion(BaseQuestion question) {
		this.question = question;
	}

	@XmlTransient
	public BaseQuestion getQuestion() {
		return question;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Answer) {
			return id == ((Answer) obj).id;
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Long.valueOf(id).hashCode();
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
