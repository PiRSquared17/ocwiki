package oop.data;

import java.util.Date;

public class History implements Entity {

	private long id;
	private User user;
	private Test test;
	private Date takenDate;
	private double mark;
	private int time;

	History() {
	}

	public History(User user, Test test, Date takenDate, double mark,
			int time) {
		this.user = user;
		this.test = test;
		this.takenDate = takenDate;
		this.mark = mark;
		this.time = time;
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

}
