package org.ocwiki.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestAttempt implements Entity {

	private long id;
	private User user;
	private Revision<Test> revision;
	private Date takenDate;
	private double mark;
	private int time;
	private Set<Answer> answers;
	
	TestAttempt() {
	}

	public TestAttempt(User user, Revision<Test> revision, Date takenDate,
			Set<Answer> answers, int time) {
		this.user = user;
		this.revision = revision;
		this.takenDate = takenDate;
		setAnswers(answers);
		this.time = time;
	}

	private double computeMark() {
		double mark = 0;
		for (Section section : getTest().getSections()) {
			for (TestQuestion question : section.getQuestions()) {
				for (Answer answer : answers) {
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
		return revision.getArticle();
	}
	
	public Revision<Test> getRevision() {
		return revision;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
		if (answers == null) {
			mark = 0;
		} else {
			mark = computeMark();
		}
	}

	public Set<Answer> getAnswers() {
		return answers;
	}
	
	private Map<Question, Answer> answerByQuestionMap;
	
	public Map<Question, Answer> getAnswerByQuestion() {
		if (answerByQuestionMap == null) {
			answerByQuestionMap = new HashMap<Question, Answer>();
			for (Answer answer : answers) {
				answerByQuestionMap.put(answer.getQuestion(), answer);
			}
		}
		return answerByQuestionMap;
	}

}
