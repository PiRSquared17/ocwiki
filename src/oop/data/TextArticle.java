package oop.data;

public class TextArticle extends BaseArticle {

	private Namespace namespace;
	private String name;
	
	TextArticle() {
	}
	
	public TextArticle(Namespace namespace, String name) {
		super();
		this.namespace = namespace;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Namespace getNamespace() {
		return namespace;
	}

}
