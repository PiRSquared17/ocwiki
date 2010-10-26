package oop.persistence;

import java.util.List;

import oop.data.ResourceCustomization;
import oop.data.ResourceLike;
import oop.data.ResourceTodo;
import oop.db.dao.ResourceCustomizationDAO;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;


public class ResourceCustomizationBridge implements FieldBridge {

	@Override
	public void set(String name, Object value, Document document,
			LuceneOptions luceneOptions) {
		List<ResourceCustomization> customizations = ResourceCustomizationDAO
				.fetchByResource((Long) value);
		StringBuilder likes = new StringBuilder();
		StringBuilder todos = new StringBuilder();
		StringBuilder dones = new StringBuilder();
		StringBuilder hards = new StringBuilder();
		StringBuilder easys = new StringBuilder();
		for (ResourceCustomization customization : customizations) {
			long userId = customization.getUser().getId();
			if (customization.getLike() == ResourceLike.LIKE) {
				likes.append(userId).append(' ');
			}
			if (customization.getTodo() == ResourceTodo.TODO) {
				todos.append(userId).append(' ');
			}
			if (customization.isDone()) {
				dones.append(userId).append(' ');
			}
			if (customization.getLevel() == 1) {
				hards.append(userId).append(' ');
			}
			if (customization.getLevel() == 0) {
				easys.append(userId).append(' ');
			}
		}
		document.add(new Field(name + ".likes", likes.toString(), Field.Store.NO, Field.Index.ANALYZED));
		document.add(new Field(name + ".todos", todos.toString(), Field.Store.NO, Field.Index.ANALYZED));
		document.add(new Field(name + ".dones", dones.toString(), Field.Store.NO, Field.Index.ANALYZED));
		document.add(new Field(name + ".hards", hards.toString(), Field.Store.NO, Field.Index.ANALYZED));
		document.add(new Field(name + ".easys", easys.toString(), Field.Store.NO, Field.Index.ANALYZED));
	}

}
