package oop.data;

import java.util.Date;
import java.util.Set;

public class History implements Entity {

	private long id;
	private User user;
	private Test test;
	private Date takenDate;
	private double mark;
	private int time;
	private Set<HistoryAnswer> answers;
	
	History() {
	}

	public History(User user, Test test, Date takenDate,
			Set<HistoryAnswer> answers, int time) {
		this.user = user;
		this.test = test;
		this.takenDate = takenDate;
		this.answers = answers;
		this.time = time;
		this.mark = computeMark();
	}

	private double computeMark() {
		double mark = 0;
		for (Section section : test.getSections()) {
			for (Question question : section.getQuestions()) {
				for (HistoryAnswer answer : answers) {
					if (answer.getQuestion().equals(question.getBase())) {
						if (answer.isCorrect()) {
							mark += question.getMark();
						}
					}
				}
			}
		}
		return mark;
	}
	
	public User getUser() {
		return user;
	}

	public Date getTakenDate() {
		return takenDate;
	}
	
	public double getMark() {
		return mark;
	}

	public int getTime() {
		return time;
	}

	public long getId() {
		return id;
	}

	public Test getTest() {
		return test;
	}

	public void setAnswers(Set<HistoryAnswer> answers) {
		this.answers = answers;
	}

	public Set<HistoryAnswer> getAnswers() {
		return answers;
	}

}
