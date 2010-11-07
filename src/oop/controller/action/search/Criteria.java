package oop.controller.action.search;

public class Criteria {

	private Tag tag;
	private String content;
	
	public Criteria(Tag tag, String content) {
		super();
		this.tag = tag;
		this.content = cleanUp(content);
	}

	private String cleanUp(String str) {
		str = str.replaceAll("\"|'|\\.|,", " ");
		str = str.replaceAll(" {2,}", " ");
		return str;
	}

	public enum Tag {
		FULLTEXT(""), 
		TOPIC("topic", "chủ-đề"), 
		IS("is", "là"), 
		NAME("name", "tên"), 
		AUTHOR("author", "tác-giả"), 
		STATUS("status", "trạng-thái"),
		TYPE("type", "loại");

		private String[] codes;

		private Tag(String... codes) {
			this.codes = codes;
		}
		
		public String[] getCodes() {
			return codes;
		}
	}

	public Tag getTag() {
		return tag;
	}

	public String getContent() {
		return content;
	}
	
}
