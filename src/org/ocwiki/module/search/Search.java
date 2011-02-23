package org.ocwiki.module.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ocwiki.data.BaseQuestion;
import org.ocwiki.data.File;
import org.ocwiki.data.Test;
import org.ocwiki.data.TestStructure;
import org.ocwiki.data.TextArticle;
import org.ocwiki.data.Topic;

public class Search {

	private static Map<Class<?>, String> entityAliases = null;
	static {
		entityAliases = new Hashtable<Class<?>, String>();
		entityAliases.put(BaseQuestion.class, "basequestion");
		entityAliases.put(Test.class, "test");
		entityAliases.put(TestStructure.class, "teststructure");
		entityAliases.put(TextArticle.class, "textarticle");
		entityAliases.put(Topic.class, "topic");
		entityAliases.put(File.class, "file");
	}

	private static String getAlias(Class<?> clazz) {
		return entityAliases.get(clazz);
	}
	
	public static List<Class<?>> getAllSearchAbleClasses(){
		return new ArrayList<Class<?>>(entityAliases.keySet());
	}
	
	public Search(){
		elements = new ArrayList<SearchElement>();
	}

	private List<SearchElement> elements;

	public void addSearchElement(SearchElement element){
		elements.add(element);
	}
	
	public void addSearchElement(SearchOperator operator,ISearchElement element){
		addSearchElement(new SearchElement(operator, element));
	}
	
	public List<SearchElement> getElements() {
		return elements;
	}

	public String buildSearchQueryForClass(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();
		sb.append("from ").append(clazz.getName()).append(" as ").append(
				getAlias(clazz));
		if (elements.size() > 0) {
			sb.append(" where ");
			for (SearchElement iSE : elements) {
				if(iSE.getElement().getSearchAbleClasses().contains(clazz)){
					if(iSE.getOperator()!=null){
						sb.append(iSE.getOperator().toString().toLowerCase());
					}
					sb.append(" ( ");
					sb.append(iSE.getElement().getQueryElement(getAlias(clazz)));
					sb.append(" ) ");
				}				
			}
		}
		return sb.toString();
	}

	public static class SearchElement {
		private SearchOperator operator;
		private ISearchElement element;

		public SearchOperator getOperator() {
			return operator;
		}

		public void setOperator(SearchOperator operator) {
			this.operator = operator;
		}

		public ISearchElement getElement() {
			return element;
		}

		public void setElement(ISearchElement element) {
			this.element = element;
		}

		public SearchElement(SearchOperator operator, ISearchElement element) {
			super();
			this.operator = operator;
			this.element = element;
		}
	}

	public static enum SearchOperator {

		AND(new String[] { "and", "va", "và" }), OR(new String[] { "or",
				"hoac", "hoặc" });

		private Set<String> aliases = null;

		SearchOperator(String[] aliases) {
			this.aliases = new HashSet<String>();
			for (String iS : aliases) {
				this.aliases.add(iS);
			}
		}

		public static Set<String> getAllKeyWords() {
			HashSet<String> tempSet = new HashSet<String>();
			for (SearchOperator iSO : SearchOperator.values()) {
				tempSet.addAll(iSO.aliases);
			}
			return tempSet;
		}

		public static SearchOperator getOperator(String alias) {
			for (SearchOperator iSO : SearchOperator.values()) {
				if (iSO.aliases.contains(alias)) {
					return iSO;
				}
			}
			return null;
		}
	}
}
