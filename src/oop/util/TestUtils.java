package oop.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oop.data.Answer;
import oop.data.Question;
import oop.data.Section;
import oop.data.Test;
import oop.data.TestVersion;

public final class TestUtils {

	public static TestVersion shuffle(Test test, String code) {
		Map<Section, List<Question>> questionMap = new HashMap<Section, List<Question>>();
		Map<Question, List<Answer>> answerMap = new HashMap<Question, List<Answer>>();
		for (Section section : test.getSections()) {
			List<Question> questionList = new ArrayList<Question>(section
					.getQuestions());
			Collections.shuffle(questionList);
			questionMap.put(section, questionList);
			
			for (Question question : questionList) {
				List<Answer> answerList = new ArrayList<Answer>(question.getAnswers());
				Collections.shuffle(answerList);
				answerMap.put(question, answerList);
			}
		}
		return new TestVersion(test, code, questionMap, answerMap);
	}
	
	public static TestVersion naturalOrder(Test test) {
		Map<Section, List<Question>> questionMap = new HashMap<Section, List<Question>>();
		Map<Question, List<Answer>> answerMap = new HashMap<Question, List<Answer>>();
		for (Section section : test.getSections()) {
			List<Question> questionList = new ArrayList<Question>(section
					.getQuestions());
			questionMap.put(section, questionList);
			
			for (Question question : questionList) {
				List<Answer> answerList = new ArrayList<Answer>(question.getAnswers());
				answerMap.put(question, answerList);
			}
		}
		return new TestVersion(test, null, questionMap, answerMap);
	}
	
}
