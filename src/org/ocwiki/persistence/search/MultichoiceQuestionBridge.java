package org.ocwiki.persistence.search;

import org.ocwiki.data.Answer;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Resource;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

public class MultichoiceQuestionBridge implements FieldBridge {

	@Override
	public void set(String name, Object value, Document document,
			LuceneOptions luceneOptions) {
		if (((Resource<?>)value).getArticle() instanceof MultichoiceQuestion) {
			MultichoiceQuestion question = (MultichoiceQuestion) ((Resource<?>)value).getArticle();
			StringBuilder answers = new StringBuilder();
			for (Answer answer : question.getAnswers()) {
				answers.append(answer.getContent().getText()).append(' ');
			}
			document.add(new Field(name + ".answers", answers.toString(),
					Field.Store.NO, Field.Index.ANALYZED));
		}
	}

}
