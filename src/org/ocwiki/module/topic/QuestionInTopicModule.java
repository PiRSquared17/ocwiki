package org.ocwiki.module.topic;

import java.util.List;

import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.db.dao.MultichoiceQuestionDAO;
import org.ocwiki.module.DefaultModule;

public class QuestionInTopicModule extends DefaultModule {

	private List<Resource<MultichoiceQuestion>> questions;

	@Override
	public void init() throws Exception {
		questions = MultichoiceQuestionDAO.fetchByTopic(getResource().getId(), 0, 20);
	}
	
	public List<Resource<MultichoiceQuestion>> getQuestions() {
		return questions;
	}
	
}
