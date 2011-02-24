package org.ocwiki.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuestionAttempt {

	private Resource<MultichoiceQuestion> question;
	private User user;
	private boolean correct;
	private Date timestamp;

	public QuestionAttempt() {
	}
	
	public QuestionAttempt(Resource<MultichoiceQuestion> question, User user, boolean correct) {
		super();
		this.timestamp = new Date();
		this.question = question;
		this.user = user;
		this.correct = correct;
	}

	public Resource<MultichoiceQuestion> getQuestion() {
		return question;
	}

	public void setQuestion(Resource<MultichoiceQuestion> question) {
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
