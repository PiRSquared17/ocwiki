package oop.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AnswerAttempt {

	private Resource<BaseQuestion> question;
	private User user;
	private boolean correct;
	private Date timestamp;

	public AnswerAttempt(Resource<BaseQuestion> question, User user, boolean correct) {
		super();
		this.timestamp = new Date();
		this.question = question;
		this.user = user;
		this.correct = correct;
	}

	public Resource<BaseQuestion> getQuestion() {
		return question;
	}

	public void setQuestion(Resource<BaseQuestion> question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
