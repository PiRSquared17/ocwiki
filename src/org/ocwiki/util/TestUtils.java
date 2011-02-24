package org.ocwiki.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocwiki.data.Answer;
import org.ocwiki.data.TestQuestion;
import org.ocwiki.data.Section;
import org.ocwiki.data.Test;
import org.ocwiki.data.TestVersion;

public final class TestUtils {

	public static TestVersion shuffle(Test test, String code) {
		Map<Section, List<TestQuestion>> questionMap = new HashMap<Section, List<TestQuestion>>();
		Map<TestQuestion, List<Answer>> answerMap = new HashMap<TestQuestion, List<Answer>>();
		for (Section section : test.getSections()) {
			List<TestQuestion> questionList = new ArrayList<TestQuestion>(section
					.getQuestions());
			Collections.shuffle(questionList);
			questionMap.put(section, questionList);
			
			for (TestQuestion question : questionList) {
				List<Answer> answerList = new ArrayList<Answer>(question.getAnswers());
				Collections.shuffle(answerList);
				answerMap.put(question, answerList);
			}
		}
		return new TestVersion(test, code, questionMap, answerMap);
	}
	
	public static TestVersion naturalOrder(Test test) {
		Map<Section, List<TestQuestion>> questionMap = new HashMap<Section, List<TestQuestion>>();
		Map<TestQuestion, List<Answer>> answerMap = new HashMap<TestQuestion, List<Answer>>();
		for (Section section : test.getSections()) {
			List<TestQuestion> questionList = new ArrayList<TestQuestion>(section
					.getQuestions());
			questionMap.put(section, questionList);
			
			for (TestQuestion question : questionList) {
				List<Answer> answerList = new ArrayList<Answer>(question.getAnswers());
				answerMap.put(question, answerList);
			}
		}
		return new TestVersion(test, null, questionMap, answerMap);
	}
	
}
