package org.ocwiki.data;

import java.util.HashSet;
import java.util.Set;

public class ChoiceAnswer extends Answer {

	private Set<Choice> choices = new HashSet<Choice>();

	public ChoiceAnswer() {
	}
	
	public Choice getChoice() {
		if (choices.size() == 1) {
			return choices.iterator().next();
		}
		return null;
	}

	public void setChoices(Set<Choice> answers) {
		this.choices = answers;
	}

	public Set<Choice> getChoices() {
		return choices;
	}

	@Override
	public boolean isCorrect() {
		int correctCount = 0;
		MultichoiceQuestion question = (MultichoiceQuestion) getQuestion();
		for (Choice choice : question.getChoices()) {
			if (choice.isCorrect() != choices.contains(choice)) {
				return false;
			}
			if (choice.isCorrect()) {
				correctCount++;
			}
		}
		if (correctCount != choices.size()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getQuestion().getId()).append(" -> [");
		for (Choice choice : choices) {
			sb.append(choice.getId()).append(", ");
		}
		if (!choices.isEmpty()) {
			sb.delete(sb.length()-2, sb.length());
		}
		sb.append("]");
		return sb.toString();
	}
	
}
