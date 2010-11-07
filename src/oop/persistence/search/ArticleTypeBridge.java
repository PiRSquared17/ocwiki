package oop.persistence.search;

import oop.data.Article;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

public class ArticleTypeBridge implements FieldBridge {

	@Override
	public void set(String name, Object value, Document document,
			LuceneOptions luceneOptions) {
		Class<?> articleType = (Class<?>) value;
		StringBuilder sb = new StringBuilder();
		addType(articleType, sb);
		document.add(new Field(name, sb.toString(), Field.Store.NO,
				Field.Index.ANALYZED));
	}

	private void addType(Class<?> articleType, StringBuilder sb) {
		if (!articleType.equals(Article.class)
				&& !articleType.equals(Object.class)) {
			sb.append(articleType.getSimpleName()).append(' ');
			addType(articleType.getSuperclass(), sb);
		}
	}

}
