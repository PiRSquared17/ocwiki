package oop.data;

import java.util.HashSet;
import java.util.Set;

public class ChoiceAnswer extends HistoryAnswer {

	private Set<Answer> choosedAnswers = new HashSet<Answer>();

	public ChoiceAnswer() {
	}
	
	public Answer getAnswer() {
		if (choosedAnswers.size() == 1) {
			return choosedAnswers.iterator().next();
		}
		return null;
	}

	public void setAnswers(Set<Answer> answers) {
		this.choosedAnswers = answers;
	}

	public Set<Answer> getAnswers() {
		return choosedAnswers;
	}

	@Override
	public boolean isCorrect() {
		int correctCount = 0;
		for (Answer answer : getQuestion().getAnswers()) {
			if (answer.isCorrect() != choosedAnswers.contains(answer)) {
				return false;
			}
			if (answer.isCorrect()) {
				correctCount++;
			}
		}
		if (correctCount != choosedAnswers.size()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getQuestion().getId()).append(" -> [");
		for (Answer answer : choosedAnswers) {
			sb.append(answer.getId()).append(", ");
		}
		if (!choosedAnswers.isEmpty()) {
			sb.delete(sb.length()-2, sb.length());
		}
		sb.append("]");
		return sb.toString();
	}
	
}
