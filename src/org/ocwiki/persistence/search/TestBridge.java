package org.ocwiki.persistence.search;

import org.ocwiki.data.Choice;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.TestQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Section;
import org.ocwiki.data.Test;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

public class TestBridge implements FieldBridge {

	@Override
	public void set(String name, Object value, Document document,
			LuceneOptions luceneOptions) {
		if (((Resource<?>)value).getArticle() instanceof Test) {
			Test test = (Test) ((Resource<?>)value).getArticle();
			StringBuilder sections = new StringBuilder();
			StringBuilder questions = new StringBuilder();
			StringBuilder answers = new StringBuilder();
			for (Section section : test.getSections()) {
				sections.append(section.getContent().getText()).append(". ");
				for (TestQuestion question : section.getQuestions()) {
					MultichoiceQuestion baseQuestion = question.getBase();
					questions.append(baseQuestion.getContent().getText()).append(". ");
					for (Choice choice : question.getAnswers()) {
						answers.append(choice.getContent().getText()).append(". ");
					}
				}
			}
			document.add(new Field(name + ".sections", sections.toString(),
					Field.Store.NO, Field.Index.ANALYZED));
			document.add(new Field(name + ".questions", questions.toString(),
					Field.Store.NO, Field.Index.ANALYZED));
			document.add(new Field(name + ".choices", answers.toString(),
					Field.Store.NO, Field.Index.ANALYZED));
		}
	}

}
