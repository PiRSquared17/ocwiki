package oop.data;

public class Media extends BaseArticle {

	private String name;
	private String extension;
	
	@Override
	public String getName() {
		return name;
	}
	
	public String getFileName() {
		return getId() + "-" + getVersion() + extension; 
	}

	@Override
	public Namespace getNamespace() {
		return Namespace.MEDIA;
	}

}
