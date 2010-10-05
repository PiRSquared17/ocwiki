package oop.module.search;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import oop.data.BaseQuestion;
import oop.data.File;
import oop.data.Test;
import oop.data.TestStructure;
import oop.data.TextArticle;
import oop.data.Topic;

public class Search {

	private static Dictionary<Class, String> entityAliases = null;
	static {
		entityAliases = new Hashtable<Class, String>();
		entityAliases.put(BaseQuestion.class, "basequestion");
		entityAliases.put(Test.class, "test");
		entityAliases.put(TestStructure.class, "teststructure");
		entityAliases.put(TextArticle.class, "textarticle");
		entityAliases.put(Topic.class, "topic");
		entityAliases.put(File.class, "file");
	}

	private static String getAlias(Class clazz) {
		return entityAliases.get(clazz);
	}
	
	public static List<Class> getAllSearchAbleClasses(){
		List<Class> allClasses = new ArrayList<Class>();
		Enumeration<Class> classEnum = entityAliases.keys();
		while(classEnum.hasMoreElements()){
			allClasses.add(classEnum.nextElement());
		}
		return allClasses;
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

	public String buildSearchQueryForClass(Class clazz) {
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
